package org.shiro.maven.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;
import org.shiro.maven.common.constant.Result;
import org.shiro.maven.common.utli.ResultApiHandler;
import org.shiro.maven.po.*;
import org.shiro.maven.service.*;
import org.shiro.maven.vo.FinancialVo;
import org.shiro.maven.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/financial")
public class FinancialController {

    @Autowired
    FinancialService financialService;
    @Autowired
    SysService sysService;
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    PersoninfService personinfService;

    @RequestMapping("/findAll")
    @RequiresPermissions(value = "查询所有工资条")
    public String findAll(Model model, @RequestParam(defaultValue = "1",required = true,value="pageNo") Integer pageNo){
        Integer pageSize = 4;
        PageHelper.startPage(pageNo,pageSize);
        List<FinancialVo> allFinancialVo = financialService.findAllFinancialVo();
        PageInfo<FinancialVo> financialVoPageInfo = new PageInfo<>(allFinancialVo);
        //加载菜单
        PermissionVo permissionVo = loadPermission();
        //加载个人信息
        Personinf personinf = self();
        //加载光亮菜单序号
        int active = findActive(permissionVo);
        permissionVo.setActive(active);
        model.addAttribute("financialVoPageInfo",financialVoPageInfo);
        model.addAttribute("financials",allFinancialVo);
        model.addAttribute("permissionVo",permissionVo);
        model.addAttribute("personinf",personinf);
        return "baise-table";
    }

    @RequestMapping("/findAllByPage")
    @RequiresPermissions(value = "查询所有工资条")
    @ResponseBody
    public Result findAllByPage(Model model, @RequestParam(defaultValue = "1",required = true,value="pageNo") Integer pageNo){
        Integer pageSize = 4;
        PageHelper.startPage(pageNo,pageSize);
        List<FinancialVo> allFinancialVo = financialService.findAllFinancialVo();
        PageInfo<FinancialVo> financialVoPageInfo = new PageInfo<>(allFinancialVo);
        model.addAttribute("financialVoPageInfo",financialVoPageInfo);
        Result success = ResultApiHandler.success(financialVoPageInfo);
        return success;
    }

    @ResponseBody
    @RequestMapping(value = "/saveFinancial",method = RequestMethod.POST)
    @RequiresPermissions(value = "工资单添加")
    public Result saveFinancial(@RequestBody  Financial financial){
        List<Financial> financials = new ArrayList<>();
        financials.add(financial);
        financialService.saveFinancial(financials);
        Result success = ResultApiHandler.success();
        return success;
    }

    @PostMapping("/modFinancial")
    @RequiresPermissions(value = "工资单修改")
    @ResponseBody
    public Result modFinancial(@RequestBody Financial financial){
        financialService.updateFinancial(financial);
        Result success = ResultApiHandler.success();
        return success;
    }

    @ResponseBody
    @RequestMapping("/findOneFinancial")
    public Result findOneFinancial(Integer peo_id){
        Financial financial = financialService.findById(peo_id);
        Result success = ResultApiHandler.success(financial);
        return success;
    }

    @ResponseBody
    @RequestMapping("/delFinancial")
    public Result delFinancial(Integer peo_id){
        financialService.deleteById(peo_id);
        Result success = ResultApiHandler.success();
        return success;
    }

    //找到当前菜单
    public int findActive(PermissionVo permissionVo){
        Map<String, String> permissionNameAndUrl = permissionVo.getPermissionNameAndUrl();
        Set<String> strings = permissionNameAndUrl.keySet();
        int count = 0;
        for (String key : strings) {
            if(key.equals("查询所有")){
                permissionVo.setActive(count);
                break;
            }
            count += 1;
        }
        return count;
    }

    //加载个人信息
    public Personinf self(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String)subject.getPrincipal();
        SysUser sysUser = sysService.findByUserName(username);
        Personinf personinf = personinfService.findById(sysUser.getPeo_id());
        return personinf;
    }


    //加载菜单
    public PermissionVo loadPermission(){
        Subject subject = SecurityUtils.getSubject();
        String activeUser =(String) subject.getPrincipal();
        SysUser sysUser = sysService.findByUserName(activeUser);
        List<RoleTmp> rolesTmp = sysRoleService.findById(sysUser.getId());
        List<Integer> roleIds = new ArrayList<>();
        List<SysPermission> permissions= new ArrayList<>();
        PermissionVo permissionVos = new PermissionVo();
        Map<String, String> maps = new HashMap<>();
        if(rolesTmp != null){
            for (RoleTmp roleTmp : rolesTmp) {
                roleIds.add(roleTmp.getSys_role_id());
            }
            permissions = sysPermissionService.findById(roleIds);
            for (SysPermission permission : permissions) {
                String name = permission.getName();
                maps.put(name,permission.getUrl());
            }
            permissionVos.setPermissionNameAndUrl(maps);
            return permissionVos;
        }
        return null;
    }




}
