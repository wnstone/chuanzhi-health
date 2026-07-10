package com.itheima.dao;

import com.itheima.pojo.Order;

import java.util.List;
import java.util.Map;

/**
 * 体检预约持久层接口
 */
public interface OrderDao {
    public List<Order> findByCondition(Order order);//查询预约记录
    public void add(Order order);//新增预约
    public Map findById4Detail(Integer id);//查询预约信息
    public Integer findOrderCountByDate(String today);//今日预约数
    public Integer findVisitsCountByDate(String today);//今日到诊数
    //本周、本月预约数
    public Integer findOrderCountAfterDate(String thisWeekMonday);
    //本周、本月到诊数
    public Integer findVisitsCountAfterDate(String thisWeekMonday);
    public List<Map> findHotSetmeal();//热门套餐
}
