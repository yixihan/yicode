package com.yixihan.yicode.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * docker 容器状态枚举类
 *
 * @author yixihan
 * @date 2023/2/2 17:20
 */
@AllArgsConstructor
@Getter
public enum DockerContainerStatusEnums {
    
    /**
     * 创建
     */
    created ("created", "创建"),
    
    /**
     * 运行
     */
    running ("running", "运行"),
    
    /**
     * 暂停
     */
    paused ("paused", "暂停"),
    
    /**
     * 重新启动
     */
    restarting ("restarting", "重新启动"),
    
    /**
     * 删除
     */
    removing ("removing", "删除"),
    
    /**
     * 退出
     */
    exited ("exited", "退出"),
    
    /**
     * 死
     */
    dead ("dead", "死");
    
    /**
     * 状态
     */
    private final String status;
    
    /**
     * 描述
     */
    private final String description;
}
