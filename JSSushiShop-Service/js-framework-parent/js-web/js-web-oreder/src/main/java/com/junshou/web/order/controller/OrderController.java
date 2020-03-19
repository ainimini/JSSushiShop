package com.junshou.web.order.controller;

import com.junshou.common.entity.Result;
import com.junshou.order.feign.CartFeign;
import com.junshou.order.feign.OrderFeign;
import com.junshou.order.pojo.Order;
import com.junshou.order.pojo.OrderItem;
import com.junshou.user.feign.AddressFeign;
import com.junshou.user.pojo.Address;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/worder")
public class OrderController {

    @Autowired
    private AddressFeign addressFeign;

    @Autowired
    private CartFeign cartFeign;

    @RequestMapping("/ready/order")
    public String readyOrder(Model model){
        //收件人的地址信息
        List<Address> addressList = addressFeign.findAll().getData();
        model.addAttribute("address",addressList);

        //购物车信息
        Map map = cartFeign.list();
        List<OrderItem> orderItemList = (List<OrderItem>) map.get("orderItemList");
        Integer totalMoney = (Integer) map.get("totalMoney");
        Integer totalNum = (Integer) map.get("totalNum");

        model.addAttribute("carts",orderItemList);
        model.addAttribute("totalMoney",totalMoney);
        model.addAttribute("totalNum",totalNum);

        //默认收件人信息
        for (Address address : addressList) {
            if ("1".equals(address.getIsDefault())){
                //默认收件人
                model.addAttribute("deAddr",address);
                break;
            }
        }
        return "order";
    }

    @Autowired
    private OrderFeign orderFeign;

    @PostMapping("/addOrder")
    @ResponseBody
    public Result add(@RequestBody Order order) throws Exception {
        Result result = orderFeign.addOrder(order);
        return result;
    }

    @GetMapping("/toPayPage")
    public String toPayPage(String orderId,Model model){
        //获取到订单的相关信息
        Order order = orderFeign.findById(orderId).getData();
        model.addAttribute("orderId",orderId);
        model.addAttribute("payMoney",order.getPayMoney());
        return "pay";
    }
}
