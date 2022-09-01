package com.admin.controller;

import com.admin.pojo.dto.config.AddSchoolDto;
import com.admin.pojo.dto.config.DeleteSchoolDto;
import com.admin.pojo.dto.config.SchoolFilterDto;
import com.admin.pojo.dto.config.UpdateSchoolDto;
import com.admin.pojo.entity.DepartmentEntity;
import com.admin.pojo.entity.SchoolEntity;
import com.admin.pojo.vo.config.SchoolListVo;
import com.admin.service.ConfigService;
import com.admin.util.CommonUtils;
import com.admin.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @Autowired
    private DataSourceTransactionManager transactionManager;

    //获取学校列表
    @PostMapping("/getSchoolList")
    public Result getSchoolList(@Validated @RequestBody SchoolFilterDto schoolFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<SchoolListVo> schoolList = configService.getSchoolList(schoolFilterDto);
        int totalCount = configService.getSchoolListTotalCount(schoolFilterDto);
        return Result.showList("00000000", "Success", schoolList, totalCount);
    }

    //新增学校
    @PostMapping("/addSchool")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addSchool(@Validated @RequestBody AddSchoolDto addSchoolDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        String schoolName = addSchoolDto.getName();
        List<SchoolEntity> schoolList = configService.getSchoolByName(schoolName);
        if (schoolList.size() > 0) {
            return Result.showInfo("00000002", "该学校名称已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addSchoolDto.setUpdateTime(now);
        addSchoolDto.setStatus("1");
        boolean isSuccess = configService.addSchool(addSchoolDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000002", "新增失败", null);
        }
    }

    //删除学校
    @PostMapping("/deleteSchool")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteSchool(@Validated @RequestBody DeleteSchoolDto deleteSchoolDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int schoolID = deleteSchoolDto.getId();
        List<SchoolEntity> schoolList = configService.getSchoolByID(schoolID);
        if (schoolList.size() < 1) {
            return Result.showInfo("00000002", "查无此学校", null);
        }
        List<DepartmentEntity> departmentList = configService.getDepartmentsBySchoolID(schoolID);
        if (departmentList.size() > 0) {
            return Result.showInfo("00000002", "该学校尚有院系存在，不可删除", null);
        }
        Boolean isSuccess = configService.deleteSchool(deleteSchoolDto);
        if (isSuccess ) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000002", "删除失败", null);
        }
    }

    //更新学校
    @PostMapping("/updateSchool")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateSchool(@Validated @RequestBody UpdateSchoolDto updateSchoolDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int schoolID = updateSchoolDto.getId();
        String schoolName = updateSchoolDto.getName();
        List<SchoolEntity> schoolList1 = configService.getSchoolByID(schoolID);
        if (schoolList1.size() < 1) {
            return Result.showInfo("00000002", "查无此学校", null);
        }
        List<SchoolEntity> schoolList2 = configService.getSchoolByName(schoolName);
        if (schoolList2.size() > 0) {
            return Result.showInfo("00000002", "该学校名称已存在", null);
        }
        Boolean isSuccess = configService.updateSchool(updateSchoolDto);
        if (isSuccess ) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000002", "删除失败", null);
        }
    }
}
