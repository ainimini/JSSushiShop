package com.junshou.service.pay.controller;

import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPayUtil;
import com.junshou.common.entity.Result;
import com.junshou.common.entity.StatusCode;
import com.junshou.common.util.ConvertUtil;
import com.junshou.service.pay.service.WXPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author X
 * @version 1.0
 * @ClassName WXPayController
 * @description 微信支付控制层
 * @date 2020/2/14
 **/
@RestController
@RequestMapping(value = "/wxpay")
@Api(value = "微信支付管理接口", description = "微信支付管理接口，提供页面的增、删、改、查")
public class WXPayController {

    @Autowired
    private WXPayService wxPayService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /***
     * 基于微信关闭订单
     * @param orderId
     * @return
     */
    @PutMapping("/close/{orderId}")
    @ApiOperation("基于微信关闭订单")
    public Result closeOrder(@PathVariable("orderId") String orderId) {
        Map map = wxPayService.closeOrder(orderId);
        return new Result(true, StatusCode.OK, "关闭订单成功", map);
    }

    /**
     * @param request
     * @param response
     * @description: 支付结果通知回调方法
     * @return:
     * @author: X
     * @date: 2020/2/16
     */
    @RequestMapping("/notify")
    @ApiOperation("支付结果通知回调方法")
    public void notifyLogic(HttpServletRequest request, HttpServletResponse response) {
        try {
            //输入流转换为字符串
            String xml = ConvertUtil.convertToString(request.getInputStream());
            System.out.println(xml);

            //基于微信发送的通知内容,完成后续的业务逻辑处理
            Map<String, String> map = WXPayUtil.xmlToMap(xml);
            if ("SUCCESS".equals(map.get("result_code"))) {

                //查询订单
                Map result = wxPayService.queryOrder(map.get("out_trade_no"));
                System.out.println("查询订单结果:" + result);

                if ("SUCCESS".equals(result.get("result_code"))) {

                    //将订单的消息发送到mq
                    Map message = new HashMap();
                    message.put("orderId", result.get("out_trade_no"));
                    message.put("transactionId", result.get("transaction_id"));

                    //获取自定义参数
                    String attach = map.get("attach");
                    Map attachMap = JSON.parseObject(attach, Map.class);
                    //消息的发送
                    rabbitTemplate.convertAndSend((String) attachMap.get("exchange"), (String) attachMap.get("routingkey"), JSON.toJSONString(message));

                    //完成双向通信
                    rabbitTemplate.convertAndSend("paynotify", "", result.get("out_trade_no"));
                } else {
                    //输出错误原因
                    System.out.println(map.get("err_code_des"));
                }

            } else {
                //输出错误原因
                System.out.println(map.get("err_code_des"));
            }

            //给微信一个结果通知
            response.setContentType("text/xml");
            String data = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
            response.getWriter().write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param orderId
     * @description: 基于微信查询订单状态
     * @return:
     * @author: X
     * @date: 2020/2/16
     */
    @GetMapping("/query")
    @ApiOperation("基于微信查询订单状态")
    public Result queryOrder(@RequestParam("orderId") String orderId) {
        Map map = wxPayService.queryOrder(orderId);
        return new Result(true, StatusCode.OK, "查询订单状态成功", map);
    }

    /**
     * @param parameterMap
     * @description: 下单 生成二维码
     * @return:
     * @author: X
     * @date: 2020/2/16
     */
    @GetMapping("/nativePay")
    @ApiOperation("下单 生成二维码")
    public Result nativePay(@RequestParam Map<String, String> parameterMap) {
        Map resultMap = wxPayService.nativePay(parameterMap);
        return new Result(true, StatusCode.OK, "创建二维码预付订单成功", resultMap);
    }
}
