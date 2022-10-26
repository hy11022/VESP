package com.admin.controller;

import com.admin.pojo.dto.config.ClassCourseFilterDto;
import com.admin.pojo.dto.task.AddTaskChildDto;
import com.admin.pojo.dto.task.AddTaskDto;
import com.admin.pojo.dto.task.UserForTaskDto;
import com.admin.pojo.entity.*;
import com.admin.pojo.vo.config.ClassCourseFilterVo;
import com.admin.pojo.vo.teach.*;
import com.admin.service.*;
import com.admin.util.JWTUtils;
import com.alibaba.fastjson.JSONArray;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSONObject;
import com.admin.util.CommonUtils;
import com.admin.pojo.dto.teach.*;
import com.admin.util.Result;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.List;

@RestController
@RequestMapping("/teach")
public class TeachController {

    @Autowired
    private TeachService teachService;

    @Autowired
    private ExperimentService experimentService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ConfigService configService;

    @Autowired
    private UserService userService;

    //获取课程列表
    @PostMapping("/getCourseList")
    public Result getCourseList(@Validated @RequestBody CourseFilterDto courseFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseFilterVo> courseList = teachService.getCourseList(courseFilterDto);
        for (CourseFilterVo courseFilterVo : courseList) {
            JSONArray ja = new JSONArray();
            String labelIDs = courseFilterVo.getLabelIDs();
            List<LabelEntity> labelList = experimentService.getLabelList(labelIDs);
            ja.addAll(labelList);
            courseFilterVo.setLabelList(ja);
        }
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
        List<CourseEntity> courseList = teachService.checkCourseByDto(addCourseDto);
        if (courseList.size() >0) {
            return Result.showInfo("00000002", "此课程已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addCourseDto.setCreateTime(now);
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
        for (CourseInfoVo courseInfoVo : courseInfo) {
            JSONArray ja = new JSONArray();
            String labelIDs = courseInfoVo.getLabelIDs();
            List<LabelEntity> labelList = experimentService.getLabelList(labelIDs);
            ja.addAll(labelList);
            courseInfoVo.setLabelList(ja);
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(courseInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }

    //获取学期列表
    @PostMapping("/getTermList")
    public Result getTermList(@Validated @RequestBody TermFilterDto termFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<TermFilterVo> termList = teachService.getTermList(termFilterDto);
        int totalCount = teachService.getTermListTotalCount(termFilterDto);
        return Result.showList("00000000", "Success", termList, totalCount);
    }

    //新增学期
    @PostMapping("/addTerm")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addTerm(@Validated @RequestBody AddTermDto addTermDto,BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<TermEntity> termList = teachService.checkTermByDto(addTermDto);
        if (termList.size() >0) {
            return Result.showInfo("00000002", "学期名称已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addTermDto.setCreateTime(now);
        if(addTermDto.getStatus().equals("1")){
            teachService.updateAllTermStatus(now);//所有学期禁用，状态修改为2
        }
        boolean isSuccess = teachService.addTerm(addTermDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除学期
    @PostMapping("/deleteTerm")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteTerm(@Validated @RequestBody DeleteTermDto deleteTermDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int termID = deleteTermDto.getId();
        List<TermEntity> termList = teachService.getTermByID(termID);
        if (termList.size() < 1) {
            return Result.showInfo("00000002", "查无此学期", null);
        }
        boolean isSuccess = teachService.deleteTerm(deleteTermDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //更新学期
    @PostMapping("/updateTerm")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateTerm(@Validated @RequestBody UpdateTermDto updateTermDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int termID = updateTermDto.getId();
        List<TermEntity> termList = teachService.getTermByID(termID);
        if (termList.size() < 1) {
            return Result.showInfo("00000002", "查无此学期", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateTermDto.setUpdateTime(now);
        boolean isSuccess = teachService.updateTerm(updateTermDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更新学期状态
    @PostMapping("/updateTermStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateTermStatus(@Validated @RequestBody UpdateTermStatusDto updateTermStatusDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int termID = updateTermStatusDto.getId();
        List<TermEntity> termList = teachService.getTermByID(termID);
        if (termList.size() < 1) {
            return Result.showInfo("00000002", "查无此学期", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateTermStatusDto.setUpdateTime(now);
        if(updateTermStatusDto.getStatus().equals("1")){
            teachService.updateAllTermStatus(now);//所有学期禁用，状态修改为2
        }
        boolean isSuccess = teachService.updateTermStatus(updateTermStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //获取课程资源列表
    @PostMapping("/getCourseResourseList")
    public Result getCourseResourseList(@Validated @RequestBody CourseResourseFilterDto courseResourseFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseResourseFilterVo> courseResourseList = teachService.getCourseResourseList(courseResourseFilterDto);
        int totalCount = teachService.getCourseResourseListTotalCount(courseResourseFilterDto);
        return Result.showList("00000000", "Success", courseResourseList, totalCount);
    }

    //新增课程资源
    @PostMapping("/addCourseResourse")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addCourseResourse(@Validated @RequestBody AddCourseResourseDto addCourseResourseDto,BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addCourseResourseDto.setCreateTime(now);
        boolean isSuccess = teachService.addCourseResourse(addCourseResourseDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除课程资源
    @PostMapping("/deleteCourseResourse")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteCourseResourse(@Validated @RequestBody DeleteCourseResourseDto deleteCourseResourseDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseResourseID = deleteCourseResourseDto.getId();
        List<CourseResourseEntity> CourseResourseList = teachService.getCourseResourseByID(courseResourseID);
        if (CourseResourseList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程资源", null);
        }
        boolean isSuccess = teachService.deleteCourseResourse(deleteCourseResourseDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //更新学期
    @PostMapping("/updateCourseResourse")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateCourseResourse(@Validated @RequestBody UpdateCourseResourseDto updateCourseResourseDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseResourseID = updateCourseResourseDto.getId();
        List<CourseResourseEntity> courseResourseList = teachService.getCourseResourseByID(courseResourseID);
        if (courseResourseList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程资源", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateCourseResourseDto.setUpdateTime(now);
        boolean isSuccess = teachService.updateCourseResourse(updateCourseResourseDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更新课程资源状态
    @PostMapping("/updateCourseResourseStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateCourseResourseStatus(@Validated @RequestBody UpdateCourseResourseStatusDto updateCourseResourseStatusDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseResourseID = updateCourseResourseStatusDto.getId();
        List<CourseResourseEntity> CourseResourseList = teachService.getCourseResourseByID(courseResourseID);
        if (CourseResourseList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程资源", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateCourseResourseStatusDto.setUpdateTime(now);
        boolean isSuccess = teachService.updateCourseResourseStatus(updateCourseResourseStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //获取课程题目列表
    @PostMapping("/getCourseQuestionList")
    public Result getCourseQuestionList(@Validated @RequestBody CourseQuestionFilterDto courseQuestionFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseQuestionFilterVo> courseQuestionList = teachService.getCourseQuestionList(courseQuestionFilterDto);
        int totalCount = teachService.getCourseQuestionListTotalCount(courseQuestionFilterDto);
        return Result.showList("00000000", "Success", courseQuestionList, totalCount);
    }

    //新增课程题目
    @PostMapping("/addCourseQuestion")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addCourseQuestion(@Validated @RequestBody AddCourseQuestionDto addCourseQuestionDto,BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addCourseQuestionDto.setCreateTime(now);
        boolean isSuccess = teachService.addCourseQuestion(addCourseQuestionDto);
        if (!isSuccess) {
            return Result.showInfo("00000002", "新增失败", null);
        }
        if(!addCourseQuestionDto.getType().equals("6")){
            List<CourseQuestionEntity> courseQuestionList= teachService.getLastCourseQuestion();
            if (courseQuestionList.size()<1) {
                return Result.showInfo("00000003", "新增失败", null);
            }
            int courseQuestionID = courseQuestionList.get(0).getId();
            String selectList = addCourseQuestionDto.getSelectList();
            JSONArray ja = JSONArray.parseArray(selectList);
            for(int i = 0;i<ja.size();i++){
                JSONObject jo = JSONObject.parseObject(ja.getString(i));
                AddQuestionSelectDto addQuestionSelectDto = new AddQuestionSelectDto();
                addQuestionSelectDto.setQuestionID(courseQuestionID);
                addQuestionSelectDto.setContent(jo.getString("content"));
                addQuestionSelectDto.setIsCorrect(jo.getString("isCorrect"));
                isSuccess = teachService.addQuestionSelects(addQuestionSelectDto);
            }
        }
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "新增失败", null);
        }
    }

    //删除课程题目
    @PostMapping("/deleteCourseQuestion")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteCourseQuestion(@Validated @RequestBody DeleteCourseQuestionDto deleteCourseQuestionDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseQuestionID = deleteCourseQuestionDto.getId();
        List<CourseQuestionEntity> courseQuestionList = teachService.getCourseQuestionByID(courseQuestionID);
        if (courseQuestionList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程题目", null);
        }
        boolean isSuccess = teachService.deleteCourseQuestion(deleteCourseQuestionDto);
        if(!courseQuestionList.get(0).getType().equals("6")){
            teachService.deleteQuestionSelects(courseQuestionID);
        }
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "删除失败", null);
        }
    }

    //更新课程题目
    @PostMapping("/updateCourseQuestion")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateCourseQuestion(@Validated @RequestBody UpdateCourseQuestionDto updateCourseQuestionDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseQuestionID = updateCourseQuestionDto.getId();
        List<CourseQuestionEntity> courseQuestionList = teachService.getCourseQuestionByID(courseQuestionID);
        if (courseQuestionList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程题目", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateCourseQuestionDto.setUpdateTime(now);
        boolean isSuccess = teachService.updateCourseQuestion(updateCourseQuestionDto);
        if (!isSuccess) {
            return Result.showInfo("00000003", "更新失败", null);
        }
        if(!updateCourseQuestionDto.getType().equals("6")){
            isSuccess = teachService.deleteQuestionSelects(courseQuestionID);
            if (!isSuccess) {
                return Result.showInfo("00000004", "更新失败", null);
            }
            String selectList = updateCourseQuestionDto.getSelectList();
            System.out.println("selectList:"+selectList);
            JSONArray ja = JSONArray.parseArray(selectList);
            for(int i = 0;i<ja.size();i++){
                JSONObject jo = JSONObject.parseObject(ja.getString(i));
                AddQuestionSelectDto addQuestionSelectDto = new AddQuestionSelectDto();
                addQuestionSelectDto.setQuestionID(courseQuestionID);
                addQuestionSelectDto.setContent(jo.getString("content"));
                addQuestionSelectDto.setIsCorrect(jo.getString("isCorrect"));
                isSuccess = teachService.addQuestionSelects(addQuestionSelectDto);
            }
        }
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000005", "更新失败", null);
        }
    }

    //更新课程题目状态
    @PostMapping("/updateCourseQuestionStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateCourseQuestionStatus(@Validated @RequestBody UpdateCourseQuestionStatusDto updateCourseQuestionStatusDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseQuestionID = updateCourseQuestionStatusDto.getId();
        List<CourseQuestionEntity> courseQuestionList = teachService.getCourseQuestionByID(courseQuestionID);
        if (courseQuestionList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程题目", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateCourseQuestionStatusDto.setUpdateTime(now);
        boolean isSuccess = teachService.updateCourseQuestionStatus(updateCourseQuestionStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //获取课程题目信息
    @PostMapping("/getCourseQuestionInfo")
    public Result getCourseQuestionInfo(@Validated @RequestBody CourseQuestionInfoDto courseQuestionInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseQuestionInfoVo> courseQuestionInfo = teachService.getCourseQuestionInfo(courseQuestionInfoDto);
        if (courseQuestionInfo.size() < 1) {
            return Result.showInfo("00000002", "查无此课程题目", null);
        }
        if(!courseQuestionInfo.get(0).getType().equals("6")){
            for (CourseQuestionInfoVo courseQuestionInfoVo : courseQuestionInfo) {
                JSONArray ja = new JSONArray();
                int courseQuestionID = courseQuestionInfoDto.getId();
                List<QuestionSelectsEntity> questionSelectsList = teachService.getQuestionSelects(courseQuestionID);
                ja.addAll(questionSelectsList);
                courseQuestionInfoVo.setSelectList(ja);
            }
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(courseQuestionInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }

    //获取课程题目复制信息
    @PostMapping("/getCourseQuestionCopyInfo")
    public Result getCourseQuestionCopyInfo(@Validated @RequestBody CourseQuestionCopyInfoDto courseQuestionCopyInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseQuestionCopyInfoVo> courseQuestionCopyInfo = teachService.getCourseQuestionCopyInfo(courseQuestionCopyInfoDto);
        if (courseQuestionCopyInfo.size() < 1) {
            return Result.showInfo("00000002", "查无此课程题目复制信息", null);
        }
        if(!courseQuestionCopyInfo.get(0).getType().equals("6")){
            for (CourseQuestionCopyInfoVo courseQuestionCopyInfoVo : courseQuestionCopyInfo) {
                JSONArray ja = new JSONArray();
                int courseQuestionCopyID = courseQuestionCopyInfoDto.getId();
                List<QuestionSelectsCopyVo> questionSelectsCopyList = teachService.getQuestionSelectsCopy(courseQuestionCopyID);
                if(courseQuestionCopyInfoDto.getOp().equals("make")){
                    questionSelectsCopyList.get(0).setIsCorrect(null);
                }
                ja.addAll(questionSelectsCopyList);
                courseQuestionCopyInfoVo.setSelectList(ja);
            }
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(courseQuestionCopyInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }

    //获取课程测评列表
    @PostMapping("/getCourseContentList")
    public Result getCourseContentList(@Validated @RequestBody CourseContentFilterDto courseContentFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseContentFilterVo> courseContentList = teachService.getCourseContentList(courseContentFilterDto);
        int totalCount = teachService.getCourseContentListTotalCount(courseContentFilterDto);
        return Result.showList("00000000", "Success", courseContentList, totalCount);
    }

    //新增课程测评
    @PostMapping("/addCourseContent")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addCourseContent(@Validated @RequestBody AddCourseContentDto addCourseContentDto, @RequestHeader Map headers, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        JSONObject authorization;
        try {
            authorization = JSONObject.parseObject(headers.get("authorization").toString());
        } catch (Exception e) {
            return Result.showInfo("00000002", "入参有误", null);
        }
        String accessToken = authorization.getString("accessToken");
        String account = JWTUtils.getAccount(accessToken);
        String now = CommonUtils.getTime(0);    //获取当前时间
        addCourseContentDto.setCreateTime(now);
        addCourseContentDto.setCreaterAccount(account);
        List<String> questionIDS = new ArrayList<>();
        if(addCourseContentDto.getCreateMethod().equals("2")){//智能组题
            JSONArray scoreArray = JSONArray.parseArray(addCourseContentDto.getScoreArray());
            addCourseContentDto.setIsAllObject("1");
            for(int i = scoreArray.size() - 1; i >= 0; i--){
                JSONObject jo = JSONObject.parseObject(scoreArray.get(i).toString());
                String type = jo.get("type").toString();
                int count = Integer.parseInt(jo.get("count").toString());
                if((type.equals("5") || type.equals("6")) && count>0){
                    addCourseContentDto.setIsAllObject("2");
                }
            }
            GetCourseQuestionDto getCourseQuestionDto = new GetCourseQuestionDto();
            for(int j = scoreArray.size() - 1; j >= 0; j--){
                JSONObject jo = JSONObject.parseObject(scoreArray.get(j).toString());
                int count = Integer.parseInt(jo.get("count").toString());
                if(count > 0){
                    String type = jo.get("type").toString();
                    getCourseQuestionDto.setCount(count);
                    getCourseQuestionDto.setCourseID(addCourseContentDto.getCourseID());
                    getCourseQuestionDto.setType(type);
                    List<CourseQuestionEntity> courseQuestionList = teachService.getCourseQuestion(getCourseQuestionDto);
                    System.out.println("courseQuestionList::::"+courseQuestionList);
                    if(courseQuestionList.size()<count){
                        return Result.showInfo("00000003", "题目数量不足", null);
                    }else{
                        for(int k = courseQuestionList.size()-1;k>=0;k--){
                            questionIDS.add(String.valueOf(courseQuestionList.get(k).getId()));
                        }
                    }
                }
            }
            addCourseContentDto.setQuestionIDs(questionIDS.toString());
        }
        JSONArray courseQuestionArray = JSONArray.parseArray(addCourseContentDto.getQuestionIDs());
        boolean isSuccess = teachService.copyCourseQuestions(courseQuestionArray);  //复制题目到course_questions_copy
        if (!isSuccess) {
            return Result.showInfo("00000004", "新增失败", null);
        }
        List<CourseQuestionCopyEntity> courseQuestionCopyList = teachService.getCourseQuestionCopyList(courseQuestionArray.size());
        JSONArray ja = new JSONArray();
        for(int i = courseQuestionCopyList.size() - 1; i >= 0; i--){
            if(!courseQuestionCopyList.get(i).getType().equals("6")){
                CopySelectsDto copySelectsDto = new CopySelectsDto();
                copySelectsDto.setQuestionCopyId(courseQuestionCopyList.get(i).getId());
                copySelectsDto.setId(courseQuestionCopyList.get(i).getQuestionID());
                teachService.copyQuestionSelects(copySelectsDto);   //复制选项到question_selects_copy
            }
            ja.add(courseQuestionCopyList.get(i).getId());
        }
        addCourseContentDto.setQuestionIDs(ja.toString());
        isSuccess = teachService.addCourseContentDto(addCourseContentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000005", "新增失败", null);
        }
    }

    //删除课程测评
    @PostMapping("/deleteCourseContent")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteCourseContent(@Validated @RequestBody DeleteCourseContentDto deleteCourseContentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseContentID = deleteCourseContentDto.getId();
        List<CourseContentEntity> courseContentList = teachService.getCourseContentByID(courseContentID);
        if (courseContentList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程测评", null);
        }
        boolean isSuccess = teachService.deleteCourseContent(deleteCourseContentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //更新课程测评
    @PostMapping("/updateCourseContent")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateCourseContent(@Validated @RequestBody UpdateCourseContentDto updateCourseContentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseContentID = updateCourseContentDto.getId();
        List<CourseContentEntity> courseContentList = teachService.getCourseContentByID(courseContentID);
        if (courseContentList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程测评", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateCourseContentDto.setUpdateTime(now);
        boolean isSuccess = teachService.updateCourseContent(updateCourseContentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //发布课程测评
    @PostMapping("/publishCourseContent")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result publishCourseContent(@Validated @RequestBody PublishCourseContentDto publishCourseContentDto, @RequestHeader Map headers, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        JSONObject authorization;
        try {
            authorization = JSONObject.parseObject(headers.get("authorization").toString());
        } catch (Exception e) {
            return Result.showInfo("00000002", "入参有误", null);
        }
        String accessToken = authorization.getString("accessToken");
        String account = JWTUtils.getAccount(accessToken);
        int courseContentID = publishCourseContentDto.getId();
        List<CourseContentEntity> courseContentList = teachService.getCourseContentByID(courseContentID);
        if (courseContentList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程测评", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        publishCourseContentDto.setUpdateTime(now);
        if(publishCourseContentDto.getReleaseMethod().equals("2")){
            publishCourseContentDto.setStatus("3");
        }else{
            publishCourseContentDto.setStatus("1");
            publishCourseContentDto.setStartTime(now);
        }
        boolean isSuccess = teachService.publishCourseContent(publishCourseContentDto);
        List<CourseContentEntity> lastcourseContent = teachService.getLastCourseContent();
        TermFilterDto termFilterDto = new TermFilterDto();
        termFilterDto.setStatus("1");
        termFilterDto.setPageSize(1);
        termFilterDto.setPageNum(1);
        List<TermFilterVo> termList = teachService.getTermList(termFilterDto);
        AddTaskDto addTaskDto = new AddTaskDto();
        addTaskDto.setTermID(termList.get(0).getId());
        addTaskDto.setContentID(lastcourseContent.get(0).getId());
        addTaskDto.setCreaterAccount(account);
        addTaskDto.setType(courseContentList.get(0).getType());
        addTaskDto.setStartTime(publishCourseContentDto.getStartTime());
        addTaskDto.setEndTime(publishCourseContentDto.getEndTime());
        addTaskDto.setCreateTime(now);
        if(publishCourseContentDto.getReleaseMethod().equals("2")){
            addTaskDto.setStatus("1");
        }else{
            addTaskDto.setStatus("2");
        }
        addTask(addTaskDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "新增失败", null);
        }
    }

    //新增任务公共方法
    public void addTask(AddTaskDto addTaskDto){
        taskService.addTask(addTaskDto);
        List<TaskEntity> taskList = taskService.getLastTaskID();
        String now = CommonUtils.getTime(0);    //获取当前时间
        int termID = addTaskDto.getTermID();
        int contentID = addTaskDto.getContentID();
        List<CourseContentEntity> courseContentList = teachService.getCourseContentByID(contentID);
        ClassCourseFilterDto classCourseFilterDto = new ClassCourseFilterDto();
        classCourseFilterDto.setTermID(termID);
        classCourseFilterDto.setCourseID(courseContentList.get(0).getCourseID());
        classCourseFilterDto.setPageNum(1);
        classCourseFilterDto.setPageSize(9999);
        classCourseFilterDto.setStatus("1");
        List<ClassCourseFilterVo> classCourseList = configService.getClassCourseList(classCourseFilterDto);
        for (ClassCourseFilterVo classCourseFilterVo : classCourseList) {
            String classid = classCourseFilterVo.getClassID();
            UserForTaskDto userForTaskDto = new UserForTaskDto();
            userForTaskDto.setBelongID(classid);
            userForTaskDto.setRole("1");
            userForTaskDto.setAuthLevel("1");
            userForTaskDto.setStatus("1");
            List<UserEntity> userList = userService.getUserForTask(userForTaskDto);
            AddTaskChildDto addTaskChildDto = new AddTaskChildDto();
            addTaskChildDto.setTaskID(taskList.get(0).getId());
            addTaskChildDto.setCreateTime(now);
            addTaskChildDto.setStatus(addTaskDto.getStatus());
            for (UserEntity u : userList) {
                addTaskChildDto.setMakerAccount(u.getAccount());
                taskService.addTaskChild(addTaskChildDto);
            }
        }
    }

    //撤回课程测评发布
    @PostMapping("/withdrawCourseContent")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result withdrawCourseContent(@Validated @RequestBody WithdrawCourseContentDto withdrawCourseContentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int courseContentID = withdrawCourseContentDto.getId();
        String now = CommonUtils.getTime(0);            //获取当前时间
        List<CourseContentEntity> courseContentList = teachService.getCourseContentByID(courseContentID);
        if (courseContentList.size() < 1) {
            return Result.showInfo("00000002", "查无此课程测评", null);
        }
        withdrawCourseContentDto.setUpdateTime(now);
        boolean isSuccess = teachService.withdrawCourseContent(withdrawCourseContentDto);
        teachService.deleteTask(withdrawCourseContentDto);
        teachService.deleteTaskChilds(withdrawCourseContentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "撤回失败", null);
        }
    }

    //获取课程测评信息
    @PostMapping("/getCourseContentInfo")
    public Result getCourseContentInfo(@Validated @RequestBody CourseContentInfoDto courseContentInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<CourseContentInfoVo> courseContentInfo = teachService.getCourseContentInfo(courseContentInfoDto);
        if (courseContentInfo.size() < 1) {
            return Result.showInfo("00000002", "查无此课程测评", null);
        }
        JSONArray ja = new JSONArray();
        String op = courseContentInfoDto.getOp();
        JSONArray courseQuestionidArray = JSONArray.parseArray(courseContentInfo.get(0).getQuestionIDs());
        for (Object o : courseQuestionidArray) {
            List<CourseQuestionCopyVo> courseQuestioncopyList = teachService.getCourseQuestionCopyByID(o.toString());
            List<QuestionSelectsCopyVo> questionSelectscopyList = teachService.getQuestionSelectsCopy(Integer.parseInt(o.toString()));
            if(!courseQuestioncopyList.get(0).getType().equals("6")){
                if(op.equals("make")){
                    questionSelectscopyList.get(0).setIsCorrect(null);
                    if(courseQuestioncopyList.get(0).getType().equals("4") || courseQuestioncopyList.get(0).getType().equals("5")){
                        questionSelectscopyList.get(0).setContent(null);
                    }
                }
                courseQuestioncopyList.get(0).setSelectList(questionSelectscopyList);
            }else {
                courseQuestioncopyList.get(0).setSelectList(null);
            }
            ja.add(courseQuestioncopyList.get(0));
        }
        courseContentInfo.get(0).setScoreArray(JSONArray.parseArray(courseContentInfo.get(0).getScoresArray()));
        courseContentInfo.get(0).setQuestionList(ja);
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(courseContentInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }
}