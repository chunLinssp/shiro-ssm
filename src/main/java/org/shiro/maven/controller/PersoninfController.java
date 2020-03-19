package org.shiro.maven.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.shiro.maven.annotation.LogAnnotation;
import org.shiro.maven.common.constant.Result;
import org.shiro.maven.common.utli.ResultApiHandler;
import org.shiro.maven.po.*;
import org.shiro.maven.service.*;
import org.shiro.maven.vo.FinancialVo;
import org.shiro.maven.vo.PermissionVo;
import org.shiro.maven.vo.PersoninfVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/personinf")
public class PersoninfController {

    @Autowired
    PersoninfService personinfService;
    @Autowired
    SysService sysService;
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    SysRoleService sysRoleService;
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/findAll")
    @RequiresPermissions("人事档案查询")
    @LogAnnotation(module = "查询",operation = "人事档案查询")
    public String findAll(Model model, @RequestParam(defaultValue = "1",required = true,value="pageNo") Integer pageNo){

        Integer pageSize = 4;
        PageHelper.startPage(pageNo,pageSize);
        List<PersoninfVo> personinfVos = personinfService.findAllPersoninfVo();
        PageInfo<PersoninfVo> pageInfo=new PageInfo<PersoninfVo>(personinfVos);
        List<Department> departments = departmentService.findAll();
        //加载菜单
        PermissionVo permissionVo = loadPermission();
        //加载个人信息
        Personinf personinf = self();
        //加载光亮菜单序号
        int active = findActive(permissionVo);

        // tableVo
        model.addAttribute("personinfVos",personinfVos);
        //管理员vo
        model.addAttribute("personinf",personinf);
        //权限vo
        model.addAttribute("permissionVo",permissionVo);
        model.addAttribute("pageInfo",pageInfo);
        // 部门vo
        model.addAttribute("departments",departments);
        return "personinfTable";

    }

    @RequestMapping("/findAllByPage")
    @RequiresPermissions(value = "人事档案查询")
    @ResponseBody
    public Result findAllByPage( @RequestParam(defaultValue = "1",required = true,value="pageNo") Integer pageNo){
        Integer pageSize = 4;
        PageHelper.startPage(pageNo,pageSize);
        List<PersoninfVo> personinfVos = personinfService.findAllPersoninfVo();
        PageInfo<PersoninfVo> financialVoPageInfo = new PageInfo<PersoninfVo>(personinfVos);
        Result success = ResultApiHandler.success(financialVoPageInfo);
        return success;
    }

    @RequestMapping("/findById")
    public String findById(Model model,  Integer id){
        //查找要修改的信息
        PersoninfVo personinfVo = personinfService.findVoById(id);
        List<Department> departments = departmentService.findAll();
        //加载菜单
        PermissionVo permissionVo = loadPermission();
        //加载个人信息
        Personinf personinf = self();
        //加载光亮菜单序号
        int active = findActive(permissionVo);

        model.addAttribute("departments",departments);
        model.addAttribute("personinfVo",personinfVo);
        model.addAttribute("personinf",personinf);
        model.addAttribute("permissionVo",permissionVo);
        return "personinfDetail";
    }


//    @ResponseBody
//    @RequestMapping("/findById")
//    public Result findById(ModelMap modelMap,  Integer id){
//        PersoninfVo personinf = personinfService.findVoById(id);
//        Result success = ResultApiHandler.success(personinf);
//        return success;
//    }

    @ResponseBody
    @RequestMapping("/deleteOne")
    @RequiresPermissions("人事档案删除")
    public Result deleteOne(Integer id){
        personinfService.delteOne(id);
        Result success = ResultApiHandler.success();
        return success;
    }

    @RequestMapping("/modOne")
    @RequiresPermissions("人事档案修改")
    public String modOne(@RequestBody PersoninfVo personinfVo){
//        personinfService.updateOne(personinf);
        String departmentName = personinfVo.getDepartmentName();
        Department dept = departmentService.findByName(departmentName);
        Integer did = dept.getId();
        personinfVo.setDep_id(did);
        personinfService.updateOne(personinfVo);
        return "personinfTable";
    }

    @ResponseBody
    @RequestMapping("/add")
    @RequiresPermissions("人事档案添加")
    public Result add(@RequestBody PersoninfVo personinfVo){
        List<PersoninfVo> personinfVos = new ArrayList<>();
        Department department = departmentService.findByName(personinfVo.getDepartmentName());
        Integer did = department.getId();
        personinfVo.setDep_id(did);

        personinfVos.add(personinfVo);
        personinfService.saveVo(personinfVos);
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
