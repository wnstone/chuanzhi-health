package com.itheima.controller;

import com.itheima.constant.MessageConstant;
import com.itheima.entity.Result;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * 用户管理
 */
@RestController
@RequestMapping("/user")
public class UserController {
    //获取当前登录（认证）用户的用户名
    @RequestMapping("/getLoginUsername")
    public Result getLoginUsername(){
        try{
            //调用Spring Security框架提供的API获取当前用户的username展示到页面
            User user = (User) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();//获取用户名
            String password = user.getPassword();//获取密码
            Collection<GrantedAuthority> authorities =
                    user.getAuthorities();
            return new Result(true,
                    MessageConstant.GET_USERNAME_SUCCESS,username);
        }catch (Exception e){
            return new Result(false, MessageConstant.GET_USERNAME_FAIL);
        }
    }
}