package com.junshou.goods.feign;

import com.junshou.common.entity.Result;
import com.junshou.goods.pojo.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * 根据ID查询Sku数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    Result<Sku> findById(@PathVariable String id);
}
