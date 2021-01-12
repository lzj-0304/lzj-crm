package com.lzj.crm.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzj.crm.dao.UserDao;
import com.lzj.crm.entity.UserEntity;
import com.lzj.crm.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {



}