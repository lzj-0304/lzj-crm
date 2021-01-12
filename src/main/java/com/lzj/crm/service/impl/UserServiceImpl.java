package com.lzj.crm.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzj.crm.dao.UserDao;
import com.lzj.crm.entity.User;
import com.lzj.crm.service.UserService;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {



}