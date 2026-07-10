package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

/**
 * 体检预约接口
 */
public interface OrderService {
    public Result order(Map map)throws Exception;//体检预约
    public Map findById(Integer id);//根据预约id查询预约信息
}
