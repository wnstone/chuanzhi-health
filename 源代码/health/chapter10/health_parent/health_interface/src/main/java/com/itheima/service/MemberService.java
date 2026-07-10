package com.itheima.service;

import com.itheima.pojo.Member;

import java.util.List;

/**
 * 会员管理接口
 */
public interface MemberService {
    public Member findByTelephone(String telephone);//根据手机号查询会员
    public void add(Member member);//新增会员

    public List<Integer> findMemberCountByMonths(List<String> months);    //获取会员数量统计数据
}
