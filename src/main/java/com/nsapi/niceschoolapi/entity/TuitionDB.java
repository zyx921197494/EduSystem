package com.nsapi.niceschoolapi.entity;

import java.io.Serializable;


public class TuitionDB implements Serializable {

    private Integer tid; //记录id
    private Integer sid; //学生编号
    private Integer status; //状态：表示是否已缴纳学费，0未缴纳，1已缴纳

    public TuitionDB(Integer tid, Integer sid, Integer status) {
        this.tid = tid;
        this.sid = sid;
        this.status = status;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
