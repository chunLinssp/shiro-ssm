package org.shiro.maven.po;

import java.util.Date;

public class Produce {

    Integer id;
    Integer peo_id;
    Integer pro_sum;
    Date create_time;

    @Override
    public String toString() {
        return "Produce{" +
                "id=" + id +
                ", peo_id=" + peo_id +
                ", pro_sum=" + pro_sum +
                ", create_time=" + create_time +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(Integer peo_id) {
        this.peo_id = peo_id;
    }

    public Integer getPro_sum() {
        return pro_sum;
    }

    public void setPro_sum(Integer pro_sum) {
        this.pro_sum = pro_sum;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
