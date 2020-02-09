package com.junshou.oauth.controller;

import com.junshou.common.entity.Result;
import com.junshou.oauth.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/2/9
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/userLogin")
public class UserLoginController {

    //客户端id
    @Value("${security.oauth2.client.clientId}")
   private String clientId;
    //客户端秘钥
    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;
    @Autowired
    private UserLoginService userLoginService;


    @RequestMapping(value = "/login")
    public Result login(String username,String password)throws Exception{
        String grant_type = "password";
        userLoginService.login(username,password,clientId,clientSecret,grant_type);
        return null;
    }
}
