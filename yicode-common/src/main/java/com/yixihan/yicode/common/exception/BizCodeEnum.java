package com.yixihan.yicode.common.exception;

/**
 * 异常枚举类
 *
 * @author yixihan
 * @date 2022-10-14-10:59
 */
public enum BizCodeEnum implements CommonError {

    /**
     * 失败类型：内部异常
     */
    FAILED_TYPE_INTERNAL(500, "服务器错误"),

    /**
     * 请求异常
     */
    FAILED_TYPE_BAD_REQUEST (400, "请求异常"),

    /**
     * 未授权
     */
    FAILED_TYPE_UNAUTHORIZED (401, "未授权"),

    /**
     * 禁止访问
     */
    FAILED_TYPE_FORBIDDEN (403, "禁止访问"),

    /**
     * 业务异常 (默认)
     */
    FAILED_TYPE_BUSINESS (20000, "业务异常"),

    /**
     * 参数校验异常
     */
    PARAMS_VALID_ERR(20001, "参数校验异常"),

    /**
     * 空指针异常
     */
    NULL_ERR(20002, "空指针异常"),

    // ===== sentinel 异常 ==== //
    /**
     * 系统限流
     */
    SENTINEL_FLOW_ERR(40001, "系统限流，请稍等"),

    /**
     * 服务降级
     */
    SENTINEL_DEGRADE_ERR(40002, "服务降级"),

    /**
     * 热点参数限流
     */
    SENTINEL_PARAM_FLOW_ERR(40003, "热点参数限流"),

    /**
     * 系统规则
     */
    SENTINEL_SYSTEM_BLOCK_ERR(40004, "系统规则（负载/...不满足要求）"),

    /**
     * 授权规则不通过
     */
    SENTINEL_AUTH_ERR(40005, "授权规则不通过"),

    // 登录异常
    /**
     * 用户名或密码错误
     */
    USERNAME_PASSWORD_ERR(10001, "用户名或密码错误"),

    /**
     * 账户已被禁用
     */
    ACCOUNT_DISABLED(10002, "账户已被禁用"),

    /**
     * 账户已被锁定
     */
    ACCOUNT_LOCKED(10003, "账户已被锁定"),

    /**
     * 账户已过期
     */
    ACCOUNT_EXPIRED(10004, "账户已过期"),

    /**
     * 账户凭据已过期
     */
    CREDENTIALS_EXPIRED(10005, "账户凭据已过期"),

    /**
     * token 过期
     */
    TOKEN_EXPIRED(10006, "登录凭证已过期, 请重新登录"),

    /**
     * 没有访问权限
     */
    NO_METHOD_ROLE(10007, "没有访问权限");


    ;


    /**
     * 状态码
     */
    private final int code;

    /**
     * 异常信息
     */
    private final String msg;

    BizCodeEnum(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public int getErrorCode() {
        return code;
    }

    @Override
    public String getErrorMsg() {
        return msg;
    }
}
