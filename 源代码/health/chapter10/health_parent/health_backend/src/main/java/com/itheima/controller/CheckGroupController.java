package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查组管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference //查找服务
    private CheckGroupService checkGroupService;
    //新增检查组
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        try{
            checkGroupService.add(checkGroup,checkitemIds);
            //调用服务成功，返回新增成功提示信息
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //调用服务失败，返回新增失败提示信息
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }

    //分页查询检查组
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean pageBean){
        return checkGroupService.findPage(pageBean);//调用findPage()发送请求
    }

    //根据id查询检查组
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            //调用服务接口 findById()，发送请求
            CheckGroup checkGroup = checkGroupService.findById(id);
            //服务调用成功，返回查询结果与查询成功提示信息
            return new Result(true,
                    MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回查询失败提示信息
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    //根据检查组id查询与之关联的检查项id
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(Integer checkgroupId){
        try{
            //调用服务接口，发送请求,返回关联的id列表
            List<Integer> list = checkGroupService.findCheckItemIdsByCheckGroupId(checkgroupId);
            //服务调用成功，返回查询结果与查询成功提示信息
            return new Result(true,
                    MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回查询失败提示信息
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //编辑检查组
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        try{
            //调用服务接口 edit()，发送请求
            checkGroupService.edit(checkGroup,checkitemIds);
            //调用服务成功，返回编辑成功提示信息
            return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //调用服务失败，返回编辑失败提示信息
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
    }

    //根据id删除检查组
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            checkGroupService.delete(id);//调用服务接口，发送请求
            //服务调用成功，返回删除成功提示信息
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回删除失败提示信息
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    //查询检查组列表
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            //调用服务接口findAll()，发送请求
            List<CheckGroup> list = checkGroupService.findAll();
            //调用服务成功，返回检查组列表list与查询成功提示信息
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //调用服务失败，返回查询失败提示信息
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
