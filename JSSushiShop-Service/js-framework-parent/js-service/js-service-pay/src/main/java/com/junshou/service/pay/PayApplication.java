package com.junshou.service.pay;

import com.github.wxpay.sdk.MyWXPayConfig;
import com.github.wxpay.sdk.WXPay;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author X
 * @version 1.0
 * @ClassName PayApplication
 * @description 支付启动类
 * @date 2020/2/14
 **/
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class PayApplication {

    public static void main(String[] args) {

        SpringApplication.run(PayApplication.class);
    }

    @Bean
    public WXPay wxPay(){
        try {
            return new WXPay(new MyWXPayConfig());
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
