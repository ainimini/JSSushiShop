package com.junshou.service.user.service;

import com.github.pagehelper.PageInfo;
import com.junshou.user.pojo.Cities;

import java.util.List;

/****
 * @Author: X
 * @Description:Cities业务层接口
 *****/
public interface CitiesService {

    /***
     * Cities多条件分页查询
     * @param cities
     * @param page
     * @param size
     * @return
     */
    PageInfo<Cities> findPage(Cities cities, int page, int size);

    /***
     * Cities分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Cities> findPage(int page, int size);

    /***
     * Cities多条件搜索方法
     * @param cities
     * @return
     */
    List<Cities> findList(Cities cities);

    /***
     * 删除Cities
     * @param id
     */
    void delete(String id);

    /***
     * 修改Cities数据
     * @param cities
     */
    void update(Cities cities);

    /***
     * 新增Cities
     * @param cities
     */
    void add(Cities cities);

    /**
     * 根据ID查询Cities
     * @param id
     * @return
     */
     Cities findById(String id);

    /***
     * 查询所有Cities
     * @return
     */
    List<Cities> findAll();
}
