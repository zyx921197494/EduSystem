package com.nsapi.niceschoolapi.service;

import com.nsapi.niceschoolapi.entity.TuitionDB;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuTuitionService {

    //管理员查看所有学生的学费缴纳情况
    public List<TuitionDB> listTuitions();

    //查单个学生的学费缴纳情况
    public TuitionDB getTuition(Integer sid);

    //添加学生时为该学生添加一条缴费记录
    public Integer addTuition(TuitionDB tuition);

    //学生缴纳学费
    public Integer updateTuition(Integer sid);

}
