package org.shiro.maven.vo;

import org.shiro.maven.po.Personinf;

public class PersoninfVo extends Personinf {

    String departmentName;
    Integer years;


    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }
}
