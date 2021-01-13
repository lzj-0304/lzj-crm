package com.lzj.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lzj.crm.enums.DevResult;
import com.lzj.crm.enums.StateStatus;
import com.lzj.crm.model.SaleChanceModel;
import com.lzj.crm.query.SaleChanceQuery;
import com.lzj.crm.utils.AssertUtil;
import com.lzj.crm.utils.PhoneUtil;
import com.lzj.crm.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzj.crm.dao.SaleChanceDao;
import com.lzj.crm.entity.SaleChance;
import com.lzj.crm.service.SaleChanceService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service("saleChanceService")
public class SaleChanceServiceImpl extends ServiceImpl<SaleChanceDao, SaleChance> implements SaleChanceService {


    @Override
    public R querySaleChancesByParams(SaleChanceQuery saleChanceQuery) {
        R r = R.ok();
        PageHelper.startPage(saleChanceQuery.getPage(),saleChanceQuery.getLimit());
        PageInfo<SaleChanceModel> pageInfo=new PageInfo<SaleChanceModel>(this.baseMapper.selectByParams(saleChanceQuery));
        r.put("code",0);
        r.put("msg","");
        r.put("count",pageInfo.getTotal());
        r.put("data",pageInfo.getList());
        return  r;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveSaleChance(SaleChance saleChance) {
        /**
         * 1.参数校验
         *      customerName  客户名非空
         *      linkMan  非空
         *      linkPhone  非空 11位手机号
         * 2. 设置相关参数默认值
         *       state 默认未分配   如果选择分配人  state 为已分配状态
         *       assignTime 默认空   如果选择分配人  分配时间为系统当前时间
         *       devResult  默认未开发  如果选择分配人 devResult 为开发中 0-未开发  1-开发中 2-开发成功 3-开发失败
         *       isValid  默认有效(1-有效  0-无效)
         *       createDate  updateDate:默认系统当前时间
         * 3.执行添加 判断添加结果
         */
        checkParams(saleChance.getCustomerName(),saleChance.getLinkMan(),saleChance.getLinkPhone());
        saleChance.setState(StateStatus.UNSTATE.getType());
        saleChance.setDevResult(DevResult.UNDEV.getStatus());
        if(StringUtils.isNotBlank(saleChance.getAssignMan())){
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
            saleChance.setAssignTime(new Date());
        }
        saleChance.setIsValid(1);
        saleChance.setCreateDate(new Date());
        saleChance.setUpdateDate(new Date());
        AssertUtil.isTrue(!(this.save(saleChance)),"机会数据添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void updateSaleChance(SaleChance saleChance){
        /**
         * 1.参数校验
         *     id 记录必须存在
         *     customerName  客户名非空
         *     linkMan  非空
         *     linkPhone  非空 11位手机号
         * 2.设置相关参数值
         *     updateDate  系统当前时间
         *       原始记录 未分配 修改后 已分配(分配人是否存在)
         *          state   0--->1
         *          assignTime   设置分配时间 系统时间
         *          devResult  0--->1
         *       原始记录  已分配  修改后  未分配
         *         state 1-->0
         *         assignTime  null
         *         devResult 1-->0
         *  3.执行更新 判断结果
         */
        SaleChance temp = getById(saleChance.getId());
        AssertUtil.isTrue(null==temp,"待更新记录不存在!");
        checkParams(saleChance.getCustomerName(),saleChance.getLinkMan(), saleChance.getLinkPhone());
        saleChance.setUpdateDate(new Date());
        if(StringUtils.isBlank(temp.getAssignMan())&&StringUtils.isNotBlank(saleChance.getAssignMan()) ){
            saleChance.setState(StateStatus.STATED.getType());
            saleChance.setAssignTime(new Date());
            saleChance.setDevResult(DevResult.DEVING.getStatus());
        }else if(StringUtils.isNotBlank(temp.getAssignMan()) && StringUtils.isBlank(saleChance.getAssignMan())){
            saleChance.setState(StateStatus.UNSTATE.getType());
            saleChance.setAssignTime(null);
            saleChance.setDevResult(DevResult.UNDEV.getStatus());
            saleChance.setAssignMan("");
        }

        AssertUtil.isTrue(!(this.updateById(saleChance)),"机会数据更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void deleteSaleChance(Integer[] ids){
        AssertUtil.isTrue(null==ids||ids.length==0,"请选择待删除记录!");
        AssertUtil.isTrue(!(this.removeByIds(Arrays.asList(ids))),"记录删除失败!");
    }

    @Override
    public void updateSaleChanceDevResult(Integer id, Integer devResult) {
        SaleChance temp =this.getById(id);
        AssertUtil.isTrue(null==temp,"待更新记录不存在!");
        temp.setDevResult(devResult);
        AssertUtil.isTrue(!(this.updateById(temp)),"机会数据状态更新失败!");
    }

    private void checkParams(String customerName, String linkMan, String linkPhone) {
        AssertUtil.isTrue(StringUtils.isBlank(customerName),"请输入客户名!");
        AssertUtil.isTrue(StringUtils.isBlank(linkMan),"请输入联系人!");
        AssertUtil.isTrue(StringUtils.isBlank(linkPhone),"请输入手机号!");
        AssertUtil.isTrue(!(PhoneUtil.isMobile(linkPhone)),"手机号格式不合法!");
    }
}