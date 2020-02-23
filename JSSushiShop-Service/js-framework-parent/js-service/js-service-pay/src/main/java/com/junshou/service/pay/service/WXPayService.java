package com.junshou.service.pay.service;

import java.util.Map;

/**
 * @author X
 * @version 1.0
 * @ClassName WXPayService
 * @description 微信支付服务层接口
 * @date 2020/2/14
 **/
public interface WXPayService {

    /**
     * @description: 基于微信关闭订单
     * @param orderId
     * @return:
     * @author: X
     * @date: 2020/2/17
     */
    Map closeOrder(String orderId);

    /**
     * @param parameterMap
     * @description: 下单 生成二维码
     * @return:
     * @author: X
     * @date: 2020/2/16
     */
    Map nativePay(Map<String, String> parameterMap);

    /**
     * @param orderId
     * @description: 基于微信查询订单状态
     * @return:
     * @author: X
     * @date: 2020/2/16
     */
    Map queryOrder(String orderId);
}
