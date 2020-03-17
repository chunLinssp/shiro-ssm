package org.shiro.maven.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Trainplan {

    Integer id;
    String content;
    Integer peo_id;
    String state;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date create_time;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date pass_time;

    @Override
    public String toString() {
        return "Trainplan{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", peo_id=" + peo_id +
                ", state='" + state + '\'' +
                ", create_time=" + create_time +
                ", pass_time=" + pass_time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(Integer peo_id) {
        this.peo_id = peo_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getPass_time() {
        return pass_time;
    }

    public void setPass_time(Date pass_time) {
        this.pass_time = pass_time;
    }
}
