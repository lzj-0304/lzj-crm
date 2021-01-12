package com.lzj.crm.controller;

import java.util.Arrays;
import java.util.Map;

import com.lzj.base.BaseController;
import com.lzj.crm.query.SaleChanceQuery;
import com.lzj.crm.service.UserService;
import com.lzj.crm.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.lzj.crm.entity.SaleChance;
import com.lzj.crm.service.SaleChanceService;
import com.lzj.crm.utils.R;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author lzj
 * @email lzj_0304@163.com
 * @date 2021-01-12 22:43:47
 */
@Controller
@RequestMapping("/sale_chance")
public class SaleChanceController extends BaseController {
    @Autowired
    private SaleChanceService saleChanceService;



    @Resource
    private UserService userService;

    @RequestMapping("index")
    public String index(){
        return "saleChance/sale_chance";
    }

    @GetMapping("list")
    @ResponseBody
    public R querySaleChanceByParams(Integer flag, HttpServletRequest request, SaleChanceQuery saleChanceQuery){
        if(null !=flag && flag==1){
            // 分配给指定用户的机会数据
            saleChanceQuery.setAssignMan(LoginUserUtil.releaseUserIdFromCookie(request));
        }
        return saleChanceService.querySaleChancesByParams(saleChanceQuery);
    }

    @RequestMapping("addOrUpdateSaleChancePage")
    public String addOrUpdateSaleChancePage(Integer id, Model model){
        if(null !=id){
            model.addAttribute("saleChance",saleChanceService.getById(id));
        }
        return "saleChance/add_update";
    }

    @RequestMapping("save")
    @ResponseBody
    public R saveSaleChance(HttpServletRequest request, SaleChance saleChance){
        saleChance.setCreateMan(userService.getById(LoginUserUtil.releaseUserIdFromCookie(request)).getTrueName());
        saleChanceService.saveSaleChance(saleChance);
        return R.ok().put("msg","机会数据添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public R updateSaleChance( SaleChance saleChance){
        saleChanceService.updateSaleChance(saleChance);
        return R.ok().put("msg","机会数据更新成功");
    }

    //101003
    @RequestMapping("delete")
    @ResponseBody
    public R deleteSaleChance(Integer[] ids){
        saleChanceService.deleteSaleChance(ids);
        return R.ok().put("msg","机会数据删除成功!");
    }




}
