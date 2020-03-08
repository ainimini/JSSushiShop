package com.junshou.service.user.service.impl;
import com.junshou.service.user.dao.PermissionMapper;
import com.junshou.user.pojo.Permission;
import com.junshou.service.user.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Permission业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;


    /**
     * Permission条件+分页查询
     * @param permission 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Permission> findPage(Permission permission, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(permission);
        //执行搜索
        return new PageInfo<Permission>(permissionMapper.selectByExample(example));
    }

    /**
     * Permission分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Permission> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Permission>(permissionMapper.selectAll());
    }

    /**
     * Permission条件查询
     * @param permission
     * @return
     */
    @Override
    public List<Permission> findList(Permission permission){
        //构建查询条件
        Example example = createExample(permission);
        //根据构建的条件查询数据
        return permissionMapper.selectByExample(example);
    }


    /**
     * Permission构建查询对象
     * @param permission
     * @return
     */
    public Example createExample(Permission permission){
        Example example=new Example(Permission.class);
        Example.Criteria criteria = example.createCriteria();
        if(permission!=null){
            // 
            if(!StringUtils.isEmpty(permission.getId())){
                    criteria.andEqualTo("id",permission.getId());
            }
            // 
            if(!StringUtils.isEmpty(permission.getRoleId())){
                    criteria.andEqualTo("roleId",permission.getRoleId());
            }
            // 
            if(!StringUtils.isEmpty(permission.getMenuId())){
                    criteria.andEqualTo("menuId",permission.getMenuId());
            }
            // 
            if(!StringUtils.isEmpty(permission.getCreateTime())){
                    criteria.andEqualTo("createTime",permission.getCreateTime());
            }
        }
        return example;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(String id){
        permissionMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Permission
     * @param permission
     */
    @Override
    public void update(Permission permission){
        permissionMapper.updateByPrimaryKey(permission);
    }

    /**
     * 增加Permission
     * @param permission
     */
    @Override
    public void add(Permission permission){
        permissionMapper.insert(permission);
    }

    /**
     * 根据ID查询Permission
     * @param id
     * @return
     */
    @Override
    public Permission findById(String id){
        return  permissionMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Permission全部数据
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectAll();
    }
}
