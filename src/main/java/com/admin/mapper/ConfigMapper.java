package com.admin.mapper;

import com.admin.pojo.dto.common.ProvinceFilterDto;
import com.admin.pojo.dto.config.*;
import com.admin.pojo.entity.*;
import com.admin.pojo.vo.config.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ConfigMapper {

    List<SchoolListVo> getSchoolList(SchoolFilterDto schoolFilterDto);

    @Insert("INSERT INTO schools SET name = #{name}, create_time = #{createTime}, status = #{status},code=#{code}")
    boolean addSchool(AddSchoolDto addSchoolDto);

    @Select("SELECT * FROM schools WHERE id = #{id}")
    List<SchoolEntity> getSchoolByID(int id);

    @Select("SELECT * FROM schools WHERE name = #{name}")
    List<SchoolEntity> getSchoolByName(String name);

    @Select("SELECT * FROM schools WHERE code = #{code}")
    List<SchoolEntity> getSchoolByCode(String code);

    @Select("SELECT * FROM departments WHERE school_id = #{id}")
    List<DepartmentEntity> getDepartmentsBySchoolID(int id);

    @Delete("DELETE FROM schools WHERE id = #{id}")
    boolean deleteSchool(DeleteSchoolDto deleteSchoolDto);

    @Update("UPDATE schools SET name = #{name},update_time=#{updateTime},code=${code} WHERE id = #{id}")
    boolean updateSchool(UpdateSchoolDto updateSchoolDto);

    @Update("UPDATE schools SET status = #{status},update_time=#{updateTime} WHERE id = #{id}")
    boolean changeSchoolStatus(UpdateSchoolDto updateSchoolDto);

    List<DepartmentListVo> getDepartmentList(DepartmentFilterDto departmentFilterDto);

    @Select("SELECT * FROM departments WHERE school_id = #{schoolID} AND (name = #{name} OR code =#{code})")
    List<DepartmentEntity> checkDepartmentByDto(AddDepartmentDto addDepartmentDto);

    @Select("SELECT * FROM departments WHERE (code =#{code} OR name = #{name}) " +
            "AND id <>#{id} AND school_id = #{schoolID}")
    List<DepartmentEntity> getDepartmentByDto(UpdateDepartmentDto updateDepartmentDto);

    @Insert("INSERT INTO departments SET name=#{name},school_id=#{schoolID}," +
            "create_time = #{createTime},status=#{status},code =#{code}")
    boolean addDepartment(AddDepartmentDto addDepartmentDto);

    @Select("SELECT * FROM departments WHERE id=#{id}")
    List<DepartmentEntity> getDepartmentByID(int id);

    @Select("SELECT * FROM specialities WHERE department_id=#{id}")
    List<SpecialityEntity> getSpecialityByDepartmentID(int id);

    @Select("SELECT * FROM classes WHERE speciality_id=#{id}")
    List<ClassesEntity> getClassBySpecialityID(int specialityID);

    @Select("SELECT * FROM classes WHERE (code =#{code} OR name = #{name}) " +
            "AND id <>#{id} AND speciality_id = #{specialityID}")
    List<ClassesEntity> getClassByDto(UpdateClassDto updateClassDto);

    @Delete("DELETE FROM departments WHERE id=#{id}")
    boolean deleteDepartment(DeleteDepartmentDto deleteDepartmentDto);

    @Update(" UPDATE departments SET name = #{name},code=#{code},update_time=#{updateTime} WHERE id = #{id}")
    boolean updateDepartment(UpdateDepartmentDto updateDepartmentDto);

    @Update(" UPDATE departments SET status = #{status},update_time=#{updateTime} WHERE id = #{id}")
    boolean changeDepartmentStatus(UpdateDepartmentDto updateDepartmentDto);

    List<FunctionModuleListVo> getFunctionModuleList(FunctionModuleFilterDto functionModuleFilterDto);

    @Select("SELECT * FROM function_modules WHERE name = #{name}")
    List<FunctionModuleEntity> checkFunctionModuleByDto(AddFunctionModuleDto addFunctionModuleDto);

    @Insert("INSERT INTO function_modules SET name = #{name},remark = #{remark}," +
            "cover_img = #{coverImg},is_need_login=#{isNeedLogin}," +
            "head_img = #{headImg},path=#{path},path_type=#{pathType}," +
            "create_time=#{createTime},role_limits = #{roleLimits}")
    boolean addFunctionModule(AddFunctionModuleDto addFunctionModuleDto);

    @Select("SELECT * FROM function_modules WHERE id=#{id}")
    List<FunctionModuleEntity> getFunctionModuleByID(int id);

    @Delete("DELETE FROM function_modules WHERE id=#{id}")
    boolean deleteFunctionModule(DeleteFunctionModuleDto deleteFunctionModuleDto);

    @Update("UPDATE function_modules SET name = #{name},remark = #{remark},cover_img = #{coverImg}," +
            "role_limits = #{roleLimits},is_need_login=#{isNeedLogin}, head_img = #{headImg}," +
            "path = #{path},path_type = #{pathType},update_time=#{updateTime} WHERE id= #{id}")
    boolean updateFunctionModule(UpdateFunctionModuleDto updateFunctionModuleDto);

    @Update("UPDATE function_modules SET status = #{status},update_time=#{updateTime} WHERE id=#{id}")
    boolean changeFunctionModuleStatus(ChangeFunctionModuleDto changeFunctionModuleDto);

    List<ProvinceEntity> getProvinceList(ProvinceFilterDto provinceFilterDto);

    @Select("SELECT * FROM schools WHERE (name = #{name} OR code =#{code}) AND id <>#{id}")
    List<SchoolEntity> getSchoolByDto(UpdateSchoolDto updateSchoolDto);

    List<SpecialityListVo> getSpecialityList(SpecialityFilterDto specialityFilterDto);

    @Select("SELECT * FROM specialities WHERE (code =#{code} OR name = #{name}) AND department_id =#{departmentID}")
    List<SpecialityEntity> checkSpecialityByDto(AddSpecialityDto addSpecialityDto);

    @Insert("INSERT INTO specialities SET code = #{code},name =#{name}," +
            "department_id=#{departmentID},create_time=#{createTime},status=#{status}")
    boolean addSpeciality(AddSpecialityDto addSpecialityDto);

    @Select("SELECT * FROM specialities WHERE id = #{specialityID}")
    List<SpecialityEntity> getSpecialitiesByID(int specialityID);

    @Select("SELECT * FROM specialities WHERE (code =#{code} OR name = #{name}) " +
            "AND id <>#{id} AND department_id = #{departmentID}")
    List<SpecialityEntity> getSpecialitiesByDto(UpdateSpecialityDto updateSpecialityDto);

    @Delete("DELETE FROM specialities WHERE id = #{id}")
    boolean deleteSpeciality(DeleteSpecialityDto deleteSpecialityDto);

    @Update("UPDATE specialities SET name = #{name},code =#{code},update_time = #{updateTime} WHERE id= #{id}")
    boolean updateSpeciality(UpdateSpecialityDto updateSpecialityDto);

    @Update("UPDATE specialities SET status = #{status},update_time = #{updateTime} WHERE id= #{id}")
    boolean changeSpecialityStatus(ChangeSpecialityDto changeSpecialityDto);

    List<ClassListVo> getClassList(ClassFilterDto classFilterDto);

    @Select("SELECT * FROM classes WHERE (code =#{code} OR name = #{name}) AND speciality_id =#{specialityID}")
    List<ClassesEntity> checkClassByDto(AddClassDto addClassDto);

    @Insert("INSERT INTO classes SET code=#{code},name = #{name},speciality_id=#{specialityID}," +
            "begin_year=#{beginYear},create_time=#{createTime},status=#{status}")
    boolean addClass(AddClassDto addClassDto);

    @Select("SELECT * FROM classes WHERE id = #{classID}")
    List<ClassesEntity> getClassesByID(int classID);

    @Delete("DELETE FROM classes WHERE id = #{id}")
    boolean deleteClass(DeleteClassDto deleteClassDto);

    @Update("UPDATE classes SET code=#{code},name = #{name},begin_year=#{beginYear}," +
            "update_time=#{updateTime} WHERE id=#{id}")
    boolean updateClass(UpdateClassDto updateClassDto);

    @Update("UPDATE classes SET status = #{status},update_time = #{updateTime} WHERE id= #{id}")
    boolean changeClassStatus(ChangeClassDto changeClassDto);

    @Select("SELECT * FROM users WHERE auth_level=#{authLevel} AND belong_id=#{belongID}")
    List<UserEntity> getUserByBelongID(DeleteDto deleteDto);

    List<LabelListVo> getLabelList(LabelFilterDto labelFilterDto);

    @Select("SELECT * FROM labels WHERE name=#{name} AND type =#{type} AND effect = #{effect}")
    List<LabelEntity> checkLabelByName(AddLabelDto addLabelDto);

    @Insert("INSERT INTO labels SET name = #{name},type=#{type},effect=#{effect}, create_time=#{createTime}")
    boolean addLabel(AddLabelDto addLabelDto);

    @Select("SELECT * FROM labels WHERE id=#{labelID}")
    List<LabelEntity> getLabelByID(int labelID);

    List<ExperimentEntity> checkLabelByID(int labelID);

    @Delete("DELETE FROM labels WHERE id = #{id}")
    boolean deleteLabel(DeleteLabelDto deleteLabelDto);

    @Update("UPDATE labels SET name = #{name},type = #{type},effect=#{effect}," +
            "update_time = #{updateTime} WHERE id= #{id}")
    boolean updateLabel(UpdateLabelDto updateLabelDto);

    @Update("UPDATE labels SET status = #{status},update_time = #{updateTime} WHERE id= #{id}")
    boolean updateLabelStatus(UpdateLabelStatusDto updateLabelStatusDto);

    List<ClassCourseFilterVo> getClassCourseList(ClassCourseFilterDto classCourseFilterDto);

    @Select("SELECT * FROM rel_class_course WHERE class_id=#{classID} AND term_id=#{termID} AND course_id=#{courseID}")
    List<ClassCourseEntity> checkClassCourseByDto(AddClassCourseDto addClassCourseDto);

    @Insert("INSERT INTO rel_class_course SET class_id = #{classID},term_id=#{termID}," +
            "course_id=#{courseID}, create_time=#{createTime}")
    boolean addClassCourse(AddClassCourseDto addClassCourseDto);

    @Delete("DELETE FROM rel_class_course WHERE id = #{id}")
    boolean deleteClassCourse(DeleteClassCourseDto deleteClassCourseDto);

    @Select("SELECT * FROM rel_class_course WHERE id=#{classCourseID}")
    List<ClassCourseEntity> getClassCourseByID(int classCourseID);

    @Select("SELECT * FROM rel_class_course WHERE course_id=#{courseID} AND term_id =#{termID} " +
            "AND class_id =#{classID} AND id<>#{id}")
    List<ClassCourseEntity> checkTermCourseByDto(UpdateClassCourseDto updateClassCourseDto);

    @Update("UPDATE rel_class_course SET term_id = #{termID},course_id = #{courseID},update_time = #{updateTime} WHERE id= #{id}")
    boolean updateClassCourse(UpdateClassCourseDto updateClassCourseDto);

    @Update("UPDATE rel_class_course SET status = #{status},update_time = #{updateTime} WHERE id= #{id}")
    boolean updateClassCourseStatus(UpdateClassCourseStatusDto updateClassCourseStatusDto);
}