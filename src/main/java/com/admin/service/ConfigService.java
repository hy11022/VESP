package com.admin.service;

import com.admin.pojo.dto.config.AddSchoolDto;
import com.admin.pojo.dto.config.DeleteSchoolDto;
import com.admin.pojo.dto.config.SchoolFilterDto;
import com.admin.pojo.dto.config.UpdateSchoolDto;
import com.admin.pojo.entity.DepartmentEntity;
import com.admin.pojo.entity.SchoolEntity;
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
}