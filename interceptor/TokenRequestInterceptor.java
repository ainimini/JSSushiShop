package com.junshou.oauth.interceptor;

import com.junshou.oauth.util.AdminToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/2/10
 * @Version 1.0
 **/
@Configuration
public class TokenRequestInterceptor implements RequestInterceptor {

    /**
     * @return requestTemplate
     * @description: feign执行之前进行拦截
     * @author: X
     * @updateTime: 2020/2/10 10:12
     * @param:
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {

        /**
         * @author: X
         * @updateTime: 2020/2/10 10:05
         * 从数据库查询用户信息
         * 没有令牌，在feign调用之前，生成令牌
         * 在feign调用之前，令牌需要携带过去
         * 在feign调用之前，令牌需要放到头文件中
         * 请求feign调用->拦截器RequestInterceptor->feigin调用之前执行拦截
         */
        //生成管理员令牌
        String adminToken = AdminToken.adminToken();
        requestTemplate.header("Authorization", "bearer " + adminToken);
    }
}
