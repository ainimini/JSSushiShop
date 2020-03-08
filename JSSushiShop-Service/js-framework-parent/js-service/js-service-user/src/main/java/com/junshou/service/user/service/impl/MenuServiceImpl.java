package com.junshou.service.user.service.impl;
import com.junshou.common.entity.Result;
import com.junshou.service.user.dao.MenuMapper;
import com.junshou.user.pojo.Menu;
import com.junshou.service.user.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junshou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/****
 * @Author:shenkunlin
 * @Description:Menu业务层接口实现类
 * @Date 2019/6/14 0:16
 *****/
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /***
     * 查询用户所有权限
     * @param username
     * @return
     */
    @Override
    public List<Menu> findPermissionByUserId(String username) {
        return  menuMapper.selectPermissionByUserId(username);
    }

    /**
     * Menu条件+分页查询
     * @param menu 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @Override
    public PageInfo<Menu> findPage(Menu menu, int page, int size){
        //分页
        PageHelper.startPage(page,size);
        //搜索条件构建
        Example example = createExample(menu);
        //执行搜索
        return new PageInfo<Menu>(menuMapper.selectByExample(example));
    }

    /**
     * Menu分页查询
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Menu> findPage(int page, int size){
        //静态分页
        PageHelper.startPage(page,size);
        //分页查询
        return new PageInfo<Menu>(menuMapper.selectAll());
    }

    /**
     * Menu条件查询
     * @param menu
     * @return
     */
    @Override
    public List<Menu> findList(Menu menu){
        //构建查询条件
        Example example = createExample(menu);
        //根据构建的条件查询数据
        return menuMapper.selectByExample(example);
    }


    /**
     * Menu构建查询对象
     * @param menu
     * @return
     */
    public Example createExample(Menu menu){
        Example example=new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        if(menu!=null){
            // 
            if(!StringUtils.isEmpty(menu.getId())){
                    criteria.andEqualTo("id",menu.getId());
            }
            // 菜单编码
            if(!StringUtils.isEmpty(menu.getCode())){
                    criteria.andEqualTo("code",menu.getCode());
            }
            // 父菜单ID
            if(!StringUtils.isEmpty(menu.getPId())){
                    criteria.andEqualTo("pId",menu.getPId());
            }
            // 名称
            if(!StringUtils.isEmpty(menu.getMenuName())){
                    criteria.andEqualTo("menuName",menu.getMenuName());
            }
            // 请求地址
            if(!StringUtils.isEmpty(menu.getUrl())){
                    criteria.andEqualTo("url",menu.getUrl());
            }
            // 是否是菜单
            if(!StringUtils.isEmpty(menu.getIsMenu())){
                    criteria.andEqualTo("isMenu",menu.getIsMenu());
            }
            // 菜单层级
            if(!StringUtils.isEmpty(menu.getLevel())){
                    criteria.andEqualTo("level",menu.getLevel());
            }
            // 菜单排序
            if(!StringUtils.isEmpty(menu.getSort())){
                    criteria.andEqualTo("sort",menu.getSort());
            }
            // 
            if(!StringUtils.isEmpty(menu.getStatus())){
                    criteria.andEqualTo("status",menu.getStatus());
            }
            // 
            if(!StringUtils.isEmpty(menu.getIcon())){
                    criteria.andEqualTo("icon",menu.getIcon());
            }
            // 
            if(!StringUtils.isEmpty(menu.getCreateTime())){
                    criteria.andEqualTo("createTime",menu.getCreateTime());
            }
            // 
            if(!StringUtils.isEmpty(menu.getUpdateTime())){
                    criteria.andEqualTo("updateTime",menu.getUpdateTime());
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
        menuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改Menu
     * @param menu
     */
    @Override
    public void update(Menu menu){
        menuMapper.updateByPrimaryKey(menu);
    }

    /**
     * 增加Menu
     * @param menu
     */
    @Override
    public void add(Menu menu){
        menuMapper.insert(menu);
    }

    /**
     * 根据ID查询Menu
     * @param id
     * @return
     */
    @Override
    public Menu findById(String id){
        return  menuMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询Menu全部数据
     * @return
     */
    @Override
    public List<Menu> findAll() {
        return menuMapper.selectAll();
    }
}
