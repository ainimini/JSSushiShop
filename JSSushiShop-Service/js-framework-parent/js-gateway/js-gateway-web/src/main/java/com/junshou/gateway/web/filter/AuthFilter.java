package com.junshou.gateway.web.filter;

import com.junshou.gateway.web.Util.JwtUtil;
import com.junshou.gateway.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter, Ordered {

    private static final String AUTHORIZE_TOKEN = "Authorization";

    /**
     * @param exchange
     * @param chain
     * @title: 全局拦截
     * @description:
     * @author: X
     * @updateTime: 2020/2/4 19:39
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //获取令牌信息
        //头文件中获取
        String token = request.getHeaders().getFirst(AUTHORIZE_TOKEN);
        //如果令牌没有在头文件中 false；令牌在头文件中 true
        boolean hasToken =true;
        //参数令牌中获取
        if (StringUtils.isEmpty(token)) {
            token = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
            hasToken =false;
        }
        //cookie中获取
        if (StringUtils.isEmpty(token)) {
            HttpCookie httpCookie = request.getCookies().getFirst(AUTHORIZE_TOKEN);
            if (httpCookie != null) {
                token = httpCookie.getValue();
            }
        }
        //如果没有令牌 进行拦截
        if (StringUtils.isEmpty(token)) {
            //设置没有权限的状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应空数据
            return response.setComplete();
        }
        //如果有令牌 检验令牌是否有效
        try {
            JwtUtil.parseJWT(token);
        } catch (Exception e) {
            //无效拦截
            //设置没有权限的状态码 401
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //响应空数据
            return response.setComplete();
        }
        //将令牌封装到头文件中
        request.mutate().header(AUTHORIZE_TOKEN,token);
        //有效放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
