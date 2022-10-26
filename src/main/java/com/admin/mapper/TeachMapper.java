package com.admin.mapper;

import com.admin.pojo.dto.teach.CourseQuestionFilterDto;
import com.admin.pojo.dto.teach.*;
import com.admin.pojo.entity.*;
import com.admin.pojo.vo.teach.CourseQuestionFilterVo;
import com.admin.pojo.vo.teach.*;
import com.alibaba.fastjson.JSONArray;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TeachMapper {

    List<CourseFilterVo> getCourseList(CourseFilterDto courseFilterDto);

    @Insert("INSERT INTO courses SET name = #{name},department_id=#{departmentID},teacher_account=#{teacherAccount}," +
            "label_ids=#{labelIDs},introduction = #{introduction},cover=#{cover},create_time=#{createTime}")
    boolean addCourse(AddCourseDto addCourseDto);

    @Select("SELECT * FROM courses WHERE id=#{courseID}")
    List<CourseEntity> getCourseByID(int courseID);

    @Delete("DELETE FROM courses WHERE id=#{id}")
    boolean deleteCourse(DeleteCourseDto deleteCourseDto);

    boolean updateCourse(UpdateCourseDto updateCourseDto);

    @Select("SELECT a.*,b.name AS departmentName FROM courses a " +
            "LEFT JOIN departments b ON a.department_id = b.id WHERE a.id=#{id}")
    List<CourseInfoVo> getCourseInfo(CourseInfoDto courseInfoDto);

    @Update("UPDATE courses SET status=#{status},update_time = #{updateTime} WHERE id=#{id} ")
    boolean updateCourseStatus(UpdateCourseStatusDto updateCourseStatusDto);

    @Select("SELECT * FROM courses WHERE name = #{name} " +
            "AND department_id=#{departmentID} AND teacher_account=#{teacherAccount}")
    List<CourseEntity> checkCourseByDto(AddCourseDto addCourseDto);

    List<TermFilterVo> getTermList(TermFilterDto termFilterDto);

    @Select("SELECT * FROM terms WHERE name = #{name}")
    List<TermEntity> checkTermByDto(AddTermDto addTermDto);

    @Insert("INSERT INTO terms SET name = #{name},create_time=#{createTime},status = #{status}")
    boolean addTerm(AddTermDto addTermDto);

    @Select("SELECT * FROM terms WHERE id = #{termID}")
    List<TermEntity> getTermByID(int termID);

    @Delete("DELETE FROM terms WHERE id=#{id}")
    boolean deleteTerm(DeleteTermDto deleteTermDto);

    @Update("UPDATE terms SET name=#{name},update_time = #{updateTime} WHERE id=#{id} ")
    boolean updateTerm(UpdateTermDto updateTermDto);

    @Update("UPDATE terms SET status=#{status},update_time = #{updateTime} WHERE id=#{id} ")
    boolean updateTermStatus(UpdateTermStatusDto updateTermStatusDto);

    @Update("UPDATE terms SET status=2,update_time = #{updateTime}")
    void updateAllTermStatus(String updateTime);

    List<CourseResourseFilterVo> getCourseResourseList(CourseResourseFilterDto courseResourseFilterDto);

    @Insert("INSERT INTO course_resources SET name = #{name},course_id=#{courseID},cover=#{cover}," +
            "type=#{type},path=#{path},introduction=#{introduction},create_time=#{createTime}")
    boolean addCourseResourse(AddCourseResourseDto addCourseResourseDto);

    @Select("SELECT * FROM course_resources WHERE id = #{courseResourseID}")
    List<CourseResourseEntity> getCourseResourseByID(int courseResourseID);

    @Delete("DELETE FROM course_resources WHERE id=#{id}")
    boolean deleteCourseResourse(DeleteCourseResourseDto deleteCourseResourseDto);

    @Update("UPDATE course_resources SET name=#{name},cover=#{cover},type=#{type}," +
            "path=#{path},introduction=#{introduction},update_time = #{updateTime} WHERE id=#{id}")
    boolean updateCourseResourse(UpdateCourseResourseDto updateCourseResourseDto);

    @Update("UPDATE course_resources SET status=#{status},update_time = #{updateTime} WHERE id=#{id}")
    boolean updateCourseResourseStatus(UpdateCourseResourseStatusDto updateCourseResourseStatusDto);

    List<CourseQuestionFilterVo> getCourseQuestionList(CourseQuestionFilterDto courseQuestionFilterDto);

    @Insert("INSERT INTO course_questions SET content = #{content},course_id=#{courseID}," +
            "type=#{type},create_time=#{createTime}")
    boolean addCourseQuestion(AddCourseQuestionDto addCourseQuestionDto);

    @Select("SELECT * FROM course_questions ORDER BY id DESC")
    List<CourseQuestionEntity> getLastCourseQuestion();

    @Insert("INSERT INTO question_selects SET content = #{content},question_id=#{questionID},is_correct=#{isCorrect}")
    boolean addQuestionSelects(AddQuestionSelectDto addQuestionSelectDto);

    @Select("SELECT * FROM course_questions WHERE id=#{courseQuestionID}")
    List<CourseQuestionEntity> getCourseQuestionByID(int courseQuestionID);

    @Delete("DELETE FROM course_questions WHERE id=#{id}")
    boolean deleteCourseQuestion(DeleteCourseQuestionDto deleteCourseQuestionDto);

    @Update("UPDATE course_questions SET content = #{content},course_id=#{courseID},type=#{type}," +
            "update_time = #{updateTime} WHERE id=#{id}")
    boolean updateCourseQuestion(UpdateCourseQuestionDto updateCourseQuestionDto);

    @Delete("DELETE FROM question_selects WHERE question_id=#{courseQuestionID}")
    boolean deleteQuestionSelects(int courseQuestionID);

    @Update("UPDATE course_questions SET status = #{status}, update_time = #{updateTime} WHERE id=#{id}")
    boolean updateCourseQuestionStatus(UpdateCourseQuestionStatusDto updateCourseQuestionStatusDto);

    @Select("SELECT a.*,b.name AS courseName FROM course_questions a " +
            "LEFT JOIN courses b ON a.course_id = b.id WHERE a.id=#{id}")
    List<CourseQuestionInfoVo> getCourseQuestionInfo(CourseQuestionInfoDto courseQuestionInfoDto);

    @Select("SELECT * FROM question_selects WHERE question_id = #{courseQuestionID}")
    List<QuestionSelectsEntity> getQuestionSelects(int courseQuestionID);

    List<CourseContentFilterVo> getCourseContentList(CourseContentFilterDto courseContentFilterDto);

    @Insert("INSERT INTO course_contents SET title = #{title},type=#{type},course_id=#{courseID}," +
            "question_ids=#{questionIDs},is_all_object=#{isAllObject},duration=#{duration}," +
            "score_array=#{scoreArray},remark=#{remark},create_method=#{createMethod}," +
            "creater_account=#{createrAccount},create_time=#{createTime}")
    boolean addCourseContentDto(AddCourseContentDto addCourseContentDto);

    @Select("SELECT * FROM course_contents WHERE id=#{courseContentID}")
    List<CourseContentEntity> getCourseContentByID(int courseContentID);

    @Delete("DELETE FROM course_contents WHERE id=#{id}")
    boolean deleteCourseContent(DeleteCourseContentDto deleteCourseContentDto);

    @Update("UPDATE course_contents SET title = #{title},type=#{type},course_id=#{courseID}," +
            "question_ids=#{questionIDs},is_all_object=#{isAllObject},duration=#{duration}," +
            "score_array=#{scoreArray},remark=#{remark},create_method=#{createMethod}," +
            "update_time=#{updateTime} WHERE id=#{id}")
    boolean updateCourseContent(UpdateCourseContentDto updateCourseContentDto);

    boolean copyCourseQuestions(JSONArray courseQuestionArray);

    @Insert("INSERT INTO question_selects_copy(question_copy_id,content,is_correct) " +
            "SELECT #{questionCopyId},content,is_correct FROM question_selects WHERE question_id = #{id}")
    void copyQuestionSelects(CopySelectsDto copySelectsDto);

    @Select("SELECT * FROM course_questions_copy ORDER BY ID DESC LIMIT #{questionSize}")
    List<CourseQuestionCopyEntity> getCourseQuestionCopyList(int questionSize);

    @Update("UPDATE course_contents SET update_time=#{updateTime},release_method=#{releaseMethod}," +
            "release_time=#{startTime},status=#{status} WHERE id=#{id}")
    boolean publishCourseContent(PublishCourseContentDto publishCourseContentDto);

    @Update("UPDATE course_contents SET update_time=#{updateTime},status = 2," +
            "release_method='',release_time='' WHERE id=#{id}")
    boolean withdrawCourseContent(WithdrawCourseContentDto withdrawCourseContentDto);

    @Select("SELECT * FROM course_questions_copy WHERE id=#{copyQuestionID}")
    List<CourseQuestionCopyVo> getCourseQuestionCopyByID(String copyQuestionID);

    @Select("SELECT a.*,a.score_array AS scoresArray,b.name AS createrName,c.name AS courseName" +
            " FROM course_contents a LEFT JOIN users b ON a.creater_account = b.account" +
            " LEFT JOIN courses c ON a.course_id = c.id WHERE a.id =#{id}")
    List<CourseContentInfoVo> getCourseContentInfo(CourseContentInfoDto courseContentInfoDto);

    @Select("SELECT * FROM question_selects_copy WHERE question_copy_id = #{courseQuestionCopyID}")
    List<QuestionSelectsCopyVo> getQuestionSelectsCopy(int courseQuestionCopyID);

    @Select("SELECT * FROM course_questions WHERE course_id in (#{courseID}) AND type = #{type} " +
            "ORDER BY RAND() LIMIT #{count}")
    List<CourseQuestionEntity> getCourseQuestion(GetCourseQuestionDto getCourseQuestionDto);

    @Select("SELECT IFNULL(MAX(id),0) AS ID FROM course_contents")
    List<CourseContentEntity> getLastCourseContent();

    @Delete("DELETE FROM tasks WHERE content_id=#{id}")
    void deleteTask(WithdrawCourseContentDto withdrawCourseContentDto);

    @Delete("DELETE FROM tasks_child WHERE task_id =(SELECT id FROM tasks WHERE content_id=#{id})")
    void deleteTaskChilds(WithdrawCourseContentDto withdrawCourseContentDto);

    @Select("SELECT a.*,b.course_id AS courseID,c.name AS courseName FROM course_questions_copy a " +
            "LEFT JOIN course_questions b ON a.question_id = b.id " +
            "LEFT JOIN courses c ON b.course_id = c.id WHERE id=#{id}")
    List<CourseQuestionCopyInfoVo> getCourseQuestionCopyInfo(CourseQuestionCopyInfoDto courseQuestionCopyInfoDto);
}