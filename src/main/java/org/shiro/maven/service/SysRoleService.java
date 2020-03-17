package org.shiro.maven.service;

import org.shiro.maven.po.RoleTmp;
import org.shiro.maven.po.SysRole;

import java.util.List;
import java.util.Set;

public interface SysRoleService {

    List<RoleTmp> findById(Integer id);

    List<SysRole> findByListId(Set<String> id);
}
