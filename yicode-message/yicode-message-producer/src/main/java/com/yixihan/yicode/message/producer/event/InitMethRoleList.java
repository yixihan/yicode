package com.yixihan.yicode.message.producer.event;

import com.yixihan.yicode.common.constant.AuthConstant;
import com.yixihan.yicode.common.enums.RoleEnums;
import com.yixihan.yicode.common.valid.RoleAccess;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 初始化接口权限数据, 缓存至 Redis 中
 * <p>
 * key : auth:methodRoleMap
 *
 * @author yixihan
 * @date 2022-10-23-22:34
 */
@Component
public class InitMethRoleList {


    static List<RoleEnums> defaultRoleList = new ArrayList<> ();

    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "requestMappingHandlerMapping")
    private RequestMappingHandlerMapping mapping;
    @Value("${spring.application.name}")
    private String applicationName;

    @PostConstruct
    public void initUrlRole() {

        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods ();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet ()) {
            // 获取方法所需的角色权限
            List<RoleEnums> roleList;
            RequestMappingInfo info = m.getKey ();
            HandlerMethod method = m.getValue ();
            RoleAccess roleAccess = method.getMethodAnnotation (RoleAccess.class);
            if (roleAccess == null || roleAccess.value () == null || roleAccess.value ().length == 0) {
                roleList = defaultRoleList;
            } else {
                roleList = new ArrayList<> (Arrays.asList (roleAccess.value ()));
            }

            // 获取方法 url
            PatternsRequestCondition p = info.getPatternsCondition ();
            String completeUrl = "/api/" + applicationName;
            String methodUrl = "";
            for (String url : p.getPatterns ()) {
                methodUrl = url;
            }
            redisTemplate.opsForHash ().put (AuthConstant.METHOD_ROLE_MAP_KEY, completeUrl + methodUrl, roleList);
        }
    }
}
