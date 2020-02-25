package com.junshou.service.seckill.config.rabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**********************************************监听秒杀订单******************************************************/
    /***
     * 队列
     * q_seckill_order_pay：秒杀订单支付队列
     */
    public static final String Q_SECKILL_ORDER_PAY = "q_seckill_order_pay";

    /**********************************************发送延时队列******************************************************/

    /***
     * 队列
     */
    public static final String Q_SECKILL_ORDER_DELAY = "q_seckill_order_delay";
    public static final String Q_SECKILL_ORDER_DELAY_LISTENER = "q_seckill_order_delay_listener";

    /***
     * 交换机
     * ex_order_pay：订单支付交换机
     */
    public static final String EX_SECKILL_ORDER_DELAY_LISTENER = "ex_seckill_order_delay_listener";


    /***
     * 声明队列Queue1
     * 延时队列 会过期 过期后将数据发送给Queue2
     */
    @Bean(Q_SECKILL_ORDER_DELAY)
    public Queue Q_SECKILL_ORDER_DELAY() {
        return QueueBuilder.durable(Q_SECKILL_ORDER_DELAY)
                //消息超时进入死信队列 绑定私信队列交换机
                .withArgument("x-dead-letter-exchange", EX_SECKILL_ORDER_DELAY_LISTENER)
                //绑定指定的routing-key
                .withArgument("x-dead-letter-routing-key", Q_SECKILL_ORDER_DELAY_LISTENER)
                .build();
    }

    /***
     * 声明队列Queue2
     *
     */
    @Bean(Q_SECKILL_ORDER_DELAY_LISTENER)
    public Queue Q_SECKILL_ORDER_DELAY_LISTENER() {
        return new Queue(Q_SECKILL_ORDER_DELAY_LISTENER, true);
    }


    /***
     * 声明交换机
     */
    @Bean(EX_SECKILL_ORDER_DELAY_LISTENER)
    public Exchange EX_SECKILL_ORDER_DELAY_LISTENER() {
        return ExchangeBuilder.directExchange(EX_SECKILL_ORDER_DELAY_LISTENER).durable(true).build();
    }

    /***
     * 队列绑定交换机
     */
    @Bean
    public Binding BINDING_ORDER_DELAY(@Qualifier(Q_SECKILL_ORDER_DELAY_LISTENER) Queue queue, @Qualifier(EX_SECKILL_ORDER_DELAY_LISTENER) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Q_SECKILL_ORDER_DELAY_LISTENER).noargs();
    }
}
