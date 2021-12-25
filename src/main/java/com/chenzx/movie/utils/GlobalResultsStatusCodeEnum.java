package com.chenzx.movie.utils;

/**
 * @author ChenZexuan
 * @version 1.0
 * @date 2021/12/15 16:37
 */
public enum GlobalResultsStatusCodeEnum {

    /**
     * 成功
     */
    SUCCESS(200),
    /**
     * 通用失败状态码
     */
    FAIL(-1),
    /**
     * 账户被锁定
     */
    SECURITY_ACCOUNT_LOCKED(-2),
    /**
     * 账户被禁用
     */
    SECURITY_ACCOUNT_DISABLE(-3),
    /**
     * 用户名或密码输入错误
     */
    SECURITY_ACCOUNT_USERNAME_PASSWORD_ERROR(-4),
    /**
     * 需要登录
     */
    SECURITY_LOGIN_REQUIRED(-5),
    /**
     * 无权限访问
     */
    SECURITY_NO_ACCESS(-6),
    /**
     * 请求参数错误
     */
    REQUEST_PARAMETER_ERROR(-7);


    private final Integer value;

    GlobalResultsStatusCodeEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
