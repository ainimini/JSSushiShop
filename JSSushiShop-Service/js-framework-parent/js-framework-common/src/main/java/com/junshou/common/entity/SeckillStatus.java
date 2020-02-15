package com.junshou.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author X
 * @version 1.0
 * @ClassName SeckillStatus
 * @description 秒杀异步接收数据实体类
 * @date 2020/2/15
 **/
public class SeckillStatus implements Serializable {

    private String username;
    private Date createTime;
    private Integer status;
    private Long goodsId;
    private Float money;
    private Long orderId;
    private String time;

    public SeckillStatus(){

    }

    public SeckillStatus(String username,Date createTime, Integer status,Long goodsId,String time){
        this.username = username;
        this.createTime = createTime;
        this.status = status;
        this.goodsId = goodsId;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
