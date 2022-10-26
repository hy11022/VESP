package com.admin.service.impl;

import com.admin.mapper.TeachMapper;
import com.admin.pojo.dto.teach.CourseQuestionFilterDto;
import com.admin.pojo.dto.teach.*;
import com.admin.pojo.entity.*;
import com.admin.pojo.vo.teach.CourseQuestionFilterVo;
import com.admin.pojo.vo.teach.*;
import com.admin.service.TeachService;
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
public class TeachServiceImpl implements TeachService {

    @Autowired
    TeachMapper teachMapper;

    @Override
    public List<CourseFilterVo> getCourseList(CourseFilterDto courseFilterDto) {
        PageHelper.startPage(courseFilterDto.getPageNum(), courseFilterDto.getPageSize());
        return teachMapper.getCourseList(courseFilterDto);
    }

    @Override
    public int getCourseListTotalCount(CourseFilterDto courseFilterDto) {
        return teachMapper.getCourseList(courseFilterDto).size();
    }

    @Override
    public boolean addCourse(AddCourseDto addCourseDto) {
        return teachMapper.addCourse(addCourseDto);
    }

    @Override
    public List<CourseEntity> getCourseByID(int courseID) {
        return teachMapper.getCourseByID(courseID);
    }

    @Override
    public List<CourseEntity> checkCourseByDto(AddCourseDto addCourseDto) {
        return teachMapper.checkCourseByDto(addCourseDto);
    }

    @Override
    public boolean deleteCourse(DeleteCourseDto deleteCourseDto) {
        return teachMapper.deleteCourse(deleteCourseDto);
    }

    @Override
    public boolean updateCourse(UpdateCourseDto updateCourseDto) {
        return teachMapper.updateCourse(updateCourseDto);
    }

    @Override
    public boolean updateCourseStatus(UpdateCourseStatusDto updateCourseStatusDto) {
        return teachMapper.updateCourseStatus(updateCourseStatusDto);
    }

    @Override
    public List<CourseInfoVo> getCourseInfo(CourseInfoDto courseInfoDto) {
        return teachMapper.getCourseInfo(courseInfoDto);
    }

    @Override
    public List<TermFilterVo> getTermList(TermFilterDto termFilterDto) {
        PageHelper.startPage(termFilterDto.getPageNum(), termFilterDto.getPageSize());
        return teachMapper.getTermList(termFilterDto);
    }

    @Override
    public int getTermListTotalCount(TermFilterDto termFilterDto) {
        return teachMapper.getTermList(termFilterDto).size();
    }

    @Override
    public List<TermEntity> checkTermByDto(AddTermDto addTermDto) {
        return teachMapper.checkTermByDto(addTermDto);
    }

    @Override
    public List<TermEntity> getTermByID(int termID) {
        return teachMapper.getTermByID(termID);
    }

    @Override
    public boolean addTerm(AddTermDto addTermDto) {
        return teachMapper.addTerm(addTermDto);
    }

    @Override
    public boolean deleteTerm(DeleteTermDto deleteTermDto) {
        return teachMapper.deleteTerm(deleteTermDto);
    }

    @Override
    public boolean updateTerm(UpdateTermDto updateTermDto) {
        return teachMapper.updateTerm(updateTermDto);
    }

    @Override
    public boolean updateTermStatus(UpdateTermStatusDto updateTermStatusDto) {
        return teachMapper.updateTermStatus(updateTermStatusDto);
    }

    @Override
    public void updateAllTermStatus(String updateTime) {
        teachMapper.updateAllTermStatus(updateTime);
    }

    @Override
    public List<CourseResourseFilterVo> getCourseResourseList(CourseResourseFilterDto courseResourseFilterDto) {
        PageHelper.startPage(courseResourseFilterDto.getPageNum(), courseResourseFilterDto.getPageSize());
        return teachMapper.getCourseResourseList(courseResourseFilterDto);
    }

    @Override
    public int getCourseResourseListTotalCount(CourseResourseFilterDto courseResourseFilterDto) {
        return teachMapper.getCourseResourseList(courseResourseFilterDto).size();
    }

    @Override
    public boolean addCourseResourse(AddCourseResourseDto addCourseResourseDto) {
        return teachMapper.addCourseResourse(addCourseResourseDto);
    }

    @Override
    public List<CourseResourseEntity> getCourseResourseByID(int courseResourseID) {
        return teachMapper.getCourseResourseByID(courseResourseID);
    }

    @Override
    public boolean deleteCourseResourse(DeleteCourseResourseDto deleteCourseResourseDto) {
        return teachMapper.deleteCourseResourse(deleteCourseResourseDto);
    }

    @Override
    public boolean updateCourseResourse(UpdateCourseResourseDto updateCourseResourseDto) {
        return teachMapper.updateCourseResourse(updateCourseResourseDto);
    }

    @Override
    public boolean updateCourseResourseStatus(UpdateCourseResourseStatusDto updateCourseResourseStatusDto) {
        return teachMapper.updateCourseResourseStatus(updateCourseResourseStatusDto);
    }

    @Override
    public List<CourseQuestionFilterVo> getCourseQuestionList(CourseQuestionFilterDto courseQuestionFilterDto) {
        PageHelper.startPage(courseQuestionFilterDto.getPageNum(), courseQuestionFilterDto.getPageSize());
        return teachMapper.getCourseQuestionList(courseQuestionFilterDto);
    }

    @Override
    public List<CourseQuestionEntity> getCourseQuestionByID(int courseQuestionID) {
        return teachMapper.getCourseQuestionByID(courseQuestionID);
    }

    @Override
    public int getCourseQuestionListTotalCount(CourseQuestionFilterDto courseQuestionFilterDto) {
        return teachMapper.getCourseQuestionList(courseQuestionFilterDto).size();
    }

    @Override
    public boolean addCourseQuestion(AddCourseQuestionDto addCourseQuestionDto) {
        return teachMapper.addCourseQuestion(addCourseQuestionDto);
    }

    @Override
    public boolean addQuestionSelects(AddQuestionSelectDto addQuestionSelectDto) {
        return teachMapper.addQuestionSelects(addQuestionSelectDto);
    }

    @Override
    public boolean deleteCourseQuestion(DeleteCourseQuestionDto deleteCourseQuestionDto) {
        return teachMapper.deleteCourseQuestion(deleteCourseQuestionDto);
    }

    @Override
    public boolean updateCourseQuestion(UpdateCourseQuestionDto updateCourseQuestionDto) {
        return teachMapper.updateCourseQuestion(updateCourseQuestionDto);
    }

    @Override
    public boolean deleteQuestionSelects(int courseQuestionID) {
        return teachMapper.deleteQuestionSelects(courseQuestionID);
    }

    @Override
    public List<CourseQuestionEntity> getLastCourseQuestion() {
        return teachMapper.getLastCourseQuestion();
    }

    @Override
    public List<CourseQuestionInfoVo> getCourseQuestionInfo(CourseQuestionInfoDto courseQuestionInfoDto) {
        return teachMapper.getCourseQuestionInfo(courseQuestionInfoDto);
    }

    @Override
    public List<QuestionSelectsEntity> getQuestionSelects(int courseQuestionID) {
        return teachMapper.getQuestionSelects(courseQuestionID);
    }

    @Override
    public List<CourseQuestionCopyInfoVo> getCourseQuestionCopyInfo(CourseQuestionCopyInfoDto courseQuestionCopyInfoDto) {
        return teachMapper.getCourseQuestionCopyInfo(courseQuestionCopyInfoDto);
    }

    @Override
    public List<QuestionSelectsCopyVo> getQuestionSelectsCopy(int courseQuestionCopyID) {
        return teachMapper.getQuestionSelectsCopy(courseQuestionCopyID);
    }

    @Override
    public List<CourseContentFilterVo> getCourseContentList(CourseContentFilterDto courseContentFilterDto) {
        PageHelper.startPage(courseContentFilterDto.getPageNum(), courseContentFilterDto.getPageSize());
        return teachMapper.getCourseContentList(courseContentFilterDto);
    }

    @Override
    public List<CourseContentInfoVo> getCourseContentInfo(CourseContentInfoDto courseContentInfoDto) {
        return teachMapper.getCourseContentInfo(courseContentInfoDto);
    }

    @Override
    public List<CourseContentEntity> getCourseContentByID(int courseContentID) {
        return teachMapper.getCourseContentByID(courseContentID);
    }

    @Override
    public List<CourseQuestionCopyEntity> getCourseQuestionCopyList(int questionSize) {
        return teachMapper.getCourseQuestionCopyList(questionSize);
    }

    @Override
    public List<CourseQuestionCopyVo> getCourseQuestionCopyByID(String copyQuestionID) {
        return teachMapper.getCourseQuestionCopyByID(copyQuestionID);
    }

    @Override
    public boolean copyCourseQuestions(JSONArray courseQuestionArray) {
        return teachMapper.copyCourseQuestions(courseQuestionArray);
    }

    @Override
    public void copyQuestionSelects(CopySelectsDto copySelectsDto) {
        teachMapper.copyQuestionSelects(copySelectsDto);
    }

    @Override
    public int getCourseContentListTotalCount(CourseContentFilterDto courseContentFilterDto) {
        return teachMapper.getCourseContentList(courseContentFilterDto).size();
    }

    @Override
    public boolean updateCourseQuestionStatus(UpdateCourseQuestionStatusDto updateCourseQuestionStatusDto) {
        return teachMapper.updateCourseQuestionStatus(updateCourseQuestionStatusDto);
    }

    @Override
    public boolean addCourseContentDto(AddCourseContentDto addCourseContentDto) {
        return teachMapper.addCourseContentDto(addCourseContentDto);
    }

    @Override
    public boolean deleteCourseContent(DeleteCourseContentDto deleteCourseContentDto) {
        return teachMapper.deleteCourseContent(deleteCourseContentDto);
    }

    @Override
    public boolean updateCourseContent(UpdateCourseContentDto updateCourseContentDto) {
        return teachMapper.updateCourseContent(updateCourseContentDto);
    }

    @Override
    public boolean publishCourseContent(PublishCourseContentDto publishCourseContentDto) {
        return teachMapper.publishCourseContent(publishCourseContentDto);
    }

    @Override
    public boolean withdrawCourseContent(WithdrawCourseContentDto withdrawCourseContentDto) {
        return teachMapper.withdrawCourseContent(withdrawCourseContentDto);
    }

    @Override
    public void deleteTask(WithdrawCourseContentDto withdrawCourseContentDto) {
        teachMapper.deleteTask(withdrawCourseContentDto);
    }

    @Override
    public void deleteTaskChilds(WithdrawCourseContentDto withdrawCourseContentDto) {
        teachMapper.deleteTaskChilds(withdrawCourseContentDto);
    }

    @Override
    public List<CourseQuestionEntity> getCourseQuestion(GetCourseQuestionDto getCourseQuestionDto) {
        return teachMapper.getCourseQuestion(getCourseQuestionDto);
    }

    @Override
    public List<CourseContentEntity> getLastCourseContent() {
        return teachMapper.getLastCourseContent();
    }
}