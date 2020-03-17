package org.shiro.maven.service;

import org.shiro.maven.po.SysPermission;

import java.util.List;

public interface SysPermissionService {
    List<SysPermission> findById(List<Integer> id);
}
