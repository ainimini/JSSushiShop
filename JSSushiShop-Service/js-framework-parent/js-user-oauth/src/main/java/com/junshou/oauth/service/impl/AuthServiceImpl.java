package com.junshou.oauth.service.impl;

import com.junshou.oauth.service.AuthService;
import com.junshou.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${security.oauth2.client.ttl}")
    private long ttl;

    @Override
    public AuthToken login(String username, String password, String clientId, String clientSecret) throws Exception {
        //1.申请令牌
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-auth");
        URI uri = serviceInstance.getUri();
        String url = uri + "/oauth/token";
        //String url = "http://localhost:9001/oauth/token/";

        //请求提交的数据封装
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", this.getHttpBasic(clientId, clientSecret));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

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

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        System.out.println(responseEntity.getBody().get("access_token"));
        Map map = responseEntity.getBody();
        if (map == null || map.get("access_token") == null || map.get("refresh_token") == null || map.get("jti") == null) {
            //申请令牌失败
            throw new RuntimeException("申请令牌失败");
        }

        //2.封装结果数据
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String) map.get("access_token"));
        authToken.setRefreshToken((String) map.get("refresh_token"));
        authToken.setJti((String) map.get("jti"));

        //3.将jti作为redis中的key,将jwt作为redis中的value进行数据的存放
        stringRedisTemplate.boundValueOps(authToken.getJti()).set(authToken.getAccessToken(), ttl, TimeUnit.SECONDS);
        return authToken;
    }

    //请求头封装
    private String getHttpBasic(String clientId, String clientSecret) throws Exception {
        String value = clientId + ":" + clientSecret;
        byte[] encode = Base64Utils.encode(value.getBytes());
        return "Basic " + new String(encode, "UTF-8");
    }

    /**
     * String转map
     *
     * @param string
     * @return
     */
    public static Map<String, Object> getStringToMap(String string) {
        //根据逗号截取字符串数组
        String[] str1 = string.split(",");
        //创建Map对象
        Map<String, Object> map = new HashMap<>();
        //循环加入map集合
        for (int i = 0; i < str1.length; i++) {
            //根据":"截取字符串数组
            String[] str2 = str1[i].split(":");
            //str2[0]为KEY,str2[1]为值
            map.put(str2[0], str2[1]);
        }
        return map;
    }
}
