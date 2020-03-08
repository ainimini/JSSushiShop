package com.junshou;

import com.junshou.common.interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
@MapperScan(basePackages = "com.junshou.server.user.dao")
@EnableFeignClients(basePackages = {"com.junshou.user.feign"})
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class,args);
    }

    /**
     * @description: 将feign拦截器注入到容器中
     * @author: X
     * @updateTime: 2020/2/10 11:26
     */
    @Bean
    public FeignInterceptor feignInterceptor() {
        return new FeignInterceptor();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}