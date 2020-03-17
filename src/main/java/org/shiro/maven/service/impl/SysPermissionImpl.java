package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.SysPermissionMapper;
import org.shiro.maven.po.SysPermission;
import org.shiro.maven.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionImpl implements SysPermissionService {

    @Autowired
    SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> findById(List<Integer> id) {
        return sysPermissionMapper.findById(id);
    }



}
