package com.yixihan.yicode.common.constant;

/**
 * 权限相关常量定义
 *
 * @author yixihan
 * @date 2022-10-21-17:53
 */
public interface AuthConstant {

    /**
     * JWT存储权限前缀
     */
    String AUTHORITY_PREFIX = "";

    /**
     * JWT存储权限属性
     */
    String AUTHORITY_CLAIM_NAME = "authorities";


    /**
     * Redis 用户缓存权限规则 key
     */
    String USER_MAP_KEY = "auth:userMap";

    /**
     * Redis 方法缓存权限规则 key
     */
    String METHOD_ROLE_MAP_KEY = "auth:methodRoleMap";

    /**
     * 认证信息Http请求头
     */
    String JWT_TOKEN_HEADER = "Authorization";

    /**
     * JWT令牌前缀
     */
    String JWT_TOKEN_PREFIX = "Bearer ";

    /**
     * 用户信息Http请求头
     */
    String USER_TOKEN_HEADER = "user";

    /**
     *  token 信息起始索引
     */
    Integer TOKEN_SUB_INDEX = 7;
}
