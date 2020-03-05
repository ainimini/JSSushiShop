package com.junshou.oauth.service;

import com.junshou.oauth.util.AuthToken;

public interface AuthService {

    /***
     * 登录
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @return
     * @throws Exception
     */
    AuthToken login(String username, String password, String clientId, String clientSecret) throws Exception;

    /***
     * 验证登录用户的信息
     * @param jti
     * @return
     */
    AuthToken getUserToken(String jti);

    /***
     * 退出
     * 删除Redis中的jwt令牌
     * @param jti
     * @return
     */
    boolean delToken(String jti);
}
