package com.junshou.service.order.listener;

import com.junshou.service.order.config.rabbitMQ.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    /***
     * 延时订单监听
     * @param message
     */
    @RabbitHandler
    public void receiveCloseOrderMessage(String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(new Date());
        System.out.println("监听订单时间" + format);
        System.out.println("监听消息" + message);
    }
}
