package com.junshou.gateway.web.filter;

import com.alibaba.fastjson.JSON;
import com.junshou.gateway.web.Util.JwtUtil;
import com.junshou.gateway.web.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @title: 全局过滤器
 * @description: 实现用户权限鉴别（校验）
 * @author: X
 * @updateTime: 2020/2/4 19:39
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    /***
     * 令牌名字
     */
    private static final String AUTHORIZE_TOKEN = "Authorization";
    private static final String LOGIN_URL="http://localhost:8001/api/oauth/toLogin";

    @Autowired
    private AuthService authService;

    /**
     * @param exchange
     * @param chain
     * @title: 全局过滤器
     * @description: 全局拦截
     * @author: X
     * @updateTime: 2020/2/4 19:39
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //1.判断当前请求路径是否为登录请求,如果是,则直接放行
        String path = request.getURI().getPath();
        if ("/api/oauth/login".equals(path) || !UrlFilter.hasAuthorize(path)){
            //直接放行
            return chain.filter(exchange);
        }

        //2.从cookie中获取jti的值,如果该值不存在,拒绝本次访问
        String jti = authService.getJtiFromCookie(request);
        if (org.apache.commons.lang.StringUtils.isEmpty(jti)){
            //拒绝访问
            /*response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            //跳转登录页面
            return this.toLoginPage(LOGIN_URL+"?FROM="+request.getURI().getPath(),exchange);
        }

        //3.从redis中获取jwt的值,如果该值不存在,拒绝本次访问
        String jwt = authService.getJwtFromRedis(jti);
        System.out.println(jwt);
        Map map = JSON.parseObject(jwt, Map.class);
        String accessToken = (String) map.get("accessToken");
        if (StringUtils.isEmpty(jwt)){
            //拒绝访问
            /*response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();*/
            return  this.toLoginPage(LOGIN_URL,exchange);
        }

        //4.对当前的请求对象进行增强,让它会携带令牌的信息
        request.mutate().header(AUTHORIZE_TOKEN,"Bearer "+accessToken);
        return chain.filter(exchange);
    }

    //跳转登录页面
    private Mono<Void> toLoginPage(String loginUrl, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location",loginUrl);
        return response.setComplete();
    }

   /* @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //用户如果登录或者一些不需要权限认证的请求，直接放行
        String url = request.getURI().toString();
        if (UrlFilter.hasAuthorize(url)) {
            return chain.filter(exchange);
        }

        *//***
         * 获取令牌信息
         *//*
        //头文件中获取
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        //如果令牌没有在头文件中 false；令牌在头文件中 true
        boolean hasToken = true;
        //参数令牌中获取
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            hasToken = false;
        }
        //cookie中获取
        if (StringUtils.isEmpty(token)) {
            HttpCookie httpCookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if (httpCookie != null) {
                token = httpCookie.getValue();
            }
        }
        //令牌为空 进行拦截
        if (StringUtils.isEmpty(token)) {
            //无效拦截
            //设置没有权限的状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应空数据
            return response.setComplete();
        } else {
            //判断请求头中是否有token
            if (!hasToken) {
                //判断token前缀是否有bearer 如果没有添加
                if (!token.startsWith("bearer ") && !token.startsWith("Bearer ")) {
                    token = "bearer " + token;
                }
                //将令牌封装到头文件中
                request.mutate().header(AUTHORIZE_TOKEN, token);
            }
        }
        //有效放行
        return chain.filter(exchange);
    }*/

    /**
     * @title: 全局过滤器
     * @description: 排序 越小越先执行
     * @author: X
     * @updateTime: 2020/2/4 19:39
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
