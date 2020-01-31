package com.junshou.service.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/1/30
 * @Version 1.0
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan(basePackages = {"com.junshou.service.goods.dao"})
public class GoodsApplication {

    public static void main(String[] args){
        SpringApplication.run(GoodsApplication.class);
    }
}
