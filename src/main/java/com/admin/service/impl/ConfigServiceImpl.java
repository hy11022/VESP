package com.admin.service.impl;

import com.admin.mapper.ConfigMapper;
import com.admin.pojo.dto.config.AddSchoolDto;
import com.admin.pojo.dto.config.DeleteSchoolDto;
import com.admin.pojo.dto.config.SchoolFilterDto;
import com.admin.pojo.dto.config.UpdateSchoolDto;
import com.admin.pojo.entity.DepartmentEntity;
import com.admin.pojo.entity.SchoolEntity;
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
}