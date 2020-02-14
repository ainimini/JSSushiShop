package com.junshou.service.goods.dao;
import com.junshou.goods.pojo.Sku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

/****
 * @Author: X
 * @Description:Sku的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface SkuMapper extends Mapper<Sku> {

    /**
     * @description: 库存递减
     * @param id
     * @param num
     * @return:
     * @author: X
     * @date: 2020/2/12
     */
    @Update("update tb_sku set num=num-#{num} where id=#{id} and num>=#{num}")
    int decrCount(@Param(value = "id") String id,
                  @Param(value = "num") Integer num);
}
