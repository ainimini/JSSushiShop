package com.junshou.service.seckill.listener;

import com.alibaba.fastjson.JSON;
import com.junshou.pay.feign.PayFeign;
import com.junshou.service.seckill.config.rabbitMQ.RabbitMQConfig;
import com.junshou.service.seckill.service.SeckillOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author X
 * @version 1.0
 * @ClassName SeckillPayListener
 * @description 秒杀支付消息监听
 * @date 2020/2/25
 **/
@Component
@RabbitListener(queues = RabbitMQConfig.Q_SECKILL_ORDER_PAY)
public class SeckillPayListener {

    @Autowired
    private SeckillOrderService seckillOrderService;
    @Autowired
    private PayFeign payFeign;

    public static final String SUCCESS = "success";

    @RabbitListener
    public void receivePayMessage(String message) {
        try {
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
                //自定义数据
                String attach = (String) resultMap.get("attachMap");
                Map attachMap = JSON.parseObject(attach, Map.class);
                //支付成功修改订单状态
                if (result_code.equals(SUCCESS)) {
                    String timeEnd = (String) resultMap.get("time_end");
                    String transactionId = (String) resultMap.get("transaction_id");
                    //修改订单状态
                    seckillOrderService.updataPayStatus((String) attachMap.get("username"), timeEnd, transactionId);
                } else {
                    //关闭微信支付
                    payFeign.closeOrder(orderId);
                    //支付失败 取消订单 回滚库存
                    seckillOrderService.closeSeckillOrder((String) attachMap.get("username"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}