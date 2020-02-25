package com.junshou.service.seckill.listener;

import com.alibaba.fastjson.JSON;
import com.junshou.common.entity.SeckillStatus;
import com.junshou.service.seckill.config.rabbitMQ.RabbitMQConfig;
import com.junshou.service.seckill.service.SeckillOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author X
 * @version 1.0
 * @ClassName SeckillOrderTimeOutListener
 * @description 秒杀订单支付超时监听
 * @date 2020/2/26
 **/
@Component
@RabbitListener(queues = RabbitMQConfig.Q_SECKILL_ORDER_DELAY_LISTENER)
public class SeckillOrderTimeOutListener {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private SeckillOrderService seckillOrderService;

    /***
     * 延时订单监听
     * @param message
     */
    @RabbitHandler
    public void receiveCloseOrderMessage(String message) {
        try {
            //获取用户排队信息
            SeckillStatus seckillStatus = JSON.parseObject(message, SeckillStatus.class);
            /***
             * 如果此时Redis中没有用户排队信息 则表明该订单已经处理
             * 有用户排队信息 则表明用户尚未完成支付 关闭支付
             */
            Object userQueueStatus = redisTemplate.boundHashOps("UserQueueStatus_").get(seckillStatus.getUsername());
            if (null != userQueueStatus) {
                //关闭微信支付
                
                //删除订单
                seckillOrderService.closeSeckillOrder(seckillStatus.getUsername());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
