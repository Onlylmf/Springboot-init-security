package com.murphy.constants;

/**
 * 用户常量
 *
*
 */
public interface UserConstant {


    /**
     * header中认证字段
     */
     String HEADER_NAME = "token";

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "user_login";

    //  region 权限

    /**
     * 默认角色
     */
    String ROLE_USER = "ROLE_USER";

    /**
     * 管理员角色
     */
    String ROLE_ADMIN = "ROLE_ADMIN";

    /**
     * 被封号
     */
    String BAN_ROLE = "ban";

    // endregion
}
