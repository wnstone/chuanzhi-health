package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * 套餐数据持久化层接口
 */
public interface SetmealDao {
    public void add(Setmeal setmeal);//新增套餐基本信息

    public void setSetmealAndCheckGroup(Map<String, Integer> map);//新增套餐与检查组的关联信息

    public Page<Setmeal> findByCondition(String queryString);//分页查询套餐

    public Setmeal findById(Integer id);//根据id查询套餐

    public List<Integer> findCheckGroupIdsBySetmealId(Integer setmealId);//根据套餐id查询与之关联的检查组id

    public void edit(Setmeal setmeal);//编辑套餐基本信息

    public void deleteAssociation(Integer id);//删除套餐与检查组的关联关系

    public void deleteById(Integer id);//根据id删除套餐

    public List<Setmeal> findAll();    //获取所有套餐信息

    public Setmeal findById4Detail(Integer id);    //查询套餐详情

    public List<Map> getSetmealReport();    //查询套餐预约占比
}
