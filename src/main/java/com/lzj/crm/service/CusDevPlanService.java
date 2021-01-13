package com.lzj.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.crm.entity.CusDevPlan;
import com.lzj.crm.query.CusDevPlanQuery;
import com.lzj.crm.utils.R;


/**
 * 
 *
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-13 14:22:35
 */
public interface CusDevPlanService extends IService<CusDevPlan> {
    public R queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery);
    public void saveCusDevPlan(CusDevPlan cusDevPlan);
    public void updateCusDevPlan(CusDevPlan cusDevPlan);
    public void deleteCusDevPlan(Integer id);
}

