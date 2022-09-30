package com.admin.service;

import com.admin.pojo.dto.teach.*;
import com.admin.pojo.entity.CourseEntity;
import com.admin.pojo.vo.teach.CourseFilterVo;
import com.admin.pojo.vo.teach.CourseInfoVo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TeachService {

    List<CourseFilterVo> getCourseList(CourseFilterDto courseFilterDto);

    int getCourseListTotalCount(CourseFilterDto courseFilterDto);

    boolean addCourse(AddCourseDto addCourseDto);

    List<CourseEntity> getCourseByID(int courseID);

    boolean deleteCourse(DeleteCourseDto deleteCourseDto);

    boolean updateCourse(UpdateCourseDto updateCourseDto);

    boolean updateCourseStatus(UpdateCourseStatusDto updateCourseStatusDto);

    List<CourseInfoVo> getCourseInfo(CourseInfoDto courseInfoDto);
}