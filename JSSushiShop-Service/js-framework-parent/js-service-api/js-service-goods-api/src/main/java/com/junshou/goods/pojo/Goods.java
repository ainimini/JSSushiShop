package com.junshou.goods.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Goods
 * @Description 商品添加实体类
 * @Author X
 * @Data 2020/2/1
 * @Version 1.0
 **/
public class Goods implements Serializable {

    //spu
    private Spu spu;

    //sku集合
    private List<Sku> skuList;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<Sku> skuList) {
        this.skuList = skuList;
    }
}
