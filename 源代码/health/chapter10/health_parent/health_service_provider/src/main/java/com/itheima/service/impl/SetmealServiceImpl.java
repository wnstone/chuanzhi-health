package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import redis.clients.jedis.JedisPool;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 套餐服务接口实现类
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired //注入Jedis对象
    private JedisPool jedisPool;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${out_put_path}")
    private String outPutPath;//从属性文件中读取要生成的html对应的目录
    //新增套餐，同时关联检查组
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);//调用持久层接口，新增套餐基本信息
        Integer setmealId = setmeal.getId();//获取套餐id
        //为套餐设置关联检查组
        this.setSetmealAndCheckGroup(setmealId,checkgroupIds);
        //完成所有数据库新增操作后需要将图片名称保存到redis
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());
        this.generateMobileStaticHtml();//生成静态页面
    }

    //设置套餐和检查组多对多关联关系
    public void setSetmealAndCheckGroup(Integer setmealId,
                                        Integer[] checkgroupIds){
        if(checkgroupIds != null && checkgroupIds.length > 0){
            for (Integer checkgroupId : checkgroupIds) {//遍历检查组id
                Map<String,Integer> map = new HashMap<>();
                map.put("setmealId",setmealId);//套餐id
                map.put("checkgroupId",checkgroupId);//勾选的检查组id
                //调用持久层接口，新增套餐与检查组关联信息
                setmealDao.setSetmealAndCheckGroup(map);
            }
        }
    }

    //分页查询
    public PageResult findPage(QueryPageBean pageBean) {
        Integer currentPage = pageBean.getCurrentPage();//当前页
        Integer pageSize = pageBean.getPageSize();       //每页显示记录数
        String queryString = pageBean.getQueryString(); //查询条件
        PageHelper.startPage(currentPage,pageSize);//使用分页助手插件实现分页查询
        //调用持久化接口
        Page<Setmeal> page = setmealDao.findByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());//返回分页对象
    }

    //根据id查询套餐
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);//调用持久层接口
    }

    //根据套餐id查询与之关联的检查组id
    public List<Integer> findCheckGroupIdsBySetmealId(Integer setmealId) {
        return setmealDao.findCheckGroupIdsBySetmealId(setmealId);
    }

    //编辑套餐
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.edit(setmeal);//编辑套餐基本信息
        setmealDao.deleteAssociation(setmeal.getId());//删除套餐与检查组关联关系
        //重新建立套餐关联检查组
        this.setSetmealAndCheckGroup(setmeal.getId(),checkgroupIds);
        this.generateMobileStaticHtml();//生成静态页面
    }

    //根据id删除套餐
    public void delete(Integer id) {
        Setmeal setmeal = setmealDao.findById(id);//查询套餐信息
        //判断套餐信息中的图片是否为空
        if (!setmeal.getImg().equals(null) && setmeal.getImg() != ""){
            //图片不为空，删除七牛云服务器上的图片
            QiniuUtils.deleteFileFromQiniu(setmeal.getImg());
        }
        setmealDao.deleteAssociation(id);//删除关联关系
        setmealDao.deleteById(id);//删除套餐
        this.generateMobileStaticHtml();//生成静态页面
    }

    //生成当前方法所需的静态页面
    public void generateMobileStaticHtml(){
        //在生成静态页面之前需要查询数据
        List<Setmeal> list = setmealDao.findAll();//查询套餐列表数据
        generateMobileSetmealListHtml(list);//需要生成套餐列表静态页面
        generateMobileSetmealDetailHtml(list);//需要生成套餐详情页面
    }
    //生成套餐列表静态页面
    public void generateMobileSetmealListHtml(List<Setmeal> list){
        Map map = new HashMap();
        map.put("setmealList",list);//为模板提供数据，用于生成静态页面
        generteHtml("mobile_setmeal.ftl","m_setmeal.html",map);
    }
    //生成套餐详情静态页面（多个）
    public void generateMobileSetmealDetailHtml(List<Setmeal> setmealList){
        for (Setmeal setmeal : setmealList) {
            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("setmeal",
                    setmealDao.findById4Detail(setmeal.getId()));
            generteHtml("mobile_setmeal_detail.ftl",
                    "setmeal_detail_"+setmeal.getId()+".html",dataMap);
        }
    }
    //通用的方法，用于生成静态页面
    public void generteHtml(String templateName,String htmlPageName,
                            Map map){
        Configuration configuration =
                freeMarkerConfigurer.getConfiguration();//获得配置对象
        Writer out = null;
        try {
            Template template = configuration.getTemplate(templateName);
            //构造输出流
            out = new FileWriter(new File(outPutPath + "/" + htmlPageName));
            //输出文件
            template.process(map,out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //根据id查询套餐详情
    public Setmeal findSetmealById(Integer id) {
        return setmealDao.findById4Detail(id);
    }

    //查询套餐预约占比情况
    public List<Map> getSetmealReport() {
        return setmealDao.getSetmealReport();//调用持久化方法，查询套餐预约占比
    }
    @Override
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }
}
