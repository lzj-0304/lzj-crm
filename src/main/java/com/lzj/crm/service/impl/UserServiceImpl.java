package com.lzj.crm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lzj.crm.model.UserModel;
import com.lzj.crm.utils.AssertUtil;
import com.lzj.crm.utils.Md5Util;
import com.lzj.crm.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzj.crm.dao.UserDao;
import com.lzj.crm.entity.User;
import com.lzj.crm.service.UserService;

import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


    @Override
    public UserModel login(String userName,String userPwd){
        /**
         * 1.参数校验
         *    用户名  非空
         *    密码    非空
         * 2.根据用户名  查询用户记录
         * 3.用户存在性校验
         *     不存在   -->记录不存在  方法结束
         * 4.用户存在
         *     校验密码
         *        密码错误 -->密码不正确   方法结束
         * 5.密码正确
         *     用户登录成功  返回用户信息
         */
        checkLoginParams(userName,userPwd);
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("user_name",userName));
        AssertUtil.isTrue(null == user,"用户不存在或已注销!");
        AssertUtil.isTrue(!(user.getUserPwd().equals(Md5Util.encode(userPwd))),"用户密码不正确，请重新输入!");
        return buildUserInfo(user);
    }





    private UserModel buildUserInfo(User user) {
        UserModel userModel=new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(user.getId()));
        userModel.setUserName(user.getUserName());
        userModel.setTrueName(user.getTrueName());
        return userModel;
    }

    private void checkLoginParams(String userName, String userPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(userName),"用户名不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(userPwd),"用户密码不能为空!");
    }

    @Override
    public List<Map<String, Object>> queryAllSales() {
        return this.baseMapper.queryAllSales();
    }
}