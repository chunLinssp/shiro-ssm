package org.shiro.maven.common.constant;

public enum UserStatus {

    normal(0,"正常状态"),blocked(1,"封禁状态");

    private Integer code;
    private final String info;

    UserStatus(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


    public String getInfo(){
        return info;
    }
    public static String getMessage(String name) {
        for (UserStatus item : UserStatus.values()) {
            if (item.name().equals(name)) {
                return item.info;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (UserStatus item : UserStatus.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }
}
