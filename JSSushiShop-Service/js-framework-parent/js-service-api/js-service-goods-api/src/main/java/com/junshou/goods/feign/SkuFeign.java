package com.junshou.goods.feign;

import com.junshou.common.entity.Result;
import com.junshou.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/2/9
 * @Version 1.0
 **/
@FeignClient(value = "goods")
@RequestMapping("/sku")
public interface SkuFeign {

    /***
     * 回滚库存(增加库存并扣减销量)
     * @param skuId
     * @param num
     * @return
     */
    @RequestMapping("/resumeStockNum")
    Result resumeStockNum(@RequestParam("skuId") String skuId,@RequestParam("num")Integer num);

    /**
     * @description: 订单下单完成后 商品数量递减
     * Map封装 key 商品ID
     * value 商品递减的数量
     * @param:
     * @return:
     * @author: X
     * @date: 2020/2/11
     */
    @GetMapping(value = "/decr/count")
    Result decrCount(@RequestParam Map<String, Integer> decrMap);

    /***
     * 根据ID查询Sku数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Sku> findById(@PathVariable String id);
}
