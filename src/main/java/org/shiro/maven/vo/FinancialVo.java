package org.shiro.maven.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class FinancialVo {

    // 员工编号
    Integer id;
    String post_name;
    BigDecimal std_award;
    BigDecimal real_money;
    @JsonFormat(pattern="yyyy-MM-dd")
    Date get_time;


    @Override
    public String toString() {
        return "FinancialVo{" +
                "id=" + id +
                ", post_name='" + post_name + '\'' +
                ", std_award=" + std_award +
                ", real_money=" + real_money +
                ", get_time=" + get_time +
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

    public BigDecimal getStd_award() {
        return std_award;
    }

    public void setStd_award(BigDecimal std_award) {
        this.std_award = std_award;
    }

    public BigDecimal getReal_money() {
        return real_money;
    }

    public void setReal_money(BigDecimal real_money) {
        this.real_money = real_money;
    }

    public Date getGet_time() {
        return get_time;
    }

    public void setGet_time(Date get_time) {
        this.get_time = get_time;
    }
}
