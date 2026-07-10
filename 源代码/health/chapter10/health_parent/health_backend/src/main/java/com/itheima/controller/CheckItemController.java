package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查项管理
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference //查找服务
    private CheckItemService checkItemService;
    // 新增检查项方法
    @PreAuthorize("hasAuthority('CHECKITEM_ADD')")//权限校验
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);//调用服务接口方法add()，发送请求
            //服务调用成功，返回新增检查项成功提示信息
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回新增检查项失败提示信息
            return new Result(false,MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }

    //分页查询
    @PreAuthorize("hasAuthority('CHECKITEM_QUERY')")//权限校验
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        //调用服务接口 findPage()，返回分页结果封装对象
        return checkItemService.findPage(queryPageBean);
    }

    //根据id查询检查项
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            //调用服务接口findById()，返回检查项实体对象
            CheckItem checkItem = checkItemService.findById(id);
            //调用服务接口成功，返回成功结果信息
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
        }catch (Exception e){
            e.printStackTrace();
            //调用服务接口失败，返回失败结果信息
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    //编辑检查项
    @PreAuthorize("hasAuthority('CHECKITEM_EDIT')")//权限校验
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try {
            checkItemService.edit(checkItem);//调用服务接口edit()
            //调用服务接口成功，返回成功结果信息
            return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //调用服务接口失败，返回失败结果信息
            return  new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    //根据id删除检查项
    @PreAuthorize("hasAuthority('CHECKITEM_DELETE')")//权限校验
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            checkItemService.delete(id);//调用服务接口，发送请求
            //服务调用成功，返回成功提示信息
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        }catch (Exception e){
            String message = e.getMessage();
            e.printStackTrace();
            return new Result(false, message);//服务调用失败，返回失败信息
        }
    }

    //查询检查项列表
    @RequestMapping("/findAll")
    public Result findAll(){
        try {
            List<CheckItem> list = checkItemService.findAll();
            //服务调用成功，返回查询结果与查询成功提示信息
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
        }catch (Exception e){
            e.printStackTrace();
            //服务调用失败，返回查询失败提示信息
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }
}
