package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

/**
 * 检查项服务接口
 */
public interface CheckItemService {
    public void add(CheckItem checkItem);//新增检查项服务接口

    public PageResult findPage(QueryPageBean queryPageBean);//分页查询检查项接口

    public CheckItem findById(Integer id); //根据id查询检查项

    public void edit(CheckItem checkItem); //编辑检查项

    public void delete(Integer id);//根据id删除检查项

    public List<CheckItem> findAll();//查询检查项列表
}
