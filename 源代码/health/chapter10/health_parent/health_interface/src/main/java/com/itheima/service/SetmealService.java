package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * 套餐服务接口
 */
public interface SetmealService {
    public void add(Setmeal setmeal, Integer[] checkgroupIds);//新增套餐

    public PageResult findPage(QueryPageBean pageBean);//分页查询套餐

    public Setmeal findById(Integer id);//根据id查询套餐

    public List<Integer> findCheckGroupIdsBySetmealId(Integer setmealId);//根据套餐id查询与之关联的检查组id

    public void edit(Setmeal setmeal, Integer[] checkgroupIds);//编辑套餐

    public void delete(Integer id);//根据id删除套餐

    public Setmeal findSetmealById(Integer id);//根据id查询套餐详情

    public List<Map> getSetmealReport();//查询套餐预约占比情况
    public List<Setmeal> findAll() ;
}
