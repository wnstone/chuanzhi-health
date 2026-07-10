package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

/**
 * 套餐管理
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference //查找服务
    private SetmealService setmealService;
    @Autowired //注入Jedis对象
    private JedisPool jedisPool;
    //图片上传
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        //获取原始文件名
        String originalFilename = imgFile.getOriginalFilename();
        int lastIndexOf = originalFilename.lastIndexOf(".");
        //获取文件后缀
        String suffix = originalFilename.substring(lastIndexOf - 1);
        //使用UUID随机产生文件名称，防止同名文件覆盖
        String fileName = UUID.randomUUID().toString() + suffix;
        try{
            //将图片保存到七牛云
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            //文件上传成功后，需要将文件名称保存到redis中
           // jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
            return new Result(true,MessageConstant.PIC_UPLOAD_SUCCESS,fileName);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    //新增套餐
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try{
            setmealService.add(setmeal,checkgroupIds);//调用服务add()发送请求
            //调用服务成功，返回新增成功提示信息
            return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //调用服务失败，返回新增失败提示信息
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
    }

    //分页查询套餐
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean pageBean){
        return setmealService.findPage(pageBean);//调用服务 findPage()发送请求
    }

    //根据id查询套餐
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            Setmeal setmeal = setmealService.findById(id);
            //服务调用成功，返回查询结果与查询成功提示信息
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回查询失败提示信息
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    //根据套餐id查询与之关联的检查组id
    @RequestMapping("/findCheckGroupIdsBySetmealId")
    public Result findCheckGroupIdsBySetmealId(Integer setmealId){
        try{
            //调用服务接口，发送请求,返回关联的id列表
            List<Integer> list = setmealService.findCheckGroupIdsBySetmealId(setmealId);
            //服务调用成功，返回查询结果与查询成功提示信息
            return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回查询失败提示信息
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    //编辑套餐
    @RequestMapping("/edit")
    public Result edit(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        try{
            setmealService.edit(setmeal,checkgroupIds);//调用服务edit()发送请求
            //调用服务成功，返回编辑成功提示信息
            return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //调用服务失败，返回编辑失败提示信息
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
    }

    //根据id删除套餐
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            setmealService.delete(id);//调用服务接口，发送请求
            //服务调用成功，返回删除成功提示信息
            return new Result(true,MessageConstant.DELETE_SETMEAL_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回删除失败提示信息
            return new Result(false,MessageConstant.DELETE_SETMEAL_FAIL);
        }
    }
}
