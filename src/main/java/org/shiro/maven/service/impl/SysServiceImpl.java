package org.shiro.maven.service.impl;


import org.shiro.maven.mapper.SysUserMapper;
import org.shiro.maven.po.SysUser;
import org.shiro.maven.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by codingBoy on 16/11/20.
 * 认证和授权的服务接口
 */
@Service
public class SysServiceImpl implements SysService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUserName(String username) {
        SysUser sysUser = sysUserMapper.findByUserName(username);
        return sysUser;
    }
}
