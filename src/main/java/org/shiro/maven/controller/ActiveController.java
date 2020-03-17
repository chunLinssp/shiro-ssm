package org.shiro.maven.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.shiro.maven.common.constant.Result;
import org.shiro.maven.common.utli.ResultApiHandler;
import org.shiro.maven.po.*;
import org.shiro.maven.service.*;
import org.shiro.maven.vo.PermissionVo;
import org.shiro.maven.vo.PersoninfVo;
import org.shiro.maven.vo.TrainplanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/active")
public class ActiveController {

    @Autowired
    TransferService transferService;
    @Autowired
    TrainplanService trainplanService;
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

    @RequestMapping("addTrainplan")
    @ResponseBody
    @RequiresPermissions("人才培养计划添加")
    public Result addTrainplan(@RequestBody Trainplan trainplan){
        Date date = new Date();
        trainplan.setCreate_time(date);
        trainplan.setState("0");
        trainplanService.saveTrainplan(trainplan);
        Result success = ResultApiHandler.success();
        return success;
    }

    @RequestMapping("deleteTrainplan")
    @ResponseBody
    @RequiresPermissions("人才培养计划删除")
    public Result deleteTrainplan( Integer id){
        trainplanService.deleteOne(id);
        Result success = ResultApiHandler.success();
        return success;
    }

    @RequestMapping("modTrainplan")
    @ResponseBody
    @RequiresPermissions("人才培养计划修改")
    public Result modTrainplan(@RequestBody Trainplan trainplan){
        trainplanService.updateTrainplane(trainplan);
        Result success = ResultApiHandler.success();
        return success;
    }

//    @RequestMapping("checkTrainplan")
//    @ResponseBody
//    @RequiresPermissions("审批人才计划")
//    public Result checkTrainplan(String state,Integer id){
//        trainplanService.checkTrainplane(state,id);
//        Result success = ResultApiHandler.success();
//        return success;
//    }


    @RequestMapping("findAllTrainplan")
    @RequiresPermissions("人才培养计划查询")

    public String findAllTrainplan(Model model, @RequestParam(defaultValue = "1",required = true,value="pageNo") Integer pageNo){

        // 加载管理员信息，菜单信息
        initModel(model);
        Integer pageSize = 4;
        PageHelper.startPage(pageNo,pageSize);
        List<TrainplanVo> trainplanVos = trainplanService.findAll();
        PageInfo<TrainplanVo> trainplanVoPageInfo = new PageInfo<TrainplanVo>(trainplanVos);
        model.addAttribute("trainplanPageInfo",trainplanVoPageInfo);
        return "TrainplanTable";
    }

    @RequestMapping("/findAllTrainplanPage")
    @RequiresPermissions(value = "人才培养计划查询")
    @ResponseBody
    public Result findAllTrainplanPage( @RequestParam(defaultValue = "1",required = true,value="pageNo") Integer pageNo){
        Integer pageSize = 4;
        PageHelper.startPage(pageNo,pageSize);
        List<TrainplanVo> trainplanVos = trainplanService.findAll();
        PageInfo<TrainplanVo> trainplanVoPageInfo = new PageInfo<TrainplanVo>(trainplanVos);
        Result success = ResultApiHandler.success(trainplanVoPageInfo);
        return success;
    }

    @RequestMapping("findTrainplanById")
    @ResponseBody
    @RequiresPermissions("人才培养计划查询")
    public Result findTrainplanById(Integer peo_id){
        TrainplanVo trainplanVo = trainplanService.findVoById(peo_id);
        Result success = ResultApiHandler.success(trainplanVo);
        return success;
    }

    @RequestMapping("checkTrainplan")
    @ResponseBody
    @RequiresRoles({"厂长"})
    public Result checkTrainplan(Integer peo_id,String state){
        trainplanService.checkTrainplane(state,peo_id,new Date());
        Result success = ResultApiHandler.success();
        return success;
    }




    @RequestMapping("modTransfer")
    @ResponseBody
    @RequiresPermissions("人员变更修改")
    public Result modTransfer(@RequestBody Transfer transfer){
        transferService.updateTransfer(transfer);
        Result success = ResultApiHandler.success();
        return success;
    }

    @RequestMapping("checkTransfer")
    @ResponseBody
    @RequiresPermissions("审批人员变更")
    public Result checkTransfer(String state,Integer id){
        transferService.checkTransfer(state,id);
        Result success = ResultApiHandler.success();
        return success;
    }

    @RequestMapping("addTransfer")
    @ResponseBody
    @RequiresPermissions("人员变更添加")
    public Result addTransfer(@RequestBody Transfer transfer){
        transferService.saveTransfer(transfer);
        Result success = ResultApiHandler.success();
        return success;
    }

    @RequestMapping("findAllTransfer")
    @ResponseBody
    @RequiresPermissions("人员变更查询")
    public String findAllTransfer(Model model, @RequestParam(defaultValue = "1",required = true,value="pageNo") Integer pageNo){
        Integer pageSize = 4;
        PageHelper.startPage(pageNo,pageSize);
        List<Transfer> transfers = transferService.findAll();
        PageInfo<Transfer> transferPageInfo = new PageInfo<>(transfers);
        model.addAttribute("transferPageInfo",transferPageInfo);
        return null;
    }

//    @RequestMapping("deleteTransfer")
//    @ResponseBody
//    @RequiresPermissions("人员变更删除")
//    public Result deleteTransfer(Integer id){
//        transferService.deleteOne(id);
//        Result success = ResultApiHandler.success();
//        return success;
//    }


    public void initModel(Model model){
        //加载菜单
        PermissionVo permissionVo = loadPermission();
        //加载个人信息
        Personinf personinf = self();
        //加载光亮菜单序号
        int active = findActive(permissionVo);
        model.addAttribute("personinf",personinf);
        model.addAttribute("permissionVo",permissionVo);
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
