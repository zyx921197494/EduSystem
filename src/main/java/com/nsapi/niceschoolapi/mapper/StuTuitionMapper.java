package com.nsapi.niceschoolapi.mapper;

import com.nsapi.niceschoolapi.entity.TuitionDB;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StuTuitionMapper {

    //管理员查看所有学生的学费缴纳情况
    public List<TuitionDB> listTuitions();

    //查单个学生的学费缴纳情况
    public TuitionDB getTuition(@Param("sid") Integer sid);

    //添加学生时为该学生添加一条缴费记录
    public Integer addTuition(TuitionDB tuition);

    //学生缴纳学费
    public Integer updateTuition(@Param("sid") Integer sid);

}
