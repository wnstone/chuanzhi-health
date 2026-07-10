package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 预约设置服务接口实现类
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired //注入DAO对象
    private OrderSettingDao orderSettingDao;
    //批量导入预约设置信息
    public void add(List<OrderSetting> list) {
        if (list != null && list.size() > 0) {//判断list
            for (OrderSetting orderSetting : list) {//遍历list
                //判断当前日期是否已经进行了预约设置
                long count = orderSettingDao.
                        findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    //查询当前日期的已预约人数
                    int number = orderSettingDao.
                            findByReservations(orderSetting.getOrderDate());
                    //已预约人数大于最新设置的可预约人数，不能进行预约设置
                    if (number > orderSetting.getNumber()) {
                        throw new RuntimeException
                                (MessageConstant.ORDERSETTING_FAIL);
                    }
                    //更新预约设置
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    //新增预约设置
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }

    //根据年月查询对应的预约设置信息
    public List<Map<String, Object>> getOrderSettingByMonth(String month) {
        String begin = month + "-1";//2019-7-1
        String end = month + "-31";//表示月份是1、3、5、7、8、10、12月
        String[] strings = month.split("-");
        String[] strings1 = {"4","6","9","11"};//表示月份是4、6、9、11月
        if (Integer.parseInt(strings[0]) % 4 != 0 && strings[1].contains("2")){
            end = month + "-28";//不是闰年
        }else if (Integer.parseInt(strings[0]) % 4 == 0 && strings[1].contains("2")){
            end = month + "-29";//闰年
        }else if (Arrays.asList(strings1).contains(strings[1])){
            end = month + "-30";//2019-9-30
        }
        Map<String,String> map = new HashMap<>();//设置查询参数
        map.put("begin",begin);
        map.put("end",end);
        //调用持久层接口，查询预约设置信息，返回OrderSetting类型的list
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map<String, Object>> data = new ArrayList<>();
        //遍历查询结果list
        for (OrderSetting orderSetting : list) {
            Map<String, Object> orderSettingData = new HashMap<>();
            orderSettingData.put("date",orderSetting.getOrderDate().getDate());
            orderSettingData.put("number",orderSetting.getNumber());
            orderSettingData.put("reservations",orderSetting.getReservations());
            data.add(orderSettingData);
        }
        return data;
    }

    //根据日期修改可预约人数
    public void editNumberByOrderDate(OrderSetting orderSetting) {
        //判断当前日期是否已经进行了预约设置
        long count = orderSettingDao.
                findCountByOrderDate(orderSetting.getOrderDate());
        if (count > 0) {
            //查询当前日期下的已预约人数
            int number = orderSettingDao.
                    findByReservations(orderSetting.getOrderDate());
            //已预约人数大于最新设置的可预约人数，不能进行预约设置
            if (number > orderSetting.getNumber()) {
                throw new RuntimeException(MessageConstant.ORDERSETTING_FAIL);
            }
            //更新预约设置
            orderSettingDao.editNumberByOrderDate(orderSetting);
        } else {
            //新增预约设置
            orderSettingDao.add(orderSetting);
        }
    }
}