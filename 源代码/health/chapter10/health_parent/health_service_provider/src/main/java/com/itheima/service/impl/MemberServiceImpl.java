package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.service.MemberService;
import com.itheima.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 会员服务接口实现类
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberDao memberDao;
    //根据手机号查询会员信息
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }
    //新增会员
    public void add(Member member) {
        //如果用户设置了密码，需要对密码进行md5加密
        String password = member.getPassword();
        if(password != null){
            password = MD5Utils.md5(password);
            member.setPassword(password);
        }
        memberDao.add(member);
    }

    //根据月份查询会员数量
    public List<Integer> findMemberCountByMonths(List<String> months) {
        List<Integer> memberCounts = new ArrayList<>();
        if(months != null && months.size() > 0){
            for (String month : months) {//2019.01
                String beginTime = month + ".1";//每月第一天
                String endTime = month + ".31";//每月最后一天
                String[] strings = month.split("\\.");//分割数据month
                String[] strings1 = {"4","6","9","11","04","06","09"};//30天
                if (Integer.parseInt(strings[0]) % 4 != 0 && (strings[1].equals("02") || strings[1].equals("2"))){
                    endTime = month + ".28";//不是闰年
                }else if (Integer.parseInt(strings[0]) % 4 == 0 && (strings[1].equals("02") || strings[1].equals("2"))){
                    endTime = month + ".29";//闰年
                }else if (Arrays.asList(strings1).contains(strings[1])){
                    endTime = month + ".30";//每月只有30天
                }
                Map<String,String> map = new HashMap<>();//封装查询参数
                map.put("begin",beginTime);
                map.put("end",endTime);
                Integer memberCount = memberDao.findMemberCountBeforeDate(map);
                memberCounts.add(memberCount);
            }
        }
        return memberCounts;
    }
}
