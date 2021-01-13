package com.lzj.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.crm.entity.SaleChance;
import com.lzj.crm.query.SaleChanceQuery;
import com.lzj.crm.utils.R;

import java.util.Map;


/**
 * 
 *
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 22:43:47
 */
public interface SaleChanceService extends IService<SaleChance> {
    public R querySaleChancesByParams(SaleChanceQuery saleChanceQuery);

    void saveSaleChance(SaleChance saleChance);

    void updateSaleChance(SaleChance saleChance);

    void deleteSaleChance(Integer[] ids);

    void updateSaleChanceDevResult(Integer id, Integer devResult);
}

