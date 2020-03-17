package org.shiro.maven.vo;

import org.shiro.maven.po.SysPermission;

import java.util.List;
import java.util.Map;

public class PermissionVo extends SysPermission {

    Map<String,String> permissionNameAndUrl;
    int active;

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Map<String, String> getPermissionNameAndUrl() {
        return permissionNameAndUrl;
    }

    public void setPermissionNameAndUrl(Map<String, String> permissionNameAndUrl) {
        this.permissionNameAndUrl = permissionNameAndUrl;
    }
}
