package com.lzj.crm.dao;

import com.lzj.crm.entity.SaleChance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzj.crm.model.SaleChanceModel;
import com.lzj.crm.query.SaleChanceQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 22:43:47
 */
@Mapper
public interface SaleChanceDao extends BaseMapper<SaleChance> {


    List<SaleChanceModel> selectByParams(SaleChanceQuery saleChanceQuery);
}
