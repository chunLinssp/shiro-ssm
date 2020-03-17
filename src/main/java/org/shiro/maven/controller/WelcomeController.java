package org.shiro.maven.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.shiro.maven.common.constant.Base;
import org.shiro.maven.po.*;
import org.shiro.maven.service.*;
import org.shiro.maven.vo.SalePeopleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    SysService sysService;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    PersoninfService personinfService;
    @Autowired
    SaleService saleService;
    @Autowired
    TrainplanService trainplanService;


    @RequestMapping("/first")
    public void first(){
        System.out.println("ok");
    }

    @RequestMapping("/welcome")
    public String index(HttpServletRequest request, Model model ){

        Subject subject = SecurityUtils.getSubject();
        String activeUser= (String) subject.getPrincipal();
        SysUser sysUser = sysService.findByUserName(activeUser);
        List<RoleTmp> rolesTmp = sysRoleService.findById(sysUser.getId());
        List<Integer> roleIds = new ArrayList<>();
        List<SysPermission> permissions= new ArrayList<>();
        Personinf personinf = new  Personinf();
        if(rolesTmp != null){
            for (RoleTmp roleTmp : rolesTmp) {
                roleIds.add(roleTmp.getSys_role_id());
            }
            permissions = sysPermissionService.findById(roleIds);
            for (SysPermission permission : permissions) {
                System.out.println(permission);
            }
            personinf = personinfService.findById(sysUser.getPeo_id());
        }
        //初始化主界面 数据model
        initModel(model);
        model.addAttribute("personinf",personinf);
        model.addAttribute("permissions",permissions);

        return "welcome";
    }

    // 根据权限,加载不同的角色model
    public void initModel(Model model){
        Subject subject = SecurityUtils.getSubject();
        boolean boos = subject.hasRole("厂长");
        if(boos == true){
            List<SalePeopleVo> top5AsMonth = saleService.findTop5AsMonth();
            //待审批个数
            Integer checkedNumber = trainplanService.WillChecked();
            model.addAttribute("top5AsMonth",top5AsMonth);
            model.addAttribute("checkedNumber",checkedNumber);
        }
    }







}
