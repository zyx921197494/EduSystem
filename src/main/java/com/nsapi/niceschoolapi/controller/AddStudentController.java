package com.nsapi.niceschoolapi.controller;

import com.nsapi.niceschoolapi.entity.*;
import com.nsapi.niceschoolapi.service.AddStudentService;
import com.nsapi.niceschoolapi.service.StuTuitionService;
import com.nsapi.niceschoolapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AddStudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AddStudentService addStudentService;

    @Autowired
    private StuTuitionService stuTuitionService;

    @RequestMapping("/addStudentPage")
    public String selPolitics(Model model) {
        //  查询政治面貌表
        List<PoliticsTypeDB> stupol = studentService.selPolitics();
        model.addAttribute("stupol", stupol);
        return "view/student/addStudent";
    }

    //  添加学生
    @RequestMapping("addStudent")
    @ResponseBody
    public LayuiResult<StudentDB> addStudent(StudentVO studentVO, String birthday, String tertime) throws Exception {
        LayuiResult<StudentDB> result = new LayuiResult<>();
        //  将接收到的时间进行类型转换
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(birthday);
        Date date2 = format.parse(tertime);
        studentVO.setSbirthday(date1);
        studentVO.setEntertime(date2);
        //  判断该年份是否已存在学生
        Integer year = addStudentService.selectStuYear(studentVO.getClassid());
        if (year != 0) {
            //  若该年份学生为空时 则添加一条分割线
            Integer fenge = addStudentService.stuSegmentation(studentVO.getClassid());
        }
        //  生成学生学号
        String stui = addStudentService.selStuid(studentVO.getGid());
        studentVO.setStuid(stui);
        studentVO.setSid(Integer.valueOf(stui));
        //  根据前台传来信息添加学生
        Integer addStudent = addStudentService.addStudent(studentVO);
        //  获取学号
        String stuid = studentVO.getStuid();
        //  根据学号查询学生id编号
        Integer sid = addStudentService.selectSid(stuid);
        //  获取所选专业id
        Integer mid = studentVO.getMid();
        //  根据学生专业查询该专业开设的必修课程
        List<CourseDB> selCourse = addStudentService.selCourse(mid);
        for ( CourseDB cou : selCourse ) {
            //  将学生id以及必修课程的id添加至学生选课表
            Integer addStuCourse = addStudentService.addStuCourse(sid, cou.getCid());
            System.out.println(addStuCourse);
        }

        //  班级人数+1
        Integer selClassinfo = addStudentService.selecteClassinfo(studentVO.getClassid());
        //  年级人数+1
        Integer selGrade = addStudentService.selecteGrade(studentVO.getGid());
        //  专业人数+1
        Integer selMajor = addStudentService.selecteMajor(studentVO.getMid());
        //  系部人数+1
        Integer selDepartment = addStudentService.selecteDepartment(studentVO.getDid());

        //  根据学号查询信息
        List<StudentDB> stu = addStudentService.selectMessage(stuid);
        //  查询sys_role角色id
        String id = addStudentService.selectRole();
        result.setData(stu);
        result.setMsg(id);
        stuTuitionService.addTuition(new TuitionDB(null, sid, 0));
        return result;

    }
}

