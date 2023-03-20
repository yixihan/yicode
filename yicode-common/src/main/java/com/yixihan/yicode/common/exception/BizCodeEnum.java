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
    FAILED_TYPE_INTERNAL (500, "服务器错误"),

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
    PARAMS_VALID_ERR (20001, "参数校验异常"),

    /**
     * 空指针异常
     */
    NULL_ERR (20002, "空指针异常"),
    
    // ============================================================== docker 异常
    /**
     * 镜像拉取错误
     */
    DOCKER_PULL_ERR (30001, "镜像拉取错误"),
    
    /**
     * 容器创建错误
     */
    DOCKER_CREATE_ERR (30002, "容器创建错误"),
    
    /**
     * 容器删除错误
     */
    DOCKER_REMOVE_ERR (30003, "容器删除错误"),
    
    
    // ============================================================== 代码运行异常
    /**
     * 代码运行异常
     */
    CODE_RUN_ERR (40001, "代码运行异常"),
    
    /**
     * 代码运行限流
     */
    CODE_FLOW_ERR (40002, "代码运行限流"),

    // ============================================================== sentinel 异常
    /**
     * 系统限流
     */
    SENTINEL_FLOW_ERR (50001, "系统限流，请稍等"),

    /**
     * 服务降级
     */
    SENTINEL_DEGRADE_ERR (50002, "服务降级"),

    /**
     * 热点参数限流
     */
    SENTINEL_PARAM_FLOW_ERR (50003, "热点参数限流"),

    /**
     * 系统规则
     */
    SENTINEL_SYSTEM_BLOCK_ERR (50004, "系统规则（负载/...不满足要求）"),

    /**
     * 授权规则不通过
     */
    SENTINEL_AUTH_ERR (50005, "授权规则不通过"),
    
    //============================================================== 认证授权异常
    /**
     * 用户名或密码错误
     */
    USERNAME_PASSWORD_ERR (10001, "用户名或密码错误"),

    /**
     * 账户已被禁用
     */
    ACCOUNT_DISABLED (10002, "账户已被禁用"),

    /**
     * 账户已被锁定
     */
    ACCOUNT_LOCKED (10003, "账户已被锁定"),

    /**
     * 账户已过期
     */
    ACCOUNT_EXPIRED (10004, "账户已过期"),

    /**
     * 账户凭据已过期
     */
    CREDENTIALS_EXPIRED (10005, "账户凭据已过期"),

    /**
     * token 过期
     */
    TOKEN_EXPIRED (10006, "登录凭证已过期, 请重新登录"),

    /**
     * 没有访问权限
     */
    NO_METHOD_ROLE (10007, "没有访问权限"),
    
    /**
     * 账户不存在
     */
    ACCOUNT_NOT_FOUND (10008, "账户不存在"),
    
    //==============================================================第三方服务
    /**
     * 文件上传失败
     */
    OSS_ERR (90001, "文件上传失败"),
    
    /**
     * 邮件发送失败
     */
    EMAIL_SEND_ERR (90002, "邮件发送失败"),
    
    /**
     * 短信发送失败
     */
    SMS_SEND_ERR (90003, "短信发送失败"),
    
    /**
     * 二维码生成错误
     */
    QR_CODE_ERR (90004, "二维码生成错误"),
    
    /**
     * 图片验证码生成错误
     */
    PHOTO_CODE_ERR (90005, "图片验证码生成错误"),
    
    /**
     * 邮箱校验失败
     */
    MOBILE_VALIDATE_ERR (90006, "手机号校验失败"),
    
    /**
     * 邮箱校验失败
     */
    EMAIL_VALIDATE_ERR (90007, "邮箱校验失败"),
    
    /**
     * uuid 为空
     */
    UUID_VALIDATE_ERR (90008, "uuid校验失败"),
    
    /**
     * 验证码为空
     */
    CODE_EMPTY_ERR (90009, "验证码不能为空"),
    
    /**
     * 验证码错误
     */
    CODE_VALIDATE_ERR (90010, "验证码错误"),

    /**
     * 验证码已过期
     */
    CODE_EXPIRED_ERR (90011, "验证码已过期")
    ;


    /**
     * 状态码
     */
    private final int code;

    /**
     * 异常信息
     */
    private final String msg;

    BizCodeEnum(int code, String msg) {
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
