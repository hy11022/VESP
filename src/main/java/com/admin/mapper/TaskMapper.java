package com.admin.mapper;

import com.admin.pojo.dto.task.*;
import com.admin.pojo.entity.QuestionSelectsCopyEntity;
import com.admin.pojo.entity.TaskChildEntity;
import com.admin.pojo.entity.TaskEntity;
import com.admin.pojo.vo.task.StudentTaskFilterVo;
import com.admin.pojo.vo.task.TaskChildFilterVo;
import com.admin.pojo.vo.task.TaskChildInfoVo;
import com.admin.pojo.vo.task.TaskFilterVo;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TaskMapper {

    @Insert("INSERT INTO tasks SET term_id = #{termID},type=#{type},content_id=#{contentID},start_time=#{startTime}," +
            "end_time = #{endTime},create_time=#{createTime},creater_account=#{createrAccount},status=#{status}")
    boolean addTask(AddTaskDto addTaskDto);

    @Select("SELECT IFNULL(MAX(id),0) AS ID FROM tasks")
    List<TaskEntity> getLastTaskID();

    @Insert("INSERT INTO tasks_child SET task_id = #{taskID},maker_account=#{makerAccount}," +
            "submit_time = #{submitTime},create_time=#{createTime},status=#{status}")
    boolean addTaskChild(AddTaskChildDto addTaskChildDto);

    List<TaskFilterVo> getTaskList(TaskFilterDto taskFilterDto);

    List<TaskChildFilterVo> getChildTaskList(TaskChildFilterDto taskChildFilterDto);

    @Select("SELECT a.*,b.content_id AS contentID FROM tasks_child a LEFT JOIN tasks b ON a.task_id = b.id WHERE a.id=#{childTaskID}")
    List<TaskChildInfoVo> getChildTaskByDto(FinishChildTaskDto finishChildTaskDto);

    @Select("SELECT a.*,b.start_time,b.end_time FROM tasks_child a LEFT JOIN tasks b ON a.task_id=b.id WHERE a.id=#{id}")
    List<TaskChildInfoVo> getChildTaskInfo(ChildTaskInfoDto childTaskInfoDto);

    List<QuestionSelectsCopyEntity> getCSelectsRecords(JSONArray answerArray);

    List<QuestionSelectsCopyEntity> getISelectsRecords(JSONArray answerArray);

    @Select("SELECT * FROM question_selects_copy WHERE id=#{answerID} AND content=#{content}")
    List<QuestionSelectsCopyEntity> checkSelectsCopy(CheckSelectsCopyDto checkSelectsCopyDto);

    @Insert("INSERT INTO answers_records SET task_child_id=#{taskChildID},question_copy_id = #{questionCopyID}," +
            "answer=#{answer},score=#{score},status=#{status}")
    boolean addAnswersRecords(AnswersRecordsDto answersRecordsDto);

    boolean updateTaskChild(UpdateTasksChildDto updateTasksChildDto);

    List<StudentTaskFilterVo> getStudentTaskList(StudentTaskFilterDto studentTaskFilterDto);
}