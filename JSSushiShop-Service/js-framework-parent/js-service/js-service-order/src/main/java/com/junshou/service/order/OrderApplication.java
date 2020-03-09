package com.junshou.service.order;

import com.junshou.common.interceptor.FeignInterceptor;
import com.junshou.common.util.IdWorker;
import feign.RequestTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName OrderApplication
 * @Description 订单启动类
 * @Author X
 * @Data 2020/2/9
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan(basePackages = {"com.junshou.service.order.dao"})
@EnableFeignClients(basePackages = {"com.junshou.goods.feign","com.junshou.user.feign","com.junshou.pay.feign"})
public class OrderApplication {

    public static void main(String arge[]) {
        SpringApplication.run(OrderApplication.class);
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

    /***
     * IdWorker注入
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }

    @Bean
    public RequestTemplate requestTemplate(){
        return new RequestTemplate();
    }
}
