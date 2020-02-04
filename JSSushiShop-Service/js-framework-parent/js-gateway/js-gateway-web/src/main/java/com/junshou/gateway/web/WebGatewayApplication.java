package com.junshou.gateway.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @ClassName WebGatewayApplication
 * @Description Web网关启动类
 * @Author X
 * @Data 2020/1/30
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
public class WebGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebGatewayApplication.class,args);
    }

    /**
     * 创建用户唯一标识 使用IP作为用户以表示 根据IP进行限流操作
     */
    @Bean(name = "ipKeyResolver")
    public KeyResolver ipKeyResolver() {
        return new KeyResolver(){
            @Override
            public  Mono<String> resolve(ServerWebExchange exchange) {
                Mono<String> ip = Mono.just(exchange.getRequest().getRemoteAddress().getHostName());
                System.out.println("用户唯一标识IP"+ip);
                return ip;
            }
        };
    }

}
