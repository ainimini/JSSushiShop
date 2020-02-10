package com.junshou.common.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @ClassName: FeignInterceptor
 * @description: 使用Feign调用的时候，token会传递给下个微服务
 * @author: X
 * @updateTime: 2020/2/10 11:22
 */
@Component
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {

        /**
         * @author: X
         * @updateTime: 2020/2/10 10:05
         * 获取用户令牌
         * 将令牌封装到头文件中
         * 开启熔断策略默认线程池隔离 feign调用会开启新的线程
         * 需要将熔断策略换成信号量隔离，就不会开启新的线程
         */
        //记录了当前用户请求的所有数据，包括请求头和请求参数等
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        /**
         * @author: X
         * @updateTime: 2020/2/10 11:07
         * 获取请求头数据
         * 获取所有头的数据
         */
        Enumeration<String> headerNames = requestAttributes.getRequest().getHeaderNames();
        while (headerNames.hasMoreElements()) {
            //请求头的key
            String headerKey = headerNames.nextElement();
            //获取请求头中的值
            String headerValue = requestAttributes.getRequest().getHeader(headerKey);
            System.out.println(headerKey + ":" + headerValue);

            //将请求头信息封装到头中，使用Feign调用的时候，会传递给下个微服务
            requestTemplate.header(headerKey, headerValue);
        }

        //传递令牌
       /* RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (request != null) {
                Enumeration<String> headerNames = request.getHeaderNames();
                while (headerNames.hasMoreElements()) {
                    String headerName = headerNames.nextElement();
                    if ("authorization".equals(headerName)) {
                        String headerValue = request.getHeader(headerName); // Bearer jwt

                        //传递令牌
                        requestTemplate.header(headerName, headerValue);
                    }
                }
            }
        }*/
    }
}
