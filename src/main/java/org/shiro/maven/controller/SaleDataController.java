package org.shiro.maven.controller;

import org.shiro.maven.common.constant.Result;
import org.shiro.maven.common.utli.ResultApiHandler;
import org.shiro.maven.po.SalePeople;
import org.shiro.maven.service.SaleService;
import org.shiro.maven.vo.SalePeopleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleDataController {

    @Autowired
    SaleService saleService;

    @ResponseBody
    @RequestMapping("/month")
    public Result saleFormonth(){
        List<SalePeopleVo> top5AsMonth = saleService.findTop5AsMonth();
        Result success = ResultApiHandler.success(top5AsMonth);
        return success;
    }

    @ResponseBody
    @RequestMapping("/weekend")
    public Result saleForweekend(){
        List<SalePeopleVo> top5AsWeekend = saleService.findTop5AsWeekend();
        Result success = ResultApiHandler.success(top5AsWeekend);
        return success;
    }

    @ResponseBody
    @RequestMapping("/yesterday")
    public Result saleForyesterday(){
        List<SalePeopleVo> top5AsYesterday = saleService.findTop5AsYesterday();
        Result success = ResultApiHandler.success(top5AsYesterday);
        return success;
    }




}
