package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisMessageConstant;
import com.itheima.entity.Result;
import com.itheima.pojo.Order;
import com.itheima.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/**
 * 体检预约
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;
    //提交体检预约请求
    @RequestMapping("/submitOrder")
    public Result submitOrder(@RequestBody Map map){
        String telephone = (String) map.get("telephone");
        //获取用户页面输入的验证码
        String validateCode = (String) map.get("validateCode");
        //从redis获取保存的验证码
        String codeInRedis = jedisPool.getResource().get(telephone +
                RedisMessageConstant.SENDTYPE_ORDER);
        //校验用户输入的验证码是否正确
        if(codeInRedis == null || !codeInRedis.equals(validateCode)){
            //验证码输入错误
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        //通过dubbo调用服务实现预约逻辑
        Result result = null;
        try{
            map.put("orderType", Order.ORDERTYPE_CLIENT);//预约类型
            result = orderService.order(map);//调用服务
        }catch (Exception e){
            e.printStackTrace();
            return result;
        }
        return result;
    }

    //根据预约id查询预约信息
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Map map = orderService.findById(id);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
