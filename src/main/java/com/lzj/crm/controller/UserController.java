package com.lzj.crm.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lzj.crm.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lzj.crm.entity.User;
import com.lzj.crm.service.UserService;
import com.lzj.crm.utils.R;



/**
 * 
 *
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 22:00:15
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;



    /**
     * 用户登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public R login(String userName, String userPwd) {
        UserModel userModel = userService.login(userName, userPwd);
        /**
         * 登录成功后
         *    1.将用户登录信息存入session
         *    2.将用户信息返回给客户端 有客户端(cookie)保存
         */
        return R.ok().put("result",userModel);
    }

    @RequestMapping("user/queryAllSales")
    @ResponseBody
    public List<Map<String,Object>> queryAllSales(){
        return userService.queryAllSales();
    }

}
