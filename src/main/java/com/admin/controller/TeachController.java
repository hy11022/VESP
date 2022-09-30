package com.admin.controller;

import com.admin.pojo.dto.experiment.UpdateExperimentItemStatusDto;
import com.admin.pojo.dto.teach.*;
import com.admin.pojo.entity.CourseEntity;
import com.admin.pojo.entity.ExperimentItemEntity;
import com.admin.pojo.vo.teach.CourseFilterVo;
import com.admin.pojo.vo.teach.CourseInfoVo;
import com.admin.service.TeachService;
import com.admin.util.CommonUtils;
import com.admin.util.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/teach")
public class TeachController {

    @Autowired
    private TeachService teachService;

    //获取课程列表
    @PostMapping("/getCourseList")
    public Result getCourseList(@Validated @RequestBody CourseFilterDto courseFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseFilterVo> courseList = teachService.getCourseList(courseFilterDto);
        int totalCount = teachService.getCourseListTotalCount(courseFilterDto);
        return Result.showList("00000000", "Success", courseList, totalCount);
    }

    //新增课程
    @PostMapping("/addCourse")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addCourse(@Validated @RequestBody AddCourseDto addCourseDto,BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addCourseDto.setUpdateTime(now);
        boolean isSuccess = teachService.addCourse(addCourseDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除课程
    @PostMapping("/deleteCourse")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteCourse(@Validated @RequestBody DeleteCourseDto deleteCourseDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseID = deleteCourseDto.getId();
        List<CourseEntity> courseList = teachService.getCourseByID(courseID);
        if (courseList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程", null);
        }
        boolean isSuccess = teachService.deleteCourse(deleteCourseDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //更新课程
    @PostMapping("/updateCourse")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateCourse(@Validated @RequestBody UpdateCourseDto updateCourseDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseID = updateCourseDto.getId();
        List<CourseEntity> courseList = teachService.getCourseByID(courseID);
        if (courseList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateCourseDto.setUpdateTime(now);
        boolean isSuccess = teachService.updateCourse(updateCourseDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更新课程状态
    @PostMapping("/updateCourseStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateCourseStatus(@Validated @RequestBody UpdateCourseStatusDto updateCourseStatusDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseID = updateCourseStatusDto.getId();
        List<CourseEntity> courseList = teachService.getCourseByID(courseID);
        if (courseList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateCourseStatusDto.setUpdateTime(now);
        boolean isSuccess = teachService.updateCourseStatus(updateCourseStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //获取课程信息
    @PostMapping("/getCourseInfo")
    public Result getCourseInfo(@Validated @RequestBody CourseInfoDto courseInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseInfoVo> courseInfo = teachService.getCourseInfo(courseInfoDto);
        if (courseInfo.size() < 1) {
            return Result.showInfo("00000002", "查无此课程", null);
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(courseInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }
}