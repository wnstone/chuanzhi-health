package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 检查组服务接口实现类
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired   //注入DAO对象
    private CheckGroupDao checkGroupDao;
    //新增检查组  同时需要关联检查项（设置多对多关系）
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);//调用持久层接口
        Integer checkGroupId = checkGroup.getId();//获取检查组id
        //关联检查项
        this.setCheckGroupAndCheckItem(checkGroupId,checkitemIds);
    }
    //设置检查组和检查项多对多关系的方法
    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds){
        //设置多对多关系
        if(checkitemIds != null && checkitemIds.length > 0){
            for (Integer checkitemId : checkitemIds) {//遍历检查项id
                Map<String,Integer> map = new HashMap<>();
                map.put("checkgroupId",checkGroupId);//检查组id
                map.put("checkitemId",checkitemId);//勾选的检查项id
                checkGroupDao.setCheckGroupAndCheckItem(map);//调用持久层接口
            }
        }
    }

    //分页查询
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();//获取当前页面
        Integer pageSize = queryPageBean.getPageSize();//获取每页显示的记录数
        String queryString = queryPageBean.getQueryString();//获取查询条件
        //分页插件，会在执行sql之前将分页关键字追加到SQL后面
        PageHelper.startPage(currentPage,pageSize);
        //调用持久层接口方法
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());//返回分页对象
    }

    //根据id查询检查组
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);//调用持久层接口
    }

    //根据检查组id查询与之关联的检查项id
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer checkgroupId) {
        //调用持久层接口
        return checkGroupDao.findCheckItemIdsByCheckGroupId(checkgroupId);
    }

    //编辑检查组，同时需要设置关联关系
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.edit(checkGroup);//编辑检查组基本信息
        checkGroupDao.deleteAssociation(checkGroup.getId());//删除关联检查项
        //重新建立关联检查项
        this.setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    //根据id删除检查组
    public void delete(Integer id) {
        //调用持久层接口，根据检查组id查询检查组与套餐是否有关联关系
        long count = checkGroupDao.selectCountByCheckGroupId(id);
        if (count > 0){
            //不能删除，已经被套餐表关联
            throw new RuntimeException(MessageConstant.DELETE_CHECKGROUP_FAIL);
        }else{
            checkGroupDao.deleteAssociation(id);//根据检查组id删除关联检查项信息
            checkGroupDao.deleteById(id);//删除检查组
        }
    }

    //查询检查组列表
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();//调用持久层方法
    }
}
