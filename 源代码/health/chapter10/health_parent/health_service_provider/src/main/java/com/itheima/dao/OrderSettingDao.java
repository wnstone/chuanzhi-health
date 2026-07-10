package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *持久层Dao接口
 */
public interface OrderSettingDao {

    public long findCountByOrderDate(Date orderDate); //判断当前日期是否已经进行了预约设置

    public void editNumberByOrderDate(OrderSetting orderSetting);//如果已经设置，执行更新操作

    public void add(OrderSetting orderSetting);//如果没有设置，执行新增操作

    public List<OrderSetting> getOrderSettingByMonth(Map<String, String> map);//根据年月查询对应的预约设置信息

    public OrderSetting findByOrderDate(Date date);//根据日期查询预约设置信息

    public void editReservationsByOrderDate(OrderSetting orderSetting);//更新已预约人数

    public int findByReservations(Date orderDate);//查询指定日期的已预约人数
}