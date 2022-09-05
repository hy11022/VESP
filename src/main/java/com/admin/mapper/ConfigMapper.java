package com.admin.mapper;

import com.admin.pojo.dto.config.*;
import com.admin.pojo.entity.ClassesEntity;
import com.admin.pojo.entity.DepartmentEntity;
import com.admin.pojo.entity.FunctionModuleEntity;
import com.admin.pojo.entity.SchoolEntity;
import com.admin.pojo.vo.config.DepartmentListVo;
import com.admin.pojo.vo.config.FunctionModuleListVo;
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

    List<DepartmentListVo> getDepartmentList(DepartmentFilterDto departmentFilterDto);

    @Select("SELECT * FROM departments WHERE school_id = #{schoolID} AND name = #{name}")
    List<DepartmentEntity> checkDepartmentByDto(AddDepartmentDto addDepartmentDto);

    @Insert("INSERT INTO departments SET name=#{name},school_id=#{schoolID},update_time = #{updateTime},status=#{status}")
    boolean addDepartment(AddDepartmentDto addDepartmentDto);

    @Select("SELECT * FROM departments WHERE id=#{id}")
    List<DepartmentEntity> getDepartmentByID(int id);

    @Select("SELECT * FROM classes WHERE department_id=#{id}")
    List<ClassesEntity> getClassesByDepartmentID(int id);

    @Delete("DELETE FROM departments WHERE id=#{id}")
    boolean deleteDepartment(DeleteDepartmentDto deleteDepartmentDto);

    @Select("SELECT * FROM departments WHERE name = #{name}")
    List<DepartmentEntity> getDepartmentByName(String name);

    boolean updateDepartment(UpdateDepartmentDto updateDepartmentDto);

    List<FunctionModuleListVo> getFunctionModuleList(FunctionModuleFilterDto functionModuleFilterDto);

    @Select("SELECT * FROM function_modules WHERE name = #{name}")
    List<FunctionModuleEntity> checkFunctionModuleByDto(AddFunctionModuleDto addFunctionModuleDto);

    @Insert("INSERT INTO function_modules SET name = #{name},remark = #{remark},cover_img = #{coverImg},head_img = #{headImg},path=#{path}")
    boolean addFunctionModule(AddFunctionModuleDto addFunctionModuleDto);

    @Select("SELECT * FROM function_modules WHERE id=#{id}")
    List<FunctionModuleEntity> getFunctionModuleByID(int id);

    @Delete("DELETE FROM function_modules WHERE id=#{id}")
    boolean deleteFunctionModule(DeleteFunctionModuleDto deleteFunctionModuleDto);

    @Update("UPDATE function_modules SET name = #{name},remark = #{remark},cover_img = #{coverImg}," +
            "head_img = #{headImg},path = #{path} WHERE id= #{id}")
    boolean updateFunctionModule(UpdateFunctionModuleDto updateFunctionModuleDto);

    @Update("UPDATE function_modules SET status = #{status} WHERE id=#{id}")
    boolean changeFunctionModuleStatus(ChangeFunctionModuleDto changeFunctionModuleDto);
}