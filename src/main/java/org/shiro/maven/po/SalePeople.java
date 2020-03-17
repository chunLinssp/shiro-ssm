package org.shiro.maven.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class SalePeople {

    Integer id;
    Integer peo_id;
    BigDecimal sale_sum;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date create_time;

    @Override
    public String toString() {
        return "SalePeople{" +
                "id=" + id +
                ", peo_id=" + peo_id +
                ", sale_sum=" + sale_sum +
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

    public BigDecimal getSale_sum() {
        return sale_sum;
    }

    public void setSale_sum(BigDecimal sale_sum) {
        this.sale_sum = sale_sum;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
