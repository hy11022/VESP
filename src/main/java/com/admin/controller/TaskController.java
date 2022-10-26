package com.admin.controller;

import com.admin.pojo.dto.task.*;
import com.admin.pojo.dto.teach.CourseContentInfoDto;
import com.admin.pojo.entity.QuestionSelectsCopyEntity;
import com.admin.pojo.vo.task.StudentTaskFilterVo;
import com.admin.pojo.vo.task.TaskChildFilterVo;
import com.admin.pojo.vo.task.TaskChildInfoVo;
import com.admin.pojo.vo.teach.CourseContentInfoVo;
import com.admin.service.TeachService;
import com.admin.util.CommonUtils;
import com.admin.util.JWTUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.admin.pojo.vo.task.TaskFilterVo;
import com.admin.service.TaskService;
import com.admin.util.Result;

import java.util.Map;
import java.util.Objects;
import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private TeachService teachService;

    //获取任务列表
    @PostMapping("/getTaskList")
    public Result getTaskList(@Validated @RequestBody TaskFilterDto taskFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<TaskFilterVo> taskList = taskService.getTaskList(taskFilterDto);
        int totalCount = taskService.getTaskListTotalCount(taskFilterDto);
        return Result.showList("00000000", "Success", taskList, totalCount);
    }

    //获取子任务列表
    @PostMapping("/getChildTaskList")
    public Result getChildTaskList(@Validated @RequestBody TaskChildFilterDto taskChildFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<TaskChildFilterVo> taskChildList = taskService.getChildTaskList(taskChildFilterDto);
        int totalCount = taskService.getChildTaskListTotalCount(taskChildFilterDto);
        return Result.showList("00000000", "Success", taskChildList, totalCount);
    }

    //获取学生任务列表
    @PostMapping("/getStudentTaskList")
    public Result getStudentTaskList(@Validated @RequestBody StudentTaskFilterDto studentTaskFilterDto, @RequestHeader Map headers, BindingResult bindingResult) {
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
        studentTaskFilterDto.setAccount(account);
        List<StudentTaskFilterVo> studentTaskList = taskService.getStudentTaskList(studentTaskFilterDto);
        int totalCount = taskService.getStudentTaskListTotalCount(studentTaskFilterDto);
        return Result.showList("00000000", "Success", studentTaskList, totalCount);
    }

    //完成子任务
    @PostMapping("/finishChildTask")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result finishChildTask(@Validated @RequestBody FinishChildTaskDto finishChildTaskDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        JSONArray ja = JSONArray.parseArray(finishChildTaskDto.getQuestionCopyList());
        int totalScore = 0;
        UpdateTasksChildDto updateTasksChildDto = new UpdateTasksChildDto();
        List<TaskChildInfoVo> childTaskInfoList = taskService.getChildTaskByDto(finishChildTaskDto);
        if(childTaskInfoList.size()<1){
            return Result.showInfo("00000002", "任务不存在", null);
        }
        if(!childTaskInfoList.get(0).getStatus().equals("2")){
            return Result.showInfo("00000002", "不可重复交卷", null);
        }
        boolean isSubAnswersEmpty = false;//主观题是否未作答
        for (Object o : ja) {
            JSONObject jo = JSONObject.parseObject(o.toString());
            String type = jo.get("type").toString();
            String answerList = jo.get("answerList").toString();
            AnswersRecordsDto answersRecordsDto = new AnswersRecordsDto();
            answersRecordsDto.setTaskChildID(finishChildTaskDto.getChildTaskID());
            answersRecordsDto.setQuestionCopyID(jo.get("id").toString());
            answersRecordsDto.setAnswer(answerList);
            JSONArray answerArray = JSONArray.parseArray(answerList);

            CourseContentInfoDto courseContentInfoDto = new CourseContentInfoDto();
            courseContentInfoDto.setId(childTaskInfoList.get(0).getContentID());
            List<CourseContentInfoVo> courseContentInfo = teachService.getCourseContentInfo(courseContentInfoDto);
            String isAllObject = courseContentInfo.get(0).getIsAllObject();
            if (isAllObject.equals("1")) {
                updateTasksChildDto.setStatus("4");
            } else {
                updateTasksChildDto.setStatus("3");
            }
            String scoreStr = courseContentInfo.get(0).getScoresArray();
            JSONArray scoreArray = JSONArray.parseArray(scoreStr);
            if ((type.equals("5") || type.equals("6"))) {
                answersRecordsDto.setScore("0");
                if (answerArray.size() < 1) {
                    answersRecordsDto.setStatus("1");
                    isSubAnswersEmpty = true;
                } else {
                    answersRecordsDto.setStatus("2");
                }
            } else {
                if (!type.equals("1") && !type.equals("2")) {
                    if (type.equals("3")) {
                        if (answerArray.size() < 1) {
                            answersRecordsDto.setStatus("1");
                            answersRecordsDto.setScore("0");
                        } else {
                            List<QuestionSelectsCopyEntity> correctselectsRecordList = taskService.getCSelectsRecords(answerArray);//检验答案是否正确
                            List<QuestionSelectsCopyEntity> incorrectselectsRecordList = taskService.getISelectsRecords(answerArray);//检验是否漏正确答案
                            boolean flag = true;
                            for (int j = correctselectsRecordList.size() - 1; j >= 0; j--) {
                                String isCorrect = correctselectsRecordList.get(j).getIsCorrect();
                                if (!isCorrect.equals("1")) {
                                    flag = false;
                                    break;//满足条件后，可以终止循环
                                }
                            }
                            for (int j = incorrectselectsRecordList.size() - 1; j >= 0; j--) {
                                String isCorrect = incorrectselectsRecordList.get(j).getIsCorrect();
                                if (isCorrect.equals("1")) {
                                    flag = false;
                                    break;//满足条件后，可以终止循环
                                }
                            }
                            answersRecordsDto.setStatus("1");
                            if (flag) {
                                JSONObject object3 = JSONObject.parseObject(scoreArray.get(2).toString());
                                answersRecordsDto.setScore(object3.get("score").toString());
                                totalScore = totalScore + Integer.parseInt(object3.get("score").toString());
                            } else {
                                answersRecordsDto.setScore("0");
                            }
                        }
                    } else if (type.equals("4")) {
                        if (answerArray.size() < 1) {
                            answersRecordsDto.setStatus("1");
                            answersRecordsDto.setScore("0");
                        } else {
                            int count = 0;
                            for (int l = answerArray.size() - 1; l >= 0; l--) {
                                JSONObject answerObject = JSONObject.parseObject(answerArray.get(l).toString());
                                String answerID = answerObject.get("id").toString();
                                String content = answerObject.get("content").toString();
                                CheckSelectsCopyDto checkSelectsCopyDto = new CheckSelectsCopyDto();
                                checkSelectsCopyDto.setAnswerID(answerID);
                                checkSelectsCopyDto.setContent(content);
                                List<QuestionSelectsCopyEntity> selectsRecordList = taskService.checkSelectsCopy(checkSelectsCopyDto);
                                if (selectsRecordList.size() > 0) {
                                    count++;
                                }
                            }
                            answersRecordsDto.setStatus("1");
                            if (count == answerArray.size()) {
                                JSONObject object4 = JSONObject.parseObject(scoreArray.get(3).toString());
                                answersRecordsDto.setScore(object4.get("score").toString());
                                totalScore = totalScore + Integer.parseInt(object4.get("score").toString());
                            } else {
                                answersRecordsDto.setScore("0");
                            }
                        }
                    }
                } else {
                    if (answerArray.size() < 1) {
                        answersRecordsDto.setStatus("1");
                        answersRecordsDto.setScore("0");
                    }else{
                        List<QuestionSelectsCopyEntity> selectsRecordList = taskService.getCSelectsRecords(answerArray);
                        boolean flag = true;
                        String isCorrect = selectsRecordList.get(0).getIsCorrect();
                        if (!isCorrect.equals("1")) {
                            flag = false;
                        }
                        answersRecordsDto.setStatus("1");
                        if (flag) {
                            JSONObject object1 = JSONObject.parseObject(scoreArray.get(0).toString());
                            JSONObject object2 = JSONObject.parseObject(scoreArray.get(1).toString());
                            if (type.equals("1")) {
                                answersRecordsDto.setScore(object1.get("score").toString());
                                totalScore = totalScore + Integer.parseInt(object1.get("score").toString());
                            } else {
                                answersRecordsDto.setScore(object2.get("score").toString());
                                totalScore = totalScore + Integer.parseInt(object2.get("score").toString());
                            }
                        } else {
                            answersRecordsDto.setScore("0");
                        }
                    }
                }
            }
            taskService.addAnswersRecords(answersRecordsDto);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateTasksChildDto.setId(finishChildTaskDto.getChildTaskID());
        updateTasksChildDto.setSubmitTime(now);
        updateTasksChildDto.setScore("0");
        if(isSubAnswersEmpty){
            updateTasksChildDto.setStatus("4");
        }
        boolean isSuccess = taskService.updateTaskChild(updateTasksChildDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000002", "新增失败", null);
        }
    }

    //获取子任务信息
    @PostMapping("/getChildTaskInfo")
    public Result getChildTaskInfo(@Validated @RequestBody ChildTaskInfoDto childTaskInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<TaskChildInfoVo> childTaskInfo = taskService.getChildTaskInfo(childTaskInfoDto);
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(childTaskInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }
}