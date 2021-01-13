package com.lzj.crm.controller;

import com.lzj.base.BaseController;
import com.lzj.crm.entity.CusDevPlan;
import com.lzj.crm.query.CusDevPlanQuery;
import com.lzj.crm.service.SaleChanceService;
import com.lzj.crm.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.lzj.crm.service.CusDevPlanService;

import javax.annotation.Resource;
import java.util.Map;


/**
 *
 * @author lzj
 * @email lzj_0304@163.com
 */
@RequestMapping("/cus_dev_plan")
@Controller
public class CusDevPlanController extends BaseController {
    @Autowired
    private CusDevPlanService cusDevPlanService;

    @Resource
    private SaleChanceService saleChanceService;

    @RequestMapping("index")
    public String index(){
        return "cusDevPlan/cus_dev_plan";
    }


    @RequestMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid, Model model){
        model.addAttribute("saleChance",saleChanceService.getById(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery){
        return cusDevPlanService.queryCusDevPlansByParams(cusDevPlanQuery);
    }

    @RequestMapping("save")
    @ResponseBody
    public R saveCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.saveCusDevPlan(cusDevPlan);
        return R.ok().put("msg","计划项数据添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public R updateCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return R.ok().put("msg","计划项数据更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public R deleteCusDevPlan(Integer id){
        cusDevPlanService.deleteCusDevPlan(id);
        return R.ok().put("msg","计划项数据删除成功");
    }


    @RequestMapping("addOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sid,Integer id,Model model){
        model.addAttribute("sid",sid);
        model.addAttribute("cusDevPlan",cusDevPlanService.getById(id));
        return "cusDevPlan/add_update";
    }

}
