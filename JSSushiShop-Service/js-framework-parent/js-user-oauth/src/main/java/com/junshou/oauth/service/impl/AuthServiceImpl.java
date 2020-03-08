package com.junshou.oauth.service.impl;

import com.alibaba.fastjson.JSON;
import com.junshou.oauth.service.AuthService;
import com.junshou.oauth.util.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
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
        //申请令牌
        AuthToken authToken = this.getAuthToken(username, password, clientId, clientSecret);
        if (authToken == null) {
            throw new RuntimeException("申请令牌失败");
        }
        //用户身份令牌
        String jti = authToken.getJti();
        //存储到redis中的内容
        String authTokenString = JSON.toJSONString(authToken);
        //3.将jti作为redis中的key,将jwt作为redis中的value进行数据的存放
        boolean result = this.saveToken(jti, authTokenString, ttl);
        if (!result) {
            throw new RuntimeException("存储令牌失败");
        }
        return authToken;
    }

    /***
     * 将jti作为redis中的key,将jwt作为redis中的value进行数据的存放
     * @param jti
     * @param authTokenString
     * @param ttl
     * @return
     */
    private boolean saveToken(String jti, String authTokenString, long ttl) {
        stringRedisTemplate.boundValueOps(jti).set(authTokenString, ttl, TimeUnit.SECONDS);
        Long expire = stringRedisTemplate.getExpire(jti, TimeUnit.SECONDS);
        return expire > 0;
    }

    /***
     * 申请令牌
     * @param username
     * @param password
     * @param clientId
     * @param clientSecret
     * @return
     * @throws Exception
     */
    private AuthToken getAuthToken(String username, String password, String clientId, String clientSecret) throws Exception {
        //通过LoadBalancerClient类可以获取eureka中的注册信息
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-auth");
        if (null == serviceInstance) {
            throw new RuntimeException("找不到对应的服务");
        }
        //URI uri = serviceInstance.getUri();
        String url = serviceInstance.getUri().toString() + "/oauth/token";

        //请求提交的数据封装
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("username", username);
        body.add("password", password);

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Authorization", this.getHttpBasic(clientId, clientSecret));

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        /***
         * 使用默认设置很难（不可能）在RestTemplate中处理401响应。
         * 实际上是可行的，但是您必须提供错误处理程序和请求工厂。
         * 错误处理程序是显而易见的，但是问题是默认的请求工厂使用java.net，当您尝试查看响应的状态代码时，它可能引发HttpRetryException（尽管它显然是可用的）。
         * 解决方案是使用HttpComponentsClientHttpRequestFactory
         */
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                /*if (response.getRawStatusCode() != 400 && response.getRawStatusCode() != 401) {
                    super.handleError(response);
                }*/
                if (response.getStatusCode() != HttpStatus.UNAUTHORIZED && response.getStatusCode() != HttpStatus.BAD_REQUEST) {
                    super.handleError(response);
                }
            }
        });

        ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
        Map map = responseEntity.getBody();
        if (map == null || map.get("access_token") == null || map.get("refresh_token") == null || map.get("jti") == null) {
            //申请令牌失败 解析spring security返回的错误信息
            if (map != null && map.get("error_description") != null) {
                String errorDescription = (String) map.get("error_description");
                if (errorDescription.indexOf("UserDetailsService returned null") >= 0) {
                    throw new RuntimeException("用户不存在");
                } else if (errorDescription.indexOf("用户名或密码错误") >= 0) {
                    throw new RuntimeException("用户名或密码错误");
                }
            }
            return null;
        }

        //2.封装结果数据
        AuthToken authToken = new AuthToken();
        authToken.setAccessToken((String) map.get("access_token"));
        authToken.setRefreshToken((String) map.get("refresh_token"));
        authToken.setJti((String) map.get("jti"));
        return authToken;
    }

    /***
     * 从redis查询令牌
     * @param token
     * @return
     */
    @Override
    public AuthToken getUserToken(String token) {
        String key = token;
        //从redis中取到令牌信息
        String jwt = stringRedisTemplate.boundValueOps(key).get();
        //转成对象
        try {
            AuthToken authToken = JSON.parseObject(jwt, AuthToken.class);
            return authToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /***
     * 退出
     * 删除Redis中的jwt令牌
     * @param uid
     * @return
     */
    @Override
    public boolean delToken(String uid) {
        String key = uid;
        stringRedisTemplate.delete(key);
        return true;
    }

    /***
     * 请求头封装
     * @param clientId
     * @param clientSecret
     * @return
     * @throws Exception
     */
    private String getHttpBasic(String clientId, String clientSecret) throws Exception {
        String value = clientId + ":" + clientSecret;
        byte[] encode = Base64Utils.encode(value.getBytes());
        return "Basic " + new String(encode, "UTF-8");
    }
}
