package com.junshou.service.pay.service.impl;

import com.github.wxpay.sdk.WXPayUtil;
import com.github.wxpay.sdk.WXPay;
import com.junshou.service.pay.service.WXPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author X
 * @version 1.0
 * @ClassName WXPayServiceImpl
 * @description 微信支付服务层实现
 * @date 2020/2/14
 **/
@Service
public class WXPayServiceImpl implements WXPayService {

    @Autowired
    private WXPay wxPay;
    @Value("${wxpay.notify_url}")
    private String notify_url;

    @Override
    public Map closeOrder(String orderId) {
        try {
            Map<String, String> map = new HashMap();
            map.put("out_trade_no", orderId);
            Map<String, String> resultMap = wxPay.closeOrder(map);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param orderId
     * @description: 基于微信查询订单状态
     * @return:
     * @author: X
     * @date: 2020/2/16
     */
    @Override
    public Map queryOrder(String orderId) {
        try {
            Map<String, String> map = new HashMap();
            map.put("out_trade_no", orderId);
            Map<String, String> resultMap = wxPay.orderQuery(map);
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param parameterMap
     * @description: 下单 生成二维码
     * 获取微信传过来的code_url 创建二维码
     * @return:
     * @author: X
     * @date: 2020/2/16
     */
    @Override
    public Map nativePay(Map<String, String> parameterMap) {
        try {
            HashMap<String, String> paramMap = new HashMap<>();
            /***
             * 参数
             */
            //随机字符串
            paramMap.put("nonce_str", WXPayUtil.generateNonceStr());
            //商品描述
            paramMap.put("body", parameterMap.get("body"));
            //商户订单号
            paramMap.put("out_trade_no", parameterMap.get("orderId"));
            //总金额
            paramMap.put("total_fee", parameterMap.get("money"));
            //终端IP
            paramMap.put("spbill_create_ip", "127.0.0.1");
            //通知地址
            paramMap.put("notify_url", notify_url);
            //交易类型
            paramMap.put("trade_type", "NATIVE");

            //基于wxpay完成统一下单接口的调用,并获取返回结果
            Map<String, String> result = wxPay.unifiedOrder(paramMap);
            //获取返回的数据
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
