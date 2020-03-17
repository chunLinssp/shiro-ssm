package org.shiro.maven.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Personinf {
    Integer id;
    String post_name;
    Integer dep_id;
    String name;
    String sex;
    String phone;
    String address;
    String id_card;

    @JsonFormat(pattern="YYYY-MM-DD",timezone="GMT+8")
    Date work_start;
    String work_status;
    String image;

    @Override
    public String toString() {
        return "Personinf{" +
                "id=" + id +
                ", post_name='" + post_name + '\'' +
                ", dep_id=" + dep_id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", id_card='" + id_card + '\'' +
                ", work_start='" + work_start + '\'' +
                ", work_status='" + work_status + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost_name() {
        return post_name;
    }

    public void setPost_name(String post_name) {
        this.post_name = post_name;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public Date getWork_start() {
        return work_start;
    }

    public void setWork_start(Date work_start) {
        this.work_start = work_start;
    }

    public String getWork_status() {
        return work_status;
    }

    public void setWork_status(String work_status) {
        this.work_status = work_status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
