package com.junshou.service.order.listener;

import com.alibaba.fastjson.JSON;
import com.junshou.order.pojo.Order;
import com.junshou.pay.feign.PayFeign;
import com.junshou.service.order.config.rabbitMQ.RabbitMQConfig;
import com.junshou.service.order.service.OrderService;
import feign.RequestTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

/**
 * @author X
 * @version 1.0
 * @ClassName OrderTimeOutListener
 * @description 订单支付过期监听
 * @date 2020/2/25
 **/
@Component
@RabbitListener(queues = RabbitMQConfig.Q_ORDER_DELAY_LISTENER)
public class OrderTimeOutListener {

    @Autowired
    private OrderService orderService;

    /***
     * 延时订单监听
     * @param message
     */
    @RabbitHandler
    public void receiveCloseOrderMessage(String message) throws Exception {
        try {
            //调用业务层完成关闭订单的操作
            orderService.closeOrder(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
