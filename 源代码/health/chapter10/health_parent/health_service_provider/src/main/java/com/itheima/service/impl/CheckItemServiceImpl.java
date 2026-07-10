package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务接口实现类
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired //注入DAO对象
    private CheckItemDao checkItemDao;
    //新增检查项
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);        //调用持久层接口add()
    }

    //分页查询
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();//获取当前页面
        Integer pageSize = queryPageBean.getPageSize();//获取每页显示的记录数
        String queryString = queryPageBean.getQueryString();//获取查询条件
        //分页插件，会在执行sql之前将分页关键字追加到SQL后面
        PageHelper.startPage(currentPage,pageSize);
        //调用持久层接口方法
        Page<CheckItem> page = checkItemDao.findByCondition(queryString);
        //返回分页结果对象
        return new PageResult(page.getTotal(),page.getResult());
    }

    //根据id查询检查项
    public CheckItem findById(Integer id){
        return checkItemDao.findById(id);//调用持久层接口 findById()
    }

    //编辑检查项
    public void edit(CheckItem checkItem){
        checkItemDao.edit(checkItem);    //调用持久层接口 edit()
    }

    //根据id删除检查项
    public void delete(Integer id) {
        //调用持久层接口，根据id查询检查项是否与检查组表关联
        long count = checkItemDao.selectCountByCheckItemId(id);
        if(count > 0){
            //不能删除，已经被检查组表关联
            throw new RuntimeException(MessageConstant.DELETE_CHECKITEM_FAIL);
        }else{
            //没有关联，可以删除，调用持久层接口
            checkItemDao.deleteById(id);
        }
    }

    //查询检查项列表
    public List<CheckItem> findAll() {
        return checkItemDao.findAllCheckItem();//调用持久层接口
    }
}
