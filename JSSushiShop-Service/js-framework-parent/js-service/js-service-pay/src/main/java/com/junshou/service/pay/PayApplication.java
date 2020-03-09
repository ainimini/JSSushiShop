package com.junshou.service.pay;

import com.github.wxpay.sdk.MyWXPayConfig;
import com.github.wxpay.sdk.WXPay;
import com.junshou.common.interceptor.FeignInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author X
 * @version 1.0
 * @ClassName PayApplication
 * @description 支付启动类
 * @date 2020/2/14
 **/
@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2
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
