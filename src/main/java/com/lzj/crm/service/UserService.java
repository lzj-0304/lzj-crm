package com.lzj.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzj.crm.entity.User;
import com.lzj.crm.model.UserModel;


/**
 * 
 *
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 22:00:15
 */
public interface UserService extends IService<User> {
    public UserModel login(String userName, String userPwd);
}

