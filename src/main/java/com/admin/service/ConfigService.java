package com.admin.service;

import com.admin.pojo.dto.config.AddLabelDto;
import com.admin.pojo.dto.config.LabelFilterDto;
import com.admin.pojo.dto.common.ProvinceFilterDto;
import com.admin.pojo.dto.config.*;
import com.admin.pojo.entity.*;
import com.admin.pojo.vo.config.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ConfigService {

    List<SchoolListVo> getSchoolList(SchoolFilterDto schoolFilterDto);

    int getSchoolListTotalCount(SchoolFilterDto schoolFilterDto);

    boolean addSchool(AddSchoolDto addSchoolDto);

    boolean deleteSchool(DeleteSchoolDto deleteSchoolDto);

    boolean updateSchool(UpdateSchoolDto updateSchoolDto);

    boolean changeSchoolStatus(UpdateSchoolDto updateSchoolDto);

    List<SchoolEntity> getSchoolByID(int id);

    List<UserEntity> getUserByBelongID(DeleteDto deleteDto);

    List<ClassesEntity> getClassBySpecialityID(int specialityID);

    List<SchoolEntity> getSchoolByName(String name);

    List<SchoolEntity> getSchoolByCode(String code);

    List<SchoolEntity> getSchoolByDto(UpdateSchoolDto updateSchoolDto);

    List<DepartmentEntity> getDepartmentsBySchoolID(int id);

    List<DepartmentListVo> getDepartmentList(DepartmentFilterDto departmentFilterDto);

    List<DepartmentEntity> checkDepartmentByDto(AddDepartmentDto addDepartmentDto);

    List<DepartmentEntity> getDepartmentByDto(UpdateDepartmentDto updateDepartmentDto);

    int getDepartmentListTotalCount(DepartmentFilterDto departmentFilterDto);

    List<SpecialityListVo> getSpecialityList(SpecialityFilterDto specialityFilterDto);

    List<SpecialityEntity> checkSpecialityByDto(AddSpecialityDto addSpecialityDto);

    int getSpecialityListTotalCount(SpecialityFilterDto specialityFilterDto);

    boolean addDepartment(AddDepartmentDto addDepartmentDto);

    List<DepartmentEntity> getDepartmentByID(int id);

    List<SpecialityEntity> getSpecialityByDepartmentID(int id);

    boolean deleteDepartment(DeleteDepartmentDto deleteDepartmentDto);

    boolean updateDepartment(UpdateDepartmentDto updateDepartmentDto);

    boolean changeDepartmentStatus(UpdateDepartmentDto updateDepartmentDto);

    List<FunctionModuleListVo> getFunctionModuleList(FunctionModuleFilterDto functionModuleFilterDto);

    int getFunctionModuleListTotalCount(FunctionModuleFilterDto functionModuleFilterDto);

    List<FunctionModuleEntity> checkFunctionModuleByDto(AddFunctionModuleDto addFunctionModuleDto);

    boolean addFunctionModule(AddFunctionModuleDto addFunctionModuleDto);

    List<FunctionModuleEntity> getFunctionModuleByID(int id);

    List<ProvinceEntity> getProvinceList(ProvinceFilterDto provinceFilterDto);

    int getProvinceListTotalCount(ProvinceFilterDto provinceFilterDto);

    boolean deleteFunctionModule(DeleteFunctionModuleDto deleteFunctionModuleDto);

    boolean updateFunctionModule(UpdateFunctionModuleDto updateFunctionModuleDto);

    boolean updateSpeciality(UpdateSpecialityDto updateSpecialityDto);

    boolean changeFunctionModuleStatus(ChangeFunctionModuleDto changeFunctionModuleDto);

    boolean addSpeciality(AddSpecialityDto addSpecialityDto);

    boolean deleteSpeciality(DeleteSpecialityDto deleteSpecialityDto);

    List<SpecialityEntity> getSpecialitiesByID(int specialityID);

    List<SpecialityEntity> getSpecialitiesByDto(UpdateSpecialityDto updateSpecialityDto);

    List<ClassListVo> getClassList(ClassFilterDto classFilterDto);

    List<ClassesEntity> checkClassByDto(AddClassDto addClassDto);

    List<LabelEntity> getLabelByID(int labelID);

    List<ExperimentEntity> checkLabelByID(int labelID);

    int getClassListTotalCount(ClassFilterDto classFilterDto);

    boolean changeSpecialityStatus(ChangeSpecialityDto changeSpecialityDto);

    boolean addClass(AddClassDto addClassDto);

    boolean deleteClass(DeleteClassDto deleteClassDto);

    boolean deleteLabel(DeleteLabelDto deleteLabelDto);

    boolean updateLabel(UpdateLabelDto updateLabelDto);

    boolean updateLabelStatus(UpdateLabelStatusDto updateLabelStatusDto);

    boolean updateClass(UpdateClassDto updateClassDto);

    boolean changeClassStatus(ChangeClassDto changeClassDto);

    boolean addLabel(AddLabelDto addLabelDto);

    List<ClassesEntity> getClassesByID(int classID);

    List<ClassesEntity> getClassByDto(UpdateClassDto updateClassDto);

    List<LabelEntity> checkLabelByName(AddLabelDto addLabelDto);

    List<LabelEntity> getLabelList(LabelFilterDto labelFilterDto);

    int getLabelListTotalCount(LabelFilterDto labelFilterDto);
}