package com.lzj.crm.query;


import com.lzj.base.BaseQuery;
import lombok.Data;

@Data
public class SaleChanceQuery extends BaseQuery {
    // 客户名
    private String customerName;
    // 创建人
    private String createMan;
    // 分配状态
    private String state;

    //分配人
    private Integer assignMan;

    // 开发状态
    private Integer devResult;


}
