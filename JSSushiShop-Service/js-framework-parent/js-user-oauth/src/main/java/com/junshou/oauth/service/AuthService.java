package com.junshou.oauth.service;

import com.junshou.oauth.util.AuthToken;

public interface AuthService {

    AuthToken login(String username, String password, String clientId, String clientSecret) throws Exception;
}
