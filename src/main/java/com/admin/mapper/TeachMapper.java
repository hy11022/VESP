package com.admin.mapper;

import com.admin.pojo.dto.teach.*;
import com.admin.pojo.entity.CourseEntity;
import com.admin.pojo.vo.teach.CourseFilterVo;
import com.admin.pojo.vo.teach.CourseInfoVo;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface TeachMapper {

    List<CourseFilterVo> getCourseList(CourseFilterDto courseFilterDto);

    @Insert("INSERT INTO courses SET name = #{name},department_id=#{departmentID}," +
            "introduction = #{introduction},cover=#{cover},update_time=#{updateTime}")
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
}