package com.junshou.service.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author X
 * @version 1.0
 * @ClassName BusinessApplication
 * @description 商业启动类
 * @date 2020/2/18
 **/
@EnableEurekaClient
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = {"com.junshou.service.business.dao"})
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class);
    }
}
