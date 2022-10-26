package com.admin.service.impl;

import com.admin.mapper.TaskMapper;
import com.admin.pojo.dto.task.*;
import com.admin.pojo.entity.QuestionSelectsCopyEntity;
import com.admin.pojo.entity.TaskChildEntity;
import com.admin.pojo.entity.TaskEntity;
import com.admin.pojo.vo.task.StudentTaskFilterVo;
import com.admin.pojo.vo.task.TaskChildFilterVo;
import com.admin.pojo.vo.task.TaskChildInfoVo;
import com.admin.pojo.vo.task.TaskFilterVo;
import com.admin.service.TaskService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @BelongsProject: VESP
 * @BelongsPackage: com.admin.service.impl
 * @Author: Du Rongjun
 * @CreateTime: 2022-07-06  18:07
 * @Description: token
 * @Version: 1.0
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskMapper taskMapper;

    @Override
    public boolean addTask(AddTaskDto addTaskDto) {
        return taskMapper.addTask(addTaskDto);
    }

    @Override
    public boolean addTaskChild(AddTaskChildDto addTaskChildDto) {
        return taskMapper.addTaskChild(addTaskChildDto);
    }

    @Override
    public boolean addAnswersRecords(AnswersRecordsDto answersRecordsDto) {
        return taskMapper.addAnswersRecords(answersRecordsDto);
    }

    @Override
    public boolean updateTaskChild(UpdateTasksChildDto updateTasksChildDto) {
        return taskMapper.updateTaskChild(updateTasksChildDto);
    }

    @Override
    public List<TaskEntity> getLastTaskID() {
        return taskMapper.getLastTaskID();
    }

    @Override
    public List<TaskFilterVo> getTaskList(TaskFilterDto taskFilterDto) {
        PageHelper.startPage(taskFilterDto.getPageNum(), taskFilterDto.getPageSize());
        return taskMapper.getTaskList(taskFilterDto);
    }

    @Override
    public int getTaskListTotalCount(TaskFilterDto taskFilterDto) {
        return taskMapper.getTaskList(taskFilterDto).size();
    }

    @Override
    public List<TaskChildFilterVo> getChildTaskList(TaskChildFilterDto taskChildFilterDto) {
        PageHelper.startPage(taskChildFilterDto.getPageNum(), taskChildFilterDto.getPageSize());
        return taskMapper.getChildTaskList(taskChildFilterDto);
    }

    @Override
    public List<StudentTaskFilterVo> getStudentTaskList(StudentTaskFilterDto studentTaskFilterDto) {
        PageHelper.startPage(studentTaskFilterDto.getPageNum(), studentTaskFilterDto.getPageSize());
        return taskMapper.getStudentTaskList(studentTaskFilterDto);
    }

    @Override
    public int getStudentTaskListTotalCount(StudentTaskFilterDto studentTaskFilterDto) {
        return taskMapper.getStudentTaskList(studentTaskFilterDto).size();
    }

    @Override
    public List<TaskChildInfoVo> getChildTaskByDto(FinishChildTaskDto finishChildTaskDto) {
        return taskMapper.getChildTaskByDto(finishChildTaskDto);
    }

    @Override
    public List<TaskChildInfoVo> getChildTaskInfo(ChildTaskInfoDto childTaskInfoDto) {
        return taskMapper.getChildTaskInfo(childTaskInfoDto);
    }

    @Override
    public List<QuestionSelectsCopyEntity> getCSelectsRecords(JSONArray answerArray) {
        return taskMapper.getCSelectsRecords(answerArray);
    }

    @Override
    public List<QuestionSelectsCopyEntity> getISelectsRecords(JSONArray answerArray) {
        return taskMapper.getISelectsRecords(answerArray);
    }

    @Override
    public List<QuestionSelectsCopyEntity> checkSelectsCopy(CheckSelectsCopyDto checkSelectsCopyDto) {
        return taskMapper.checkSelectsCopy(checkSelectsCopyDto);
    }

    @Override
    public int getChildTaskListTotalCount(TaskChildFilterDto taskChildFilterDto) {
        return taskMapper.getChildTaskList(taskChildFilterDto).size();
    }
}