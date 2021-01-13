package com.lzj.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzj.crm.dao.SaleChanceDao;
import com.lzj.crm.query.CusDevPlanQuery;
import com.lzj.crm.utils.AssertUtil;
import com.lzj.crm.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzj.crm.dao.CusDevPlanDao;
import com.lzj.crm.entity.CusDevPlan;
import com.lzj.crm.service.CusDevPlanService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("cusDevPlanService")
public class CusDevPlanServiceImpl extends ServiceImpl<CusDevPlanDao, CusDevPlan> implements CusDevPlanService {

    @Autowired
    private SaleChanceDao saleChanceDao;

    @Override
    public R queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery){
        R r = R.ok();
        PageHelper.startPage(cusDevPlanQuery.getPage(),cusDevPlanQuery.getLimit());
        PageInfo<CusDevPlan> pageInfo=new PageInfo<CusDevPlan>(this.baseMapper.selectByParams(cusDevPlanQuery));
        r.put("code",0);
        r.put("msg","");
        r.put("count",pageInfo.getTotal());
        r.put("data",pageInfo.getList());
        return  r;
    }


    @Override
    public void saveCusDevPlan(CusDevPlan cusDevPlan){
        /**
         * 1.参数校验
         *    机会id 非空 记录必须存在
         *    计划项内容非空
         *    计划项时间非空
         * 2. 参数默认值
         *    is_valid  1
         *    createDate 系统时间
         *    updateDate  系统时间
         * 3.执行添加 判断结果
         */
        checkParams(cusDevPlan.getSaleChanceId(),cusDevPlan.getPlanItem(),cusDevPlan.getPlanDate());
        cusDevPlan.setIsValid(1);
        cusDevPlan.setCreateDate(new Date());
        cusDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(!(this.save(cusDevPlan)),"计划项记录添加失败!");
    }

    private void checkParams(Integer saleChanceId, String planItem, Date planDate) {
        AssertUtil.isTrue(null==saleChanceId||null==saleChanceDao.selectById(saleChanceId),"请设置营销机会id");
        AssertUtil.isTrue(StringUtils.isBlank(planItem),"请输入计划项内容!");
        AssertUtil.isTrue(null==planDate,"请指定计划项日期!");
    }


    public void updateCusDevPlan(CusDevPlan cusDevPlan){
        /**
         * 1.参数校验
         *    id 记录必须存在
         *    机会id 非空 记录必须存在
         *    计划项内容非空
         *    计划项时间非空
         * 2. 参数默认值
         *    updateDate  系统时间
         * 3.执行更新 判断结果
         */
        AssertUtil.isTrue(null==cusDevPlan.getId() || null==this.getById(cusDevPlan.getId()),"待更新的记录不存在!");
        checkParams(cusDevPlan.getSaleChanceId(),cusDevPlan.getPlanItem(),cusDevPlan.getPlanDate());
        cusDevPlan.setUpdateDate(new Date());
        AssertUtil.isTrue(!(this.updateById(cusDevPlan)),"记录更新失败!");
    }


    public void deleteCusDevPlan(Integer id){
        CusDevPlan cusDevPlan= this.getById(id);
        AssertUtil.isTrue( null==cusDevPlan,"待删除的记录不存在!");
        cusDevPlan.setIsValid(0);
        AssertUtil.isTrue(!(this.updateById(cusDevPlan)),"记录删除失败!");
    }


}