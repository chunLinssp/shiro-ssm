package org.shiro.maven.vo;

import java.math.BigDecimal;

public class SalePeopleVo {

    // 员工id
    Integer id;
    String name;
    String sex;
    String post_name;
    BigDecimal sale_num;


    @Override
    public String toString() {
        return "SalePeopleVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", post_name='" + post_name + '\'' +
                ", sale_num=" + sale_num +
                '}';
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public BigDecimal getSale_num() {
        return sale_num;
    }

    public void setSale_num(BigDecimal sale_num) {
        this.sale_num = sale_num;
    }
}
