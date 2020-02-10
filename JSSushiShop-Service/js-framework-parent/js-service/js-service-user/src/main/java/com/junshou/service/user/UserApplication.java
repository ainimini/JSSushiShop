package com.junshou.service.user;


import com.junshou.common.interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/1/21
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
/**
 * 开启通用mapper的包扫描
 */
@MapperScan(basePackages = {"com.junshou.service.user.dao"})
public class UserApplication {

    public static void main(String[] args) {

        SpringApplication.run(UserApplication.class);
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
}
