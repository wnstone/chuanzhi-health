package com.itheima.security;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.pojo.Permission;
import com.itheima.pojo.Role;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * SpringSecurity控制器
 */
@RestController
@Component
public class SpringSecurityUserService implements UserDetailsService {
    @Reference //查找服务，实现查询数据库
    private UserService userService;
    //根据用户名查询数据库中用户信息
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        //调用用户服务（底层基于Dubbo实现远程调用）获取用户信息
        User user = userService.findByUsername(username);
        if(user == null){
            return null;
        }
        //用于封装当前用户的角色和权限
        List<GrantedAuthority> list = new ArrayList<>();
        Set<Role> roles = user.getRoles();//查询当前用户对应的角色和权限
        if(roles != null && roles.size() > 0){
            for (Role role : roles) {
                String keyword = role.getKeyword();//角色关键字，是角色的标识
                //为当前用户授予角色
                list.add(new SimpleGrantedAuthority(keyword));
                //遍历当前角色对应的权限
                for (Permission permission : role.getPermissions()) {
                    //权限关键字
                    String permissionKeyword = permission.getKeyword();
                    //为当前用户授权
                    list.add(new SimpleGrantedAuthority(permissionKeyword));
                }
            }
        }
        return new org.springframework.security.core.userdetails.
                User(username,user.getPassword(),list);
    }
}
