package com.junshou.service.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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
}
