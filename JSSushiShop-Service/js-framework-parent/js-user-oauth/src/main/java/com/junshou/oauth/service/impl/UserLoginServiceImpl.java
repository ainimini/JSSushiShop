package com.junshou.oauth.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.junshou.oauth.service.UserLoginService;
import org.apache.http.entity.BasicHttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/2/9
 * @Version 1.0
 **/
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void login(String username, String password, String clientId, String clientSecret, String grant_type) throws Exception {

        String url = "http://localhost:9001/oauth/token/";

        //请求数据封装
        MultiValueMap<String,String> parameterMap = new LinkedMultiValueMap<String,String>();
        parameterMap.add("username", username);
        parameterMap.add("password", password);
        parameterMap.add("grant_type", grant_type);


        //请求头封装
        String Authorization = "Basic" + new String(Base64.getEncoder().encode((clientId + ":" + clientSecret).getBytes()),"UTF-8");
        MultiValueMap headerMap = new LinkedMultiValueMap();
        headerMap.add("Authorization", Authorization);

        HttpEntity httpEntity = new HttpEntity(parameterMap,headerMap);


        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
               /* if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }*/
                if (response.getStatusCode() != HttpStatus.UNAUTHORIZED && response.getStatusCode() != HttpStatus.BAD_REQUEST) {
                    super.handleError(response);
                }
            }
        });
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        System.out.println(exchange.getBody());
    }
}
