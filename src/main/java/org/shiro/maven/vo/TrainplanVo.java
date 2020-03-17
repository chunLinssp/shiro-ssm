package org.shiro.maven.vo;

import org.shiro.maven.po.Trainplan;

public class TrainplanVo {

    //员工编号
    Integer id;
    String name;
    String post_name;
    String departmentName;
    String state;
    String content;

    @Override
    public String toString() {
        return "TrainplanVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", post_name='" + post_name + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", state='" + state + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
