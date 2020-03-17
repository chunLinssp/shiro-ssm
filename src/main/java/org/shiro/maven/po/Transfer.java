package org.shiro.maven.po;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Transfer {

    Integer peo_id;
    String result;
    Integer goal_depid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date effdate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    Date applydate;
    String status;


    @Override
    public String toString() {
        return "Transfer{" +
                "peo_id=" + peo_id +
                ", result='" + result + '\'' +
                ", goal_depid=" + goal_depid +
                ", effdate=" + effdate +
                ", applydate=" + applydate +
                ", status='" + status + '\'' +
                '}';
    }

    public Integer getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(Integer peo_id) {
        this.peo_id = peo_id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Integer getGoal_depid() {
        return goal_depid;
    }

    public void setGoal_depid(Integer goal_depid) {
        this.goal_depid = goal_depid;
    }

    public Date getEffdate() {
        return effdate;
    }

    public void setEffdate(Date effdate) {
        this.effdate = effdate;
    }

    public Date getApplydate() {
        return applydate;
    }

    public void setApplydate(Date applydate) {
        this.applydate = applydate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
