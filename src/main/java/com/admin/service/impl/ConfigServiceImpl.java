package com.admin.service.impl;

import com.admin.mapper.ConfigMapper;
import com.admin.pojo.dto.common.ProvinceFilterDto;
import com.admin.pojo.dto.config.*;
import com.admin.pojo.entity.*;
import com.admin.pojo.vo.config.*;
import com.admin.service.ConfigService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    ConfigMapper configMapper;

    @Override
    public List<SchoolListVo> getSchoolList(SchoolFilterDto schoolFilterDto) {
        PageHelper.startPage(schoolFilterDto.getPageNum(), schoolFilterDto.getPageSize());
        return configMapper.getSchoolList(schoolFilterDto);
    }
    @Override
    public int getSchoolListTotalCount(SchoolFilterDto schoolFilterDto) {
        return configMapper.getSchoolList(schoolFilterDto).size();
    }

    @Override
    public boolean addSchool(AddSchoolDto addSchoolDto) {
        return configMapper.addSchool(addSchoolDto);
    }

    @Override
    public boolean deleteSchool(DeleteSchoolDto deleteSchoolDto) {
        return configMapper.deleteSchool(deleteSchoolDto);
    }

    @Override
    public boolean updateSchool(UpdateSchoolDto updateSchoolDto) {
        return configMapper.updateSchool(updateSchoolDto);
    }

    @Override
    public boolean changeSchoolStatus(UpdateSchoolDto updateSchoolDto) {
        return configMapper.changeSchoolStatus(updateSchoolDto);
    }

    @Override
    public List<SchoolEntity> getSchoolByID(int id) {
        return configMapper.getSchoolByID(id);
    }

    @Override
    public List<UserEntity> getUserByBelongID(DeleteDto deleteDto) {
        return configMapper.getUserByBelongID(deleteDto);
    }

    @Override
    public List<ClassesEntity> getClassBySpecialityID(int specialityID) {
        return configMapper.getClassBySpecialityID(specialityID);
    }

    @Override
    public List<SchoolEntity> getSchoolByName(String name) {
        return configMapper.getSchoolByName(name);
    }

    @Override
    public List<SchoolEntity> getSchoolByCode(String code) {
        return configMapper.getSchoolByCode(code);
    }

    @Override
    public List<SchoolEntity> getSchoolByDto(UpdateSchoolDto updateSchoolDto) {
        return configMapper.getSchoolByDto(updateSchoolDto);
    }

    @Override
    public List<DepartmentEntity> getDepartmentsBySchoolID(int id) {
        return configMapper.getDepartmentsBySchoolID(id);
    }

    @Override
    public List<DepartmentListVo> getDepartmentList(DepartmentFilterDto departmentFilterDto) {
        return configMapper.getDepartmentList(departmentFilterDto);
    }

    @Override
    public List<DepartmentEntity> checkDepartmentByDto(AddDepartmentDto addDepartmentDto) {
        return configMapper.checkDepartmentByDto(addDepartmentDto);
    }

    @Override
    public List<DepartmentEntity> getDepartmentByDto(UpdateDepartmentDto updateDepartmentDto) {
        return configMapper.getDepartmentByDto(updateDepartmentDto);
    }

    @Override
    public int getDepartmentListTotalCount(DepartmentFilterDto departmentFilterDto) {
        return configMapper.getDepartmentList(departmentFilterDto).size();
    }

    @Override
    public List<SpecialityListVo> getSpecialityList(SpecialityFilterDto specialityFilterDto) {
        PageHelper.startPage(specialityFilterDto.getPageNum(), specialityFilterDto.getPageSize());
        return configMapper.getSpecialityList(specialityFilterDto);
    }

    @Override
    public List<SpecialityEntity> checkSpecialityByDto(AddSpecialityDto addSpecialityDto) {
        return configMapper.checkSpecialityByDto(addSpecialityDto);
    }

    @Override
    public int getSpecialityListTotalCount(SpecialityFilterDto specialityFilterDto) {
        return configMapper.getSpecialityList(specialityFilterDto).size();
    }

    @Override
    public boolean addDepartment(AddDepartmentDto addDepartmentDto) {
        return configMapper.addDepartment(addDepartmentDto);
    }

    @Override
    public List<DepartmentEntity> getDepartmentByID(int id) {
        return configMapper.getDepartmentByID(id);
    }

    @Override
    public List<SpecialityEntity> getSpecialityByDepartmentID(int id) {
        return configMapper.getSpecialityByDepartmentID(id);
    }

    @Override
    public boolean deleteDepartment(DeleteDepartmentDto deleteDepartmentDto) {
        return configMapper.deleteDepartment(deleteDepartmentDto);
    }

    @Override
    public boolean updateDepartment(UpdateDepartmentDto updateDepartmentDto) {
        return configMapper.updateDepartment(updateDepartmentDto);
    }

    @Override
    public boolean changeDepartmentStatus(UpdateDepartmentDto updateDepartmentDto) {
        return configMapper.changeDepartmentStatus(updateDepartmentDto);
    }

    @Override
    public List<FunctionModuleListVo> getFunctionModuleList(FunctionModuleFilterDto functionModuleFilterDto) {
        PageHelper.startPage(functionModuleFilterDto.getPageNum(), functionModuleFilterDto.getPageSize());
        return configMapper.getFunctionModuleList(functionModuleFilterDto);
    }

    @Override
    public int getFunctionModuleListTotalCount(FunctionModuleFilterDto functionModuleFilterDto) {
        return configMapper.getFunctionModuleList(functionModuleFilterDto).size();
    }

    @Override
    public List<FunctionModuleEntity> checkFunctionModuleByDto(AddFunctionModuleDto addFunctionModuleDto) {
        return configMapper.checkFunctionModuleByDto(addFunctionModuleDto);
    }

    @Override
    public boolean addFunctionModule(AddFunctionModuleDto addFunctionModuleDto) {
        return configMapper.addFunctionModule(addFunctionModuleDto);
    }

    @Override
    public List<FunctionModuleEntity> getFunctionModuleByID(int id) {
        return configMapper.getFunctionModuleByID(id);
    }

    @Override
    public List<ProvinceEntity> getProvinceList(ProvinceFilterDto provinceFilterDto) {
        PageHelper.startPage(provinceFilterDto.getPageNum(), provinceFilterDto.getPageSize());
        return configMapper.getProvinceList(provinceFilterDto);
    }

    @Override
    public int getProvinceListTotalCount(ProvinceFilterDto provinceFilterDto) {
        return configMapper.getProvinceList(provinceFilterDto).size();
    }

    @Override
    public boolean deleteFunctionModule(DeleteFunctionModuleDto deleteFunctionModuleDto) {
        return configMapper.deleteFunctionModule(deleteFunctionModuleDto);
    }

    @Override
    public boolean updateFunctionModule(UpdateFunctionModuleDto updateFunctionModuleDto) {
        return configMapper.updateFunctionModule(updateFunctionModuleDto);
    }

    @Override
    public boolean updateSpeciality(UpdateSpecialityDto updateSpecialityDto) {
        return configMapper.updateSpeciality(updateSpecialityDto);
    }

    @Override
    public boolean changeFunctionModuleStatus(ChangeFunctionModuleDto changeFunctionModuleDto) {
        return configMapper.changeFunctionModuleStatus(changeFunctionModuleDto);
    }

    @Override
    public boolean addSpeciality(AddSpecialityDto addSpecialityDto) {
        return configMapper.addSpeciality(addSpecialityDto);
    }

    @Override
    public boolean deleteSpeciality(DeleteSpecialityDto deleteSpecialityDto) {
        return configMapper.deleteSpeciality(deleteSpecialityDto);
    }

    @Override
    public List<SpecialityEntity> getSpecialitiesByID(int specialityID) {
        return configMapper.getSpecialitiesByID(specialityID);
    }

    @Override
    public List<ClassListVo> getClassList(ClassFilterDto classFilterDto) {
        PageHelper.startPage(classFilterDto.getPageNum(), classFilterDto.getPageSize());
        return configMapper.getClassList(classFilterDto);
    }

    @Override
    public List<ClassesEntity> checkClassByDto(AddClassDto addClassDto) {
        return configMapper.checkClassByDto(addClassDto);
    }

    @Override
    public int getClassListTotalCount(ClassFilterDto classFilterDto) {
        return configMapper.getClassList(classFilterDto).size();
    }

    @Override
    public boolean changeSpecialityStatus(ChangeSpecialityDto changeSpecialityDto) {
        return configMapper.changeSpecialityStatus(changeSpecialityDto);
    }

    @Override
    public boolean addClass(AddClassDto addClassDto) {
        return configMapper.addClass(addClassDto);
    }

    @Override
    public boolean deleteClass(DeleteClassDto deleteClassDto) {
        return configMapper.deleteClass(deleteClassDto);
    }

    @Override
    public boolean updateClass(UpdateClassDto updateClassDto) {
        return configMapper.updateClass(updateClassDto);
    }

    @Override
    public boolean changeClassStatus(ChangeClassDto changeClassDto) {
        return configMapper.changeClassStatus(changeClassDto);
    }

    @Override
    public List<ClassesEntity> getClassesByID(int classID) {
        return configMapper.getClassesByID(classID);
    }
}