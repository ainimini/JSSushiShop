package com.junshou.service.seckill;

import com.junshou.common.util.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author X
 * @version 1.0
 * @ClassName SeckillApplication
 * @description 秒杀服务启动类
 * @date 2020/2/14
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients(basePackages = {"com.junshou.pay.feign"})
@MapperScan(basePackages = {"com.junshou.service.seckill.dao"})
@EnableScheduling
/***
 * 开启异步执行方式
 */
@EnableAsync
public class SeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillApplication.class);
    }

    /***
     * IdWorker注入
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
