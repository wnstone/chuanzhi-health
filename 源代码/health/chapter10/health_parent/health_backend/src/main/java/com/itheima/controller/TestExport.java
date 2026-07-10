package com.itheima.controller;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试类
 */
public class TestExport {
    //单元测试方法，测试PDF报表导出
    @Test
    public void testJasperReports() throws Exception{
        //获取pdf模板文件绝对磁盘路径
        String jrxmlPath ="D:\\health\\10\\health_parent\\" +
                "health_backend\\src\\main\\webapp\\template\\demo.jrxml";
        String jasperPath="D:\\health\\10\\health_parent\\" +
                "health_backend\\src\\main\\webapp\\template\\demo.jasper";
        //编译模板
        JasperCompileManager.compileReportToFile(jrxmlPath,jasperPath);
        //构造数据
        Map paramters = new HashMap();
        paramters.put("reportDate","2022-03-01");
        paramters.put("company","itcast");
        List<Map> list = new ArrayList();
        Map map1 = new HashMap();
        map1.put("name","小明");
        map1.put("address","beijing");
        map1.put("email","xiaoming@itcast.cn");
        Map map2 = new HashMap();
        map2.put("name","xiaoli");
        map2.put("address","nanjing");
        map2.put("email","xiaoli@itcast.cn");
        list.add(map1);
        list.add(map2);
        //填充数据
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperPath, paramters, new JRBeanCollectionDataSource(list));
        //输出文件
        String pdfPath = "D:\\test.pdf";
        JasperExportManager.exportReportToPdfFile(jasperPrint,pdfPath);
    }
}
