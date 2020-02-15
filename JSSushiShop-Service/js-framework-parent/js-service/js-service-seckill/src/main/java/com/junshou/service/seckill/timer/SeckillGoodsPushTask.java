package com.junshou.service.seckill.timer;

import com.junshou.common.util.DateUtil;
import com.junshou.seckill.pojo.SeckillGoods;
import com.junshou.service.seckill.dao.SeckillGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author X
 * @version 1.0
 * @ClassName SeckillGoodsPushTask
 * @description 定时将秒杀商品存入到Redis缓存中
 * @date 2020/2/14
 **/
@Component
public class SeckillGoodsPushTask {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 定时操作
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void loadGoodsPushRedis() {

        /***
         * 秒杀库存要大于0
         * 审核需要通过
         */
        //求时间菜单
        List<Date> dateMenus = DateUtil.getDateMenus();
        //循环查询每个时间区间的秒杀商品
        for (Date dateMenu : dateMenus) {
            //时间格式 yyyyMMddHH
            String timeSpace = "SeckillGoods_" + DateUtil.date2Str(dateMenu);
            //新建条件查询
            Example example = new Example(SeckillGoods.class);
            Example.Criteria criteria = example.createCriteria();
            //审核状态
            criteria.andEqualTo("status", "1");
            //秒杀商品库存
            criteria.andGreaterThan("stockCount", "0");
            //时间 开始时间大于等于当前时间
            criteria.andGreaterThanOrEqualTo("startTime", dateMenu);
            //结束时间小于当前时间
            criteria.andLessThan("startTime", DateUtil.addDateHour(dateMenu, 2));

            /***
             * 排除已经存入到Redis中的SeckillGoods
             * 求出当前命名空间下所有的key
             * 每次查询都排除之前存在key的数据
             */
            //求出当前命名空间下所有的key
            Set keys = redisTemplate.boundHashOps(timeSpace).keys();
            if (keys != null && keys.size() > 0) {
                //排除
                criteria.andNotIn("id", keys);
            }
            //查询数据
            List<SeckillGoods> seckillGoods = seckillGoodsMapper.selectByExample(example);
            for (SeckillGoods seckillGood : seckillGoods) {
                System.out.println(seckillGood.getId() + timeSpace);
                //将数据存入到Redis中
                redisTemplate.boundHashOps(timeSpace).put(seckillGood.getId(), seckillGood);
            }
        }
    }
}
