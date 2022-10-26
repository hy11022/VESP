package com.admin.service;

import com.admin.pojo.dto.teach.CourseQuestionFilterDto;
import com.admin.pojo.dto.teach.*;
import com.admin.pojo.entity.*;
import com.admin.pojo.vo.teach.CourseQuestionFilterVo;
import com.admin.pojo.vo.teach.*;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TeachService {

    List<CourseFilterVo> getCourseList(CourseFilterDto courseFilterDto);

    int getCourseListTotalCount(CourseFilterDto courseFilterDto);

    boolean addCourse(AddCourseDto addCourseDto);

    List<CourseEntity> getCourseByID(int courseID);

    List<CourseEntity> checkCourseByDto(AddCourseDto addCourseDto);

    boolean deleteCourse(DeleteCourseDto deleteCourseDto);

    boolean updateCourse(UpdateCourseDto updateCourseDto);

    boolean updateCourseStatus(UpdateCourseStatusDto updateCourseStatusDto);

    List<CourseInfoVo> getCourseInfo(CourseInfoDto courseInfoDto);

    List<TermFilterVo> getTermList(TermFilterDto termFilterDto);

    int getTermListTotalCount(TermFilterDto termFilterDto);

    List<TermEntity> checkTermByDto(AddTermDto addTermDto);

    List<TermEntity> getTermByID(int termID);

    boolean addTerm(AddTermDto addTermDto);

    boolean deleteTerm(DeleteTermDto deleteTermDto);

    boolean updateTerm(UpdateTermDto updateTermDto);

    boolean updateTermStatus(UpdateTermStatusDto updateTermStatusDto);

    void updateAllTermStatus(String updateTime);

    List<CourseResourseFilterVo> getCourseResourseList(CourseResourseFilterDto courseResourseFilterDto);

    int getCourseResourseListTotalCount(CourseResourseFilterDto courseResourseFilterDto);

    boolean addCourseResourse(AddCourseResourseDto addCourseResourseDto);

    List<CourseResourseEntity> getCourseResourseByID(int courseResourseID);

    boolean deleteCourseResourse(DeleteCourseResourseDto deleteCourseResourseDto);

    boolean updateCourseResourse(UpdateCourseResourseDto updateCourseResourseDto);

    boolean updateCourseResourseStatus(UpdateCourseResourseStatusDto updateCourseResourseStatusDto);

    List<CourseQuestionFilterVo> getCourseQuestionList(CourseQuestionFilterDto courseQuestionFilterDto);

    List<CourseQuestionEntity> getCourseQuestionByID(int courseQuestionID);

    int getCourseQuestionListTotalCount(CourseQuestionFilterDto courseQuestionFilterDto);

    boolean addCourseQuestion(AddCourseQuestionDto addCourseQuestionDto);

    boolean addQuestionSelects(AddQuestionSelectDto addQuestionSelectDto);

    boolean deleteCourseQuestion(DeleteCourseQuestionDto deleteCourseQuestionDto);

    boolean updateCourseQuestion(UpdateCourseQuestionDto updateCourseQuestionDto);

    boolean deleteQuestionSelects(int courseQuestionID);

    List<CourseQuestionEntity> getLastCourseQuestion();

    List<CourseQuestionInfoVo> getCourseQuestionInfo(CourseQuestionInfoDto courseQuestionInfoDto);

    List<QuestionSelectsEntity> getQuestionSelects(int courseQuestionID);

    List<CourseQuestionCopyInfoVo> getCourseQuestionCopyInfo(CourseQuestionCopyInfoDto courseQuestionCopyInfoDto);

    List<QuestionSelectsCopyVo> getQuestionSelectsCopy(int courseQuestionCopyID);

    List<CourseContentFilterVo> getCourseContentList(CourseContentFilterDto courseContentFilterDto);

    List<CourseContentInfoVo> getCourseContentInfo(CourseContentInfoDto courseContentInfoDto);

    List<CourseContentEntity> getCourseContentByID(int courseContentID);

    List<CourseQuestionCopyEntity> getCourseQuestionCopyList(int questionSize);

    List<CourseQuestionCopyVo> getCourseQuestionCopyByID(String copyQuestionID);

    boolean copyCourseQuestions(JSONArray courseQuestionArray);

    void copyQuestionSelects(CopySelectsDto copySelectsDto);

    int getCourseContentListTotalCount(CourseContentFilterDto courseContentFilterDto);

    boolean updateCourseQuestionStatus(UpdateCourseQuestionStatusDto updateCourseQuestionStatusDto);

    boolean addCourseContentDto(AddCourseContentDto addCourseContentDto);

    boolean deleteCourseContent(DeleteCourseContentDto deleteCourseContentDto);

    boolean updateCourseContent(UpdateCourseContentDto updateCourseContentDto);

    boolean publishCourseContent(PublishCourseContentDto publishCourseContentDto);

    boolean withdrawCourseContent(WithdrawCourseContentDto withdrawCourseContentDto);

    void deleteTask(WithdrawCourseContentDto withdrawCourseContentDto);

    void deleteTaskChilds(WithdrawCourseContentDto withdrawCourseContentDto);

    List<CourseQuestionEntity> getCourseQuestion(GetCourseQuestionDto getCourseQuestionDto);

    List<CourseContentEntity> getLastCourseContent();
}