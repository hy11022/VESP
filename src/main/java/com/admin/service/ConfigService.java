package com.admin.service;

import com.admin.pojo.dto.config.*;
import com.admin.pojo.entity.ClassesEntity;
import com.admin.pojo.entity.DepartmentEntity;
import com.admin.pojo.entity.FunctionModuleEntity;
import com.admin.pojo.entity.SchoolEntity;
import com.admin.pojo.vo.config.DepartmentListVo;
import com.admin.pojo.vo.config.FunctionModuleListVo;
import com.admin.pojo.vo.config.SchoolListVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConfigService {
    List<SchoolListVo> getSchoolList(SchoolFilterDto schoolFilterDto);

    int getSchoolListTotalCount(SchoolFilterDto schoolFilterDto);

    boolean addSchool(AddSchoolDto addSchoolDto);

    boolean deleteSchool(DeleteSchoolDto deleteSchoolDto);

    boolean updateSchool(UpdateSchoolDto updateSchoolDto);

    List<SchoolEntity> getSchoolByID(int id);

    List<SchoolEntity> getSchoolByName(String name);

    List<DepartmentEntity> getDepartmentsBySchoolID(int id);

    List<DepartmentListVo> getDepartmentList(DepartmentFilterDto departmentFilterDto);

    List<DepartmentEntity> checkDepartmentByDto(AddDepartmentDto addDepartmentDto);

    int getDepartmentListTotalCount(DepartmentFilterDto departmentFilterDto);

    boolean addDepartment(AddDepartmentDto addDepartmentDto);

    List<DepartmentEntity> getDepartmentByID(int id);

    List<ClassesEntity> getClassesByDepartmentID(int id);

    boolean deleteDepartment(DeleteDepartmentDto deleteDepartmentDto);

    List<DepartmentEntity> getDepartmentByName(String name);

    boolean updateDepartment(UpdateDepartmentDto updateDepartmentDto);

    List<FunctionModuleListVo> getFunctionModuleList(FunctionModuleFilterDto functionModuleFilterDto);

    int getFunctionModuleListTotalCount(FunctionModuleFilterDto functionModuleFilterDto);

    List<FunctionModuleEntity> checkFunctionModuleByDto(AddFunctionModuleDto addFunctionModuleDto);

    boolean addFunctionModule(AddFunctionModuleDto addFunctionModuleDto);

    List<FunctionModuleEntity> getFunctionModuleByID(int id);

    boolean deleteFunctionModule(DeleteFunctionModuleDto deleteFunctionModuleDto);

    boolean updateFunctionModule(UpdateFunctionModuleDto updateFunctionModuleDto);

    boolean changeFunctionModuleStatus(ChangeFunctionModuleDto changeFunctionModuleDto);
}