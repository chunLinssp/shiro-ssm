package org.shiro.maven.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.shiro.maven.common.constant.UserStatus;
import org.shiro.maven.po.RoleTmp;
import org.shiro.maven.po.SysPermission;
import org.shiro.maven.po.SysRole;
import org.shiro.maven.po.SysUser;
import org.shiro.maven.service.SysPermissionService;
import org.shiro.maven.service.SysRoleService;
import org.shiro.maven.service.SysService;
import org.shiro.maven.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by codingBoy on 16/11/20.
 */
public class CustomRealm extends AuthorizingRealm
{
    @Autowired
    SysService sysService;
    @Autowired
    SysPermissionService sysPermissionService;
    @Autowired
    SysRoleService sysRoleService;

    //设置realm的名称
    @Override
    public void setName(String name) {
        super.setName("customRealm");
    }

    //realm的认证方法，从数据库查询用户信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username =(String) token.getPrincipal();
        SysUser sysUser = sysService.findByUserName(username);
        if(sysUser == null){
            throw new UnknownAccountException();//没找到帐号
        }
        if(String.valueOf(UserStatus.getCode("封禁状态")).equals(sysUser.getLocked())){
            throw new LockedAccountException();//没找到帐号
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                username,
                sysUser.getPassword(),
                ByteSource.Util.bytes(sysUser.getSalt()),
                getName()
        );

        return info;
    }

    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        String username  =(String) principals.getPrimaryPrincipal();
        SysUser user = sysService.findByUserName(username);
        if(user == null){
            throw new UnknownAccountException();//没找到帐号
        }
        if(String.valueOf(UserStatus.getCode("封禁状态")).equals(user.getLocked())){
            throw new LockedAccountException();//没找到帐号
        }
        List<RoleTmp> rolesTmp = sysRoleService.findById(user.getId());
        List<Integer> roleIds = new ArrayList<>();
        List<SysPermission> permissions= new ArrayList<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> ps = new HashSet<>();
        Set<String> psIds = new HashSet<>();
        List<String> roleslist = new ArrayList<>();

        if(rolesTmp != null){
            for (RoleTmp roleTmp : rolesTmp) {
                roleIds.add(roleTmp.getSys_role_id());
                Integer sys_role_id = roleTmp.getSys_role_id();
                psIds.add(String.valueOf(sys_role_id));
            }

            permissions = sysPermissionService.findById(roleIds);
            List<String> names = new ArrayList<>();
            for (SysPermission permission : permissions) {
                ps.add(permission.getName());

            }
            List<SysRole> Sysroles = sysRoleService.findByListId(psIds);
            for (SysRole sysrole : Sysroles) {
                String name = sysrole.getName();
                roleslist.add(name);
            }
        }

        info.addRoles(roleslist);
        info.addStringPermissions(ps);
        return info;
    }

    //清除缓存
    public void clearCached() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }



}
