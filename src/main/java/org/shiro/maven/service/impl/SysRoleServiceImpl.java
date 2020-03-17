package org.shiro.maven.service.impl;

import org.shiro.maven.mapper.SysRoleMapper;
import org.shiro.maven.po.RoleTmp;
import org.shiro.maven.po.SysRole;
import org.shiro.maven.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public List<RoleTmp> findById(Integer id) {
        return sysRoleMapper.findById(id);
    }

    @Override
    public List<SysRole> findByListId(Set<String> id) {
        return sysRoleMapper.findByListId(id);
    }
}
