package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import com.itheima.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 预约设置管理
 */
@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    @Reference //查找服务
    private OrderSettingService orderSettingService;
    //文件上传（Excel）
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            //调用工具类POIUtils返回Excel文件中的数据
            List<String[]> list = POIUtils.readExcel(excelFile);
            List<OrderSetting> data = new ArrayList<>();//预约设置集合
            if(list != null && list.size() > 0){//判断list
                for (String[] strings : list) {//遍历list
                    //调用有参构造方法，为对象orderSetting绑定数据
                    OrderSetting orderSetting = new OrderSetting(new SimpleDateFormat("yyyy/MM/dd")
                            .parse(strings[0]),Integer.parseInt(strings[1]));
                    data.add(orderSetting);//将对象数据添加到data集合中
                }
            }
            orderSettingService.add(data);//调用服务接口，发送请求
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    //根据年月查询对应的预约设置信息
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String month){//2019-7
        try{
            //调用服务接口，发起请求，返回list
            List<Map<String,Object>> list = orderSettingService.getOrderSettingByMonth(month);
            //服务调用成功，返回成功提示信息和list集合
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回失败提示信息
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    //根据日期修改可预约人数
    @RequestMapping("/editNumberByOrderDate")
    public Result editNumberByOrderDate(@RequestBody OrderSetting orderSetting){
        try{
            //调用服务接口，发送请求
            orderSettingService.editNumberByOrderDate(orderSetting);
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
    }
}
