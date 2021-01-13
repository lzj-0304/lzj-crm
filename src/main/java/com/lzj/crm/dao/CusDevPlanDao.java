package com.lzj.crm.dao;

import com.lzj.crm.entity.CusDevPlan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzj.crm.query.CusDevPlanQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-13 14:22:35
 */
@Mapper
public interface CusDevPlanDao extends BaseMapper<CusDevPlan> {



    List<CusDevPlan> selectByParams(CusDevPlanQuery cusDevPlanQuery);
}
