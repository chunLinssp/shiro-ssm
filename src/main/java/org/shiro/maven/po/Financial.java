package org.shiro.maven.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Financial {

    Integer id;
    Integer peo_id;
    BigDecimal std_award;
    BigDecimal real_money;

    @JsonFormat(pattern="YYYY-MM-DD")
    Date get_time;

    @Override
    public String toString() {
        return "Financial{" +
                "id=" + id +
                ", peo_id=" + peo_id +
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

    public Integer getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(Integer peo_id) {
        this.peo_id = peo_id;
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
