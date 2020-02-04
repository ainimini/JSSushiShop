package com.junshou.service.user.service;

import com.github.pagehelper.PageInfo;
import com.junshou.user.pojo.Areas;

import java.util.List;

/****
 * @Author: X
 * @Description:Areas业务层接口
 *****/
public interface AreasService {

    /***
     * Areas多条件分页查询
     * @param areas
     * @param page
     * @param size
     * @return
     */
    PageInfo<Areas> findPage(Areas areas, int page, int size);

    /***
     * Areas分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Areas> findPage(int page, int size);

    /***
     * Areas多条件搜索方法
     * @param areas
     * @return
     */
    List<Areas> findList(Areas areas);

    /***
     * 删除Areas
     * @param id
     */
    void delete(String id);

    /***
     * 修改Areas数据
     * @param areas
     */
    void update(Areas areas);

    /***
     * 新增Areas
     * @param areas
     */
    void add(Areas areas);

    /**
     * 根据ID查询Areas
     * @param id
     * @return
     */
     Areas findById(String id);

    /***
     * 查询所有Areas
     * @return
     */
    List<Areas> findAll();
}
