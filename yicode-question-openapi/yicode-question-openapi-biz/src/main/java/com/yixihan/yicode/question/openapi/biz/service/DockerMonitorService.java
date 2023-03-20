package com.yixihan.yicode.question.openapi.biz.service;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.PullImageResultCallback;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.RestartPolicy;
import com.yixihan.yicode.common.enums.DockerContainerStatusEnums;
import com.yixihan.yicode.common.exception.BizCodeEnum;
import com.yixihan.yicode.common.exception.BizException;
import com.yixihan.yicode.common.util.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * docker 容器监视 服务类
 *
 * @author yixihan
 * @date 2023/2/3 9:42
 */
@Slf4j
@Service
public class DockerMonitorService {
    
    @Resource
    private DockerClient client;
    
    @Value ("${docker.remote-image-name}")
    private String remoteImageName;
    
    @Value ("${docker.container-count}")
    private Integer containerCount;
    
    @Value ("${docker.network-mode}")
    private String networkMode;
    
    /**
     * 容器 ID
     */
    private static final Set<String> CONTAINER_ID_SET = new HashSet<> ();
    
    /**
     * 镜像 ID
     */
    private String imageId;
    
    /**
     * 镜像名称
     */
    private static final String IMAGE_NAME = "yicode-run";
    
    /**
     * 启动服务时初始化容器, 删除原有容器, 创建新容器
     */
    @PostConstruct
    public void init() {
        // 获取镜像
        Image image = getImage ();
        
        // 镜像不存在
        if (image == null) {
            try {
                // 拉取远程镜像
                pullRemoteImage ();
            
                // 获取镜像
                image = getImage ();
            
                if (image == null) {
                    throw new BizException (BizCodeEnum.DOCKER_PULL_ERR);
                }
            } catch (InterruptedException e) {
                log.error ("镜像拉取错误!, {}", e.getMessage (), e);
                Thread.currentThread ().interrupt ();
                throw new BizException (BizCodeEnum.DOCKER_PULL_ERR);
            }
        }
    
        // 镜像 ID
        imageId = image.getId ();
        
        // 删除 yicode-run 容器
        delContainers ();
        
        // 创建新容器
        createContainers ();
    }
    
    /**
     * 容器监视<br>
     * 定时方法, 每十分钟执行一次<br>
     * 遍历容器, 找到不正常的容器, 删除并重新创建
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void monitor () {
        ExecutorService executor = ThreadUtil.newExecutor ();

        CONTAINER_ID_SET.forEach (item -> executor.execute (() -> {
            String status = client.inspectContainerCmd (item).exec ().getState ().getStatus ();
            DockerContainerStatusEnums statusEnums = DockerContainerStatusEnums.valueOf (status);
            if (DockerContainerStatusEnums.dead.equals (statusEnums) ||
                    DockerContainerStatusEnums.exited.equals (statusEnums)) {
                // 容器退出或死亡, 则删除容器
                client.stopContainerCmd (item).exec ();
                client.removeContainerCmd (item).exec ();
                // 重新创建容器
                createContainers ();
            } else if (DockerContainerStatusEnums.paused.equals (statusEnums)) {
                // 容器暂停, 则继续运行容器
                client.startContainerCmd (item).exec ();
            }
        }));
    }
    
    /**
     * 拉取远程 yicode-run 镜像
     */
    private void pullRemoteImage() throws InterruptedException {
        PullImageResultCallback exec = client
                .pullImageCmd (remoteImageName)
                .exec (new PullImageResultCallback ());
        // 等待拉取完毕
        exec.awaitCompletion ();
    }
    
    /**
     * 获取 yicode-run 镜像
     */
    private Image getImage() {
        return client.listImagesCmd ().exec ().stream ()
                .filter (item ->
                        Arrays.stream (item.getRepoTags ()).anyMatch (o ->
                                StrUtil.contains (o, IMAGE_NAME)))
                .findFirst ()
                .orElse (null);
    }
    
    /**
     * 创建新容器
     *
     */
    private void createContainers() {
        try {
            ExecutorService executor = ThreadUtil.newExecutor ();
            
            for (int i = 1; i <= containerCount; i++) {
                int number = i;
                executor.execute (() -> {
                    // 运行环境设置
                    ArrayList<String> env = new ArrayList<> ();
                    env.add ("JAVA_OPTS=’-Xmx512m’");
                    
                    // 主机配置
                    HostConfig hostConfig = new HostConfig ()
                            // 设置自动重启
                            .withRestartPolicy (RestartPolicy.alwaysRestart ())
                            // 设置网络模式
                            .withNetworkMode (networkMode);
                    
                    // 创建容器
                    CreateContainerResponse response = client.createContainerCmd (imageId)
                            // 容器名称
                            .withName ("yicode-run-" + number)
                            // 主机配置
                            .withHostConfig (hostConfig)
                            // 运行时环境配置
                            .withEnv (env).exec ();
                    
                    // 启动容器
                    client.startContainerCmd (response.getId ()).exec ();
                    CONTAINER_ID_SET.add (response.getId ());
                });
            }
            
            // 关闭线程
            executor.shutdown();
            // 等待线程执行完毕
            Assert.isTrue (executor.awaitTermination (Long.MAX_VALUE, TimeUnit.SECONDS),
                    BizCodeEnum.DOCKER_CREATE_ERR);
        } catch (InterruptedException e) {
            Thread.currentThread ().interrupt ();
            throw new BizException (BizCodeEnum.DOCKER_CREATE_ERR);
        }
    }
    
    /**
     * 删除所有 yicode-run 容器
     */
    private void delContainers() {
        try {
            log.info ("开始删除原有容器...");
            ExecutorService executor = ThreadUtil.newExecutor ();
            client.listContainersCmd ()
                    .withShowAll (Boolean.TRUE)
                    .exec ()
                    .stream ()
                    .filter (item ->
                            item.getImageId () != null && item.getImageId ().equals (imageId))
                    .forEach (contain -> executor.execute (() -> {
                        client.stopContainerCmd (contain.getId ()).exec ();
                        client.removeContainerCmd (contain.getId ()).exec ();
                    }));
            // 关闭线程
            executor.shutdown();
            // 等待线程执行完毕
            Assert.isTrue (executor.awaitTermination (Long.MAX_VALUE, TimeUnit.SECONDS),
                    BizCodeEnum.DOCKER_REMOVE_ERR);
            log.info ("原有容器删除完毕!");
        } catch (InterruptedException e) {
            Thread.currentThread ().interrupt ();
            throw new BizException (BizCodeEnum.DOCKER_REMOVE_ERR);
        }
    }
}
