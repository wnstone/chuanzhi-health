package com.itheima.dao;

import com.itheima.pojo.Member;

import java.util.Map;

/**
 * 会员数据持久化层接口
 */
public interface MemberDao {
    public Member findByTelephone(String telephone);//根据手机号查询会员信息
    public void add(Member member);//新增会员
    //根据月份查找会员
    public Integer findMemberCountBeforeDate(Map<String,String> map);
    public Integer findMemberCountByDate(String date);//今日新增会员数
    public Integer findMemberTotalCount();//总会员数
    //根据日期统计会员数，统计指定日期之后的会员数
    public Integer findMemberCountAfterDate(String thisWeekMonday);
}
