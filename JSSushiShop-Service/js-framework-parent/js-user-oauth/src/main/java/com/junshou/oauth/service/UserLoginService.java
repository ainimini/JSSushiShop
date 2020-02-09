package com.junshou.oauth.service;


/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/2/9-10:19
 * @Version 1.0
 **/
public interface UserLoginService {
    void login(String username, String password, String clientId, String clientSecret, String grant_type) throws Exception;
}
