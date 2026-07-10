package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * 预约设置服务接口
 */
public interface OrderSettingService {

    public void add(List<OrderSetting> data);//文件上传（Excel）

    public List<Map<String, Object>> getOrderSettingByMonth(String month);//根据年月查询对应的预约设置信息

    public void editNumberByOrderDate(OrderSetting orderSetting);//根据日期修改可预约人数
}
