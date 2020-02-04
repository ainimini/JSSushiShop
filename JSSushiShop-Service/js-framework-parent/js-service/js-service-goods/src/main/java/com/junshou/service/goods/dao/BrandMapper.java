package com.junshou.service.goods.dao;
import com.junshou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author: X
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {

    /**
     * @title 查询品牌集合
     * @description 根据分类id查询品牌集合
     * @author: X
     * @updateTime 2020/2/1 9:02
     * @return
     * @param categoryId
     */
    @Select(value = "SELECT tb.* FROM tb_category_brand tcb,tb_brand tb WHERE tb.id=tcb.brand_id AND tcb.category_id=#{categoryId}")
    List<Brand> findBrandByCategoryId(Integer categoryId);
}
