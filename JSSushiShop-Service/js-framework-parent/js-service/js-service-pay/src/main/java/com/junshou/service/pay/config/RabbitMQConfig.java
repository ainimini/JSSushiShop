package com.junshou.service.pay.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    /**********************************************发送普通订单******************************************************/

    /***
     * 队列
     * order_pay：订单支付队列
     */
    public static final String Q_ORDER_PAY = "q_order_pay";

    /***
     * 交换机
     * ex_order_pay：订单支付交换机
     */
    public static final String EX_ORDER_PAY = "ex_order_pay";


    /***
     * 声明队列
     */
    @Bean(Q_ORDER_PAY)
    public Queue Q_ORDER_PAY() {
        return new Queue(Q_ORDER_PAY);
    }

    /***
     * 声明交换机
     */
    @Bean(EX_ORDER_PAY)
    public Exchange EX_ORDER_PAY() {
        return ExchangeBuilder.directExchange(EX_ORDER_PAY).durable(true).build();
    }

    /***
     * 队列绑定交换机
     */
    @Bean
    public Binding BINDING_ORDER_PAY(@Qualifier(Q_ORDER_PAY) Queue queue, @Qualifier(EX_ORDER_PAY) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Q_ORDER_PAY).noargs();
    }



    /**********************************************发送秒杀订单******************************************************/

    /***
     * 队列
     * q_seckill_order_pay：秒杀订单支付队列
     */
    public static final String Q_SECKILL_ORDER_PAY = "q_seckill_order_pay";

    /***
     * 交换机
     * ex_seckill_order_pay：订单支付交换机
     */
    public static final String EX_SECKILL_ORDER_PAY = "ex_seckill_order_pay";


    /***
     * 声明队列
     */
    @Bean(Q_SECKILL_ORDER_PAY)
    public Queue Q_SECKILL_ORDER_PAY() {
        return new Queue(Q_SECKILL_ORDER_PAY);
    }

    /***
     * 声明交换机
     */
    @Bean(EX_SECKILL_ORDER_PAY)
    public Exchange EX_SECKILL_ORDER_PAY() {
        return ExchangeBuilder.directExchange(EX_SECKILL_ORDER_PAY).durable(true).build();
    }

    /***
     * 队列绑定交换机
     */
    @Bean
    public Binding BINDING_SECKILL_ORDER_PAY(@Qualifier(Q_SECKILL_ORDER_PAY) Queue queue, @Qualifier(EX_SECKILL_ORDER_PAY) Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(Q_SECKILL_ORDER_PAY).noargs();
    }
}
