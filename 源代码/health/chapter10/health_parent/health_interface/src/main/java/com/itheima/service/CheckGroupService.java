package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

/**
 * 检查组服务接口
 */
public interface CheckGroupService {
    //新增检查组
    public void add(CheckGroup checkGroup, Integer[] checkitemIds);

    public PageResult findPage(QueryPageBean queryPageBean);//分页查询检查组接口

    public CheckGroup findById(Integer id);//根据id查询检查组

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId);//根据检查组id查询与之关联的检查项id

    public void edit(CheckGroup checkGroup, Integer[] checkitemIds);//编辑检查组

    public void delete(Integer id);//根据id删除检查组

    public List<CheckGroup> findAll();//查询检查组列表
}
