package com.junshou.service.order.listener;

import com.alibaba.fastjson.JSON;
import com.junshou.service.order.config.rabbitMQ.RabbitMQConfig;
import com.junshou.service.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author X
 * @version 1.0
 * @ClassName OrderPayListener
 * @description 订单支付消息监听
 * @date 2020/2/24
 **/
@Component
@RabbitListener(queues = RabbitMQConfig.Q_ORDER_PAY)
public class OrderPayListener {

    @Autowired
    private OrderService orderService;

    public static final String SUCCESS = "success";

    /***
     * 支付结果监听
     * @param message
     */
    @RabbitHandler
    public void receivePayMessage(String message) throws Exception {
        //支付结果
        Map resultMap = JSON.parseObject(message, Map.class);
        System.out.println("接收到了订单支付的消息:" + resultMap);
        //通信标识
        String return_code = (String) resultMap.get("return_code");
        if (return_code.equals(SUCCESS)) {
            //业务结果
            String result_code = (String) resultMap.get("result_code");
            //订单号
            String orderId = (String) resultMap.get("out_trade_no");
            //支付成功修改订单状态
            if (result_code.equals(SUCCESS)) {
                String timeEnd = (String) resultMap.get("time_end");
                String transactionId = (String) resultMap.get("transaction_id");
                orderService.updatePayStatus(orderId,timeEnd,transactionId);
            } else {
                //支付失败 取消订单 回滚库存 关闭微信支付
                orderService.closeOrder(orderId);
            }
        }
    }
}
