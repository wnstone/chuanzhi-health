package com.itheima.service;

import java.util.Map;

/**
 * 数据统计分析接口
 */
public interface ReportService {
    //获取运营数据
    public Map<String,Object> getBusinessReportData()throws Exception;
}