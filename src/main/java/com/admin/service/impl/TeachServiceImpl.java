package com.admin.service.impl;

import com.admin.mapper.TeachMapper;
import com.admin.pojo.dto.teach.*;
import com.admin.pojo.entity.CourseEntity;
import com.admin.pojo.vo.teach.CourseFilterVo;
import com.admin.pojo.vo.teach.CourseInfoVo;
import com.admin.service.TeachService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @BelongsProject: SPCA-end
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
}
