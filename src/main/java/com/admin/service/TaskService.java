package com.admin.service;

import com.admin.pojo.dto.task.*;
import com.admin.pojo.entity.QuestionSelectsCopyEntity;
import com.admin.pojo.entity.TaskChildEntity;
import com.admin.pojo.entity.TaskEntity;
import com.admin.pojo.vo.task.StudentTaskFilterVo;
import com.admin.pojo.vo.task.TaskChildFilterVo;
import com.admin.pojo.vo.task.TaskChildInfoVo;
import com.admin.pojo.vo.task.TaskFilterVo;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TaskService {

    boolean addTask(AddTaskDto addTaskDto);

    boolean addTaskChild(AddTaskChildDto addTaskChildDto);

    boolean addAnswersRecords(AnswersRecordsDto answersRecordsDto);

    boolean updateTaskChild(UpdateTasksChildDto updateTasksChildDto);

    List<TaskEntity> getLastTaskID();

    List<TaskFilterVo> getTaskList(TaskFilterDto taskFilterDto);

    int getTaskListTotalCount(TaskFilterDto taskFilterDto);

    List<TaskChildFilterVo> getChildTaskList(TaskChildFilterDto taskChildFilterDto);

    List<StudentTaskFilterVo> getStudentTaskList(StudentTaskFilterDto studentTaskFilterDto);

    int getStudentTaskListTotalCount(StudentTaskFilterDto studentTaskFilterDto);

    List<TaskChildInfoVo> getChildTaskByDto(FinishChildTaskDto finishChildTaskDto);

    List<TaskChildInfoVo> getChildTaskInfo(ChildTaskInfoDto childTaskInfoDto);

    List<QuestionSelectsCopyEntity> getCSelectsRecords(JSONArray answerArray);

    List<QuestionSelectsCopyEntity> getISelectsRecords(JSONArray answerArray);

    List<QuestionSelectsCopyEntity> checkSelectsCopy(CheckSelectsCopyDto checkSelectsCopyDto);

    int getChildTaskListTotalCount(TaskChildFilterDto taskChildFilterDto);
}