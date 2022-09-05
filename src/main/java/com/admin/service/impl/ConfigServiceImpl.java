package com.admin.service.impl;

import com.admin.mapper.ConfigMapper;
import com.admin.pojo.dto.config.*;
import com.admin.pojo.entity.ClassesEntity;
import com.admin.pojo.entity.DepartmentEntity;
import com.admin.pojo.entity.FunctionModuleEntity;
import com.admin.pojo.entity.SchoolEntity;
import com.admin.pojo.vo.config.DepartmentListVo;
import com.admin.pojo.vo.config.FunctionModuleListVo;
import com.admin.pojo.vo.config.SchoolListVo;
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
    public List<SchoolEntity> getSchoolByID(int id) {
        return configMapper.getSchoolByID(id);
    }

    @Override
    public List<SchoolEntity> getSchoolByName(String name) {
        return configMapper.getSchoolByName(name);
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
    public int getDepartmentListTotalCount(DepartmentFilterDto departmentFilterDto) {
        return configMapper.getDepartmentList(departmentFilterDto).size();
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
    public List<ClassesEntity> getClassesByDepartmentID(int id) {
        return configMapper.getClassesByDepartmentID(id);
    }

    @Override
    public boolean deleteDepartment(DeleteDepartmentDto deleteDepartmentDto) {
        return configMapper.deleteDepartment(deleteDepartmentDto);
    }

    @Override
    public List<DepartmentEntity> getDepartmentByName(String name) {
        return configMapper.getDepartmentByName(name);
    }

    @Override
    public boolean updateDepartment(UpdateDepartmentDto updateDepartmentDto) {
        return configMapper.updateDepartment(updateDepartmentDto);
    }

    @Override
    public List<FunctionModuleListVo> getFunctionModuleList(FunctionModuleFilterDto functionModuleFilterDto) {
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
    public boolean deleteFunctionModule(DeleteFunctionModuleDto deleteFunctionModuleDto) {
        return configMapper.deleteFunctionModule(deleteFunctionModuleDto);
    }

    @Override
    public boolean updateFunctionModule(UpdateFunctionModuleDto updateFunctionModuleDto) {
        return configMapper.updateFunctionModule(updateFunctionModuleDto);
    }

    @Override
    public boolean changeFunctionModuleStatus(ChangeFunctionModuleDto changeFunctionModuleDto) {
        return configMapper.changeFunctionModuleStatus(changeFunctionModuleDto);
    }
}