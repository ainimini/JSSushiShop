package com.junshou.goods.feign;

import com.junshou.common.entity.Result;
import com.junshou.goods.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName dell
 * @Description TOOD
 * @Author X
 * @Data 2020/2/9-21:49
 * @Version 1.0
 **/
@FeignClient(value = "goods")
@RequestMapping("/spu")
public interface SpuFeign {

    /***
     * 根据ID查询Spu数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Spu> findById(@PathVariable String id);
}
