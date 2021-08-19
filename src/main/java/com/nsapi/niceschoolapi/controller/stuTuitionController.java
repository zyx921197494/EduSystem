package com.nsapi.niceschoolapi.controller;

import com.nsapi.niceschoolapi.common.config.MySysUser;
import com.nsapi.niceschoolapi.entity.LayuiResult;
import com.nsapi.niceschoolapi.entity.TuitionDB;
import com.nsapi.niceschoolapi.service.StuTuitionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName stuTuitionController
 * @Description
 * @Author zyx
 * @Date 2021-08-19 9:28
 * @Blog www.winkelblog.top
 */
@Controller
@RequestMapping("tuition")
public class stuTuitionController {

    @Autowired
    private StuTuitionService stuTuitionService;

    //管理员进入学费查询页面
    @RequestMapping("/tuitionPage")
    public String tuitionPage() {
        return "view/student/selTuition";
    }

    //学生进入学费缴纳页面
    @RequestMapping("/stuTuitionPage")
    public String stuTuitionPage() {
        return "view/student/stuTuition";
    }


    //管理员查看所有学生的学费缴纳情况
    @ResponseBody
    @PostMapping("listTuitions")
    public LayuiResult listTuitions() {
        LayuiResult<TuitionDB> result = new LayuiResult<>();
        List<TuitionDB> list = stuTuitionService.listTuitions();
        if (list.isEmpty()) {
            result.setCode(0);
        } else {
            result.setCode(1);
        }
        result.setData(list);
        return result;
    }

    //查单个学生的学费缴纳情况
    @ResponseBody
    @PostMapping("getTuition")
    public TuitionDB getTuition() {
        String stuid = MySysUser.loginName();
        return stuTuitionService.getTuition(Integer.parseInt(stuid));
    }

//    //添加学生时为该学生添加一条缴费记录
//    @ResponseBody
//    @PostMapping("addTuition")
//    public Integer addTuition(@RequestBody TuitionDB tuition) {
//        return stuTuitionService.addTuition(tuition);
//    }

    //学生缴纳学费
    @ResponseBody
    @PostMapping("updateTuition/{sid}")
    public Integer updateTuition(@PathVariable("sid") Integer sid) {
        return stuTuitionService.updateTuition(sid);
    }


}
