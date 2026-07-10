package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * 检查项数据持久化层接口
 */
public interface CheckItemDao {
    public void add(CheckItem checkItem);//新增检查项

    public Page<CheckItem> findByCondition(String queryString);//分页查询检查项

    public CheckItem findById(Integer id);//根据id查询检查项

    public void edit(CheckItem checkItem);//编辑检查项

    public long selectCountByCheckItemId(Integer checkItemId);//根据id查询检查项的关联关系

    public void deleteById(Integer id);//根据id删除检查项

    public List<CheckItem> findAllCheckItem();//查询检查项列表
}