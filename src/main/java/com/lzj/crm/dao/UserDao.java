package com.lzj.crm.dao;

import com.lzj.crm.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 21:26:41
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
	
}
