package com.lzj.crm.dao;

import com.lzj.crm.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 22:00:15
 */
@Mapper
public interface UserDao extends BaseMapper<User> {



    List<Map<String, Object>> queryAllSales();
}
