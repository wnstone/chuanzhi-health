package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * 检查组数据持久化层接口
 */
public interface CheckGroupDao {
    public void add(CheckGroup checkGroup);//添加检查组

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);//设置检查组和检查项多对多关系

    public Page<CheckGroup> findByCondition(String queryString);//分页查询检查组

    public CheckGroup findById(Integer id);//根据id查询检查组

    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId);//根据检查组id查询与之关联的检查项id

    public void edit(CheckGroup checkGroup);//编辑检查组基本信息

    public void deleteAssociation(Integer checkgroupId);//删除检查组和检查项的关联关系（操作中间关系表）

    public long selectCountByCheckGroupId(Integer id);//根据检查组id查询检查组与套餐的关联关系

    public void deleteById(Integer id);//根据id删除检查组

    public List<CheckGroup> findAll();//查询检查组列表
}
