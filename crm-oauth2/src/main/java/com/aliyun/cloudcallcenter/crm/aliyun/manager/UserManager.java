package com.aliyun.cloudcallcenter.crm.aliyun.manager;

import com.aliyun.cloudcallcenter.crm.model.User;

/**
 * @author edward
 * @date 2017/11/12
 */
public interface UserManager {
    /**
     * 通过用户名查找用户信息
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 更新用户在阿里云的访问令牌
     *
     * @param userName
     * @param accessToken
     * @param isRefreshed 是否是通过refreshToken获取的accessToken,刷新令牌和用授权码换取的令牌的返回值一致，但不包含refresh_token和id_token。
     * @return
     */
    User updateAccessTokenByUserName(String userName, String accessToken, boolean isRefreshed);


    void revokeAccessTokenByUserName(String name,  boolean tokenPackageIsExist);
}
