package com.admin.mapper;

import com.admin.pojo.dto.config.AddSchoolDto;
import com.admin.pojo.dto.config.DeleteSchoolDto;
import com.admin.pojo.dto.config.SchoolFilterDto;
import com.admin.pojo.dto.config.UpdateSchoolDto;
import com.admin.pojo.entity.DepartmentEntity;
import com.admin.pojo.entity.SchoolEntity;
import com.admin.pojo.vo.config.SchoolListVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConfigMapper {
    List<SchoolListVo> getSchoolList(SchoolFilterDto schoolFilterDto);

    @Insert("INSERT INTO schools SET name = #{name}, update_time = #{updateTime},status = #{status}")
    boolean addSchool(AddSchoolDto addSchoolDto);

    @Select("SELECT * FROM schools WHERE id = #{id}")
    List<SchoolEntity> getSchoolByID(int id);

    @Select("SELECT * FROM schools WHERE name = #{name}")
    List<SchoolEntity> getSchoolByName(String name);

    @Select("SELECT * FROM departments WHERE school_id = #{id}")
    List<DepartmentEntity> getDepartmentsBySchoolID(int id);

    @Delete("DELETE FROM schools WHERE id = #{id}")
    boolean deleteSchool(DeleteSchoolDto deleteSchoolDto);

    boolean updateSchool(UpdateSchoolDto updateSchoolDto);
}