package com.admin.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.validation.BindingResult;
import com.admin.pojo.dto.config.LabelFilterDto;
import com.admin.pojo.dto.config.AddLabelDto;
import com.admin.service.ConfigService;
import com.admin.pojo.dto.config.*;
import com.admin.util.CommonUtils;
import com.admin.pojo.vo.config.*;
import com.admin.pojo.entity.*;
import com.admin.util.Result;
import java.util.Objects;
import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

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
        String name = addSchoolDto.getName();
        String code = addSchoolDto.getCode();
        List<SchoolEntity> schoolList1 = configService.getSchoolByName(name);
        List<SchoolEntity> schoolList2 = configService.getSchoolByCode(code);
        if (schoolList1.size() > 0) {
            return Result.showInfo("00000002", "该学校名称已存在", null);
        }
        if (schoolList2.size() > 0) {
            return Result.showInfo("00000003", "该学校编号已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addSchoolDto.setUpdateTime(now);
        addSchoolDto.setStatus("1");
        boolean isSuccess = configService.addSchool(addSchoolDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "新增失败", null);
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
            return Result.showInfo("00000003", "该学校尚有院系存在，不可删除", null);
        }
        DeleteDto deleteDto = new DeleteDto();
        deleteDto.setAuthLevel(4);
        deleteDto.setBelongID(schoolID);
        List<UserEntity> userList = configService.getUserByBelongID(deleteDto);
        if (userList.size() > 0) {
            return Result.showInfo("00000004", "该学校尚有用户存在，不可删除", null);
        }
        boolean isSuccess = configService.deleteSchool(deleteSchoolDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000005", "删除失败", null);
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
        List<SchoolEntity> schoolList1 = configService.getSchoolByID(schoolID);
        if (schoolList1.size() < 1) {
            return Result.showInfo("00000002", "查无此学校", null);
        }
        List<SchoolEntity> schoolList2 = configService.getSchoolByDto(updateSchoolDto);
        if (schoolList2.size() > 0) {
            return Result.showInfo("00000003", "该学校名称或编号重复", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateSchoolDto.setUpdateTime(now);
        boolean isSuccess = configService.updateSchool(updateSchoolDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "修改失败", null);
        }
    }

    //修改学校状态
    @PostMapping("/changeSchoolStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result changeSchoolStatus(@Validated @RequestBody UpdateSchoolDto updateSchoolDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int schoolID = updateSchoolDto.getId();
        List<SchoolEntity> schoolList1 = configService.getSchoolByID(schoolID);
        if (schoolList1.size() < 1) {
            return Result.showInfo("00000002", "查无此学校", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateSchoolDto.setUpdateTime(now);
        boolean isSuccess = configService.changeSchoolStatus(updateSchoolDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "修改失败", null);
        }
    }

    //获取院系列表
    @PostMapping("/getDepartmentList")
    public Result getDepartmentList(@Validated @RequestBody DepartmentFilterDto departmentFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<DepartmentListVo> departmentList = configService.getDepartmentList(departmentFilterDto);
        int totalCount = configService.getDepartmentListTotalCount(departmentFilterDto);
        return Result.showList("00000000", "Success", departmentList, totalCount);
    }

    //新增院系
    @PostMapping("/addDepartment")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addDepartment(@Validated @RequestBody AddDepartmentDto addDepartmentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<DepartmentEntity> departmentList = configService.checkDepartmentByDto(addDepartmentDto);
        if (departmentList.size() > 0) {
            return Result.showInfo("00000002", "该院系已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addDepartmentDto.setUpdateTime(now);
        addDepartmentDto.setStatus("1");
        boolean isSuccess = configService.addDepartment(addDepartmentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除院系
    @PostMapping("/deleteDepartment")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteDepartment(@Validated @RequestBody DeleteDepartmentDto deleteDepartmentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int departmentID = deleteDepartmentDto.getId();
        List<DepartmentEntity> departmentList = configService.getDepartmentByID(departmentID);
        if (departmentList.size() < 1) {
            return Result.showInfo("00000002", "查无此院系", null);
        }
        List<SpecialityEntity> specialityList = configService.getSpecialityByDepartmentID(departmentID);
        if (specialityList.size() > 0) {
            return Result.showInfo("00000003", "该院系尚有专业存在，不可删除", null);
        }
        DeleteDto deleteDto = new DeleteDto();
        deleteDto.setAuthLevel(3);
        deleteDto.setBelongID(departmentID);
        List<UserEntity> userList = configService.getUserByBelongID(deleteDto);
        if (userList.size() > 0) {
            return Result.showInfo("00000004", "该院系尚有用户存在，不可删除", null);
        }
        boolean isSuccess = configService.deleteDepartment(deleteDepartmentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000005", "删除失败", null);
        }
    }

    //更新学院
    @PostMapping("/updateDepartment")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateDepartment(@Validated @RequestBody UpdateDepartmentDto updateDepartmentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int departmentID = updateDepartmentDto.getId();
        List<DepartmentEntity> departmentList1 = configService.getDepartmentByID(departmentID);
        if (departmentList1.size() < 1) {
            return Result.showInfo("00000002", "查无此院系", null);
        }
        updateDepartmentDto.setSchoolID(departmentList1.get(0).getSchoolID());
        List<DepartmentEntity> departmentList2 = configService.getDepartmentByDto(updateDepartmentDto);
        if (departmentList2.size() > 0) {
            return Result.showInfo("00000003", "该院系名称或编号重复", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateDepartmentDto.setUpdateTime(now);
        boolean isSuccess = configService.updateDepartment(updateDepartmentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "更新失败", null);
        }
    }

    //修改院系状态
    @PostMapping("/changeDepartmentStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result changeDepartmentStatus(@Validated @RequestBody UpdateDepartmentDto updateDepartmentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int departmentID = updateDepartmentDto.getId();
        List<DepartmentEntity> departmentList1 = configService.getDepartmentByID(departmentID);
        if (departmentList1.size() < 1) {
            return Result.showInfo("00000002", "查无此院系", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateDepartmentDto.setUpdateTime(now);
        boolean isSuccess = configService.changeDepartmentStatus(updateDepartmentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "修改失败", null);
        }
    }

    //获取专业列表
    @PostMapping("/getSpecialityList")
    public Result getSpecialityList(@Validated @RequestBody SpecialityFilterDto specialityFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<SpecialityListVo> specialityList = configService.getSpecialityList(specialityFilterDto);
        int totalCount = configService.getSpecialityListTotalCount(specialityFilterDto);
        return Result.showList("00000000", "Success", specialityList, totalCount);
    }

    //新增专业
    @PostMapping("/addSpeciality")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addSpeciality(@Validated @RequestBody AddSpecialityDto addSpecialityDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<SpecialityEntity> specialityList = configService.checkSpecialityByDto(addSpecialityDto);
        if (specialityList.size() > 0) {
            return Result.showInfo("00000002", "该专业编号或名称已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addSpecialityDto.setUpdateTime(now);
        addSpecialityDto.setStatus("1");
        boolean isSuccess = configService.addSpeciality(addSpecialityDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除专业
    @PostMapping("/deleteSpeciality")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteSpeciality(@Validated @RequestBody DeleteSpecialityDto deleteSpecialityDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int specialityID = deleteSpecialityDto.getId();
        List<SpecialityEntity> specialitiesList = configService.getSpecialitiesByID(specialityID);
        if (specialitiesList.size() < 1) {
            return Result.showInfo("00000002", "查无此专业", null);
        }
        List<ClassesEntity> calssList = configService.getClassBySpecialityID(specialityID);
        if (calssList.size() > 0) {
            return Result.showInfo("00000003", "该专业尚有班级存在，不可删除", null);
        }
        DeleteDto deleteDto = new DeleteDto();
        deleteDto.setAuthLevel(2);
        deleteDto.setBelongID(specialityID);
        List<UserEntity> userList = configService.getUserByBelongID(deleteDto);
        if (userList.size() > 0) {
            return Result.showInfo("00000004", "该专业尚有用户存在，不可删除", null);
        }
        boolean isSuccess = configService.deleteSpeciality(deleteSpecialityDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000005", "删除失败", null);
        }
    }

    //更新专业
    @PostMapping("/updateSpeciality")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateSpeciality(@Validated @RequestBody UpdateSpecialityDto updateSpecialityDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int specialityID = updateSpecialityDto.getId();
        List<SpecialityEntity> specialitiesList = configService.getSpecialitiesByID(specialityID);
        if (specialitiesList.size() < 1) {
            return Result.showInfo("00000002", "查无此专业", null);
        }
        updateSpecialityDto.setDepartmentID(specialitiesList.get(0).getDepartmentID());
        List<SpecialityEntity> specialitiesList2 = configService.getSpecialitiesByDto(updateSpecialityDto);
        if (specialitiesList2.size() > 0) {
            return Result.showInfo("00000003", "该专业名称或编号重复", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateSpecialityDto.setUpdateTime(now);
        boolean isSuccess = configService.updateSpeciality(updateSpecialityDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更改专业状态
    @PostMapping("/changeSpecialityStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result changeSpecialityStatus(@Validated @RequestBody ChangeSpecialityDto changeSpecialityDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int specialityID = changeSpecialityDto.getId();
        List<SpecialityEntity> specialitiesList = configService.getSpecialitiesByID(specialityID);
        if (specialitiesList.size() < 1) {
            return Result.showInfo("00000002", "查无此专业", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        changeSpecialityDto.setUpdateTime(now);
        boolean isSuccess = configService.changeSpecialityStatus(changeSpecialityDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更改失败", null);
        }
    }

    //获取班级列表
    @PostMapping("/getClassList")
    public Result getClassList(@Validated @RequestBody ClassFilterDto classFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ClassListVo> classList = configService.getClassList(classFilterDto);
        int totalCount = configService.getClassListTotalCount(classFilterDto);
        return Result.showList("00000000", "Success", classList, totalCount);
    }

    //新增班级
    @PostMapping("/addClass")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addClass(@Validated @RequestBody AddClassDto addClassDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ClassesEntity> classList = configService.checkClassByDto(addClassDto);
        if (classList.size() > 0) {
            return Result.showInfo("00000002", "该班级编号或名称已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addClassDto.setUpdateTime(now);
        addClassDto.setStatus("1");
        boolean isSuccess = configService.addClass(addClassDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除班级
    @PostMapping("/deleteClass")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteClass(@Validated @RequestBody DeleteClassDto deleteClassDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int classID = deleteClassDto.getId();
        List<ClassesEntity> classesList = configService.getClassesByID(classID);
        if (classesList.size() < 1) {
            return Result.showInfo("00000002", "查无此班级", null);
        }
        DeleteDto deleteDto = new DeleteDto();
        deleteDto.setAuthLevel(1);
        deleteDto.setBelongID(classID);
        List<UserEntity> userList = configService.getUserByBelongID(deleteDto);
        if (userList.size() > 0) {
            return Result.showInfo("00000003", "该班级尚有用户存在，不可删除", null);
        }
        boolean isSuccess = configService.deleteClass(deleteClassDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "删除失败", null);
        }
    }

    //更新班级
    @PostMapping("/updateClass")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateClass(@Validated @RequestBody UpdateClassDto updateClassDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int classID = updateClassDto.getId();
        List<ClassesEntity> classesList = configService.getClassesByID(classID);
        if (classesList.size() < 1) {
            return Result.showInfo("00000002", "查无此班级", null);
        }
        updateClassDto.setSpecialityID(classesList.get(0).getSpecialityID());
        List<ClassesEntity> classList2 = configService.getClassByDto(updateClassDto);
        if (classList2.size() > 0) {
            return Result.showInfo("00000003", "该班级名称或编号重复", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateClassDto.setUpdateTime(now);
        boolean isSuccess = configService.updateClass(updateClassDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //修改班级状态
    @PostMapping("/changeClassStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result changeClassStatus(@Validated @RequestBody ChangeClassDto changeClassDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int classID = changeClassDto.getId();
        List<ClassesEntity> classesList = configService.getClassesByID(classID);
        if (classesList.size() < 1) {
            return Result.showInfo("00000002", "查无此班级", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        changeClassDto.setUpdateTime(now);
        boolean isSuccess = configService.changeClassStatus(changeClassDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "修改失败", null);
        }
    }

    //查询功能模块列表
    @PostMapping("/getFunctionModuleList")
    public Result getFunctionModuleList(@Validated @RequestBody FunctionModuleFilterDto functionModuleFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<FunctionModuleListVo> functionModuleList = configService.getFunctionModuleList(functionModuleFilterDto);
        int totalCount = configService.getFunctionModuleListTotalCount(functionModuleFilterDto);
        return Result.showList("00000000", "Success", functionModuleList, totalCount);
    }

    //新增功能模块
    @PostMapping("/addFunctionModule")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addFunctionModule(@Validated @RequestBody AddFunctionModuleDto addFunctionModuleDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<FunctionModuleEntity> functionModuleList = configService.checkFunctionModuleByDto(addFunctionModuleDto);
        if (functionModuleList.size() > 0) {
            return Result.showInfo("00000002", "该模块已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addFunctionModuleDto.setUpdateTime(now);
        addFunctionModuleDto.setStatus("1");
        boolean isSuccess = configService.addFunctionModule(addFunctionModuleDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除功能模块
    @PostMapping("/deleteFunctionModule")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteFunctionModule(@Validated @RequestBody DeleteFunctionModuleDto deleteFunctionModuleDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int functionModuleID = deleteFunctionModuleDto.getId();
        List<FunctionModuleEntity> functionModuleList = configService.getFunctionModuleByID(functionModuleID);
        if (functionModuleList.size() < 1) {
            return Result.showInfo("00000002", "查无此功能模块", null);
        }
        boolean isSuccess = configService.deleteFunctionModule(deleteFunctionModuleDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //更新功能模块
    @PostMapping("/updateFunctionModule")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateFunctionModule(@Validated @RequestBody UpdateFunctionModuleDto updateFunctionModuleDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int functionModuleID = updateFunctionModuleDto.getId();
        List<FunctionModuleEntity> functionModuleList = configService.getFunctionModuleByID(functionModuleID);
        if (functionModuleList.size() < 1) {
            return Result.showInfo("00000002", "查无此功能模块", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateFunctionModuleDto.setUpdateTime(now);
        boolean isSuccess = configService.updateFunctionModule(updateFunctionModuleDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更改功能模块状态
    @PostMapping("/changeFunctionModuleStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result changeFunctionModuleStatus(@Validated @RequestBody ChangeFunctionModuleDto changeFunctionModuleDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int functionModuleID = changeFunctionModuleDto.getId();
        List<FunctionModuleEntity> functionModuleList = configService.getFunctionModuleByID(functionModuleID);
        if (functionModuleList.size() < 1) {
            return Result.showInfo("00000002", "查无此功能模块", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        changeFunctionModuleDto.setUpdateTime(now);
        boolean isSuccess = configService.changeFunctionModuleStatus(changeFunctionModuleDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更改失败", null);
        }
    }

    //获取标签列表
    @PostMapping("/getLabelList")
    public Result getLabelList(@Validated @RequestBody LabelFilterDto labelFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<LabelEntity> labelList = configService.getLabelList(labelFilterDto);
        int totalCount = configService.getLabelListTotalCount(labelFilterDto);
        return Result.showList("00000000", "Success", labelList, totalCount);
    }

    //新增标签
    @PostMapping("/addLabel")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addLabel(@Validated @RequestBody AddLabelDto addLabelDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<LabelEntity> labelList = configService.checkLabelByName(addLabelDto);
        if (labelList.size() > 0) {
            return Result.showInfo("00000002", "该标签已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addLabelDto.setUpdateTime(now);
        boolean isSuccess = configService.addLabel(addLabelDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除标签
    @PostMapping("/deleteLabel")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteLabel(@Validated @RequestBody DeleteLabelDto deleteLabelDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int labelID = deleteLabelDto.getId();
        List<LabelEntity> labelList = configService.getLabelByID(labelID);
        if (labelList.size() < 1) {
            return Result.showInfo("00000002", "查无此标签", null);
        }
        List<ExperimentEntity> checkList = configService.checkLabelByID(labelID);
        if (checkList.size() > 0) {
            return Result.showInfo("00000003", "该标签被引用中，不可删除", null);
        }
        boolean isSuccess = configService.deleteLabel(deleteLabelDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "删除失败", null);
        }
    }

    //更新标签
    @PostMapping("/updateLabel")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateLabel(@Validated @RequestBody UpdateLabelDto updateLabelDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int labelID = updateLabelDto.getId();
        List<LabelEntity> labelList = configService.getLabelByID(labelID);
        if (labelList.size() < 1) {
            return Result.showInfo("00000002", "查无此标签", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateLabelDto.setUpdateTime(now);
        boolean isSuccess = configService.updateLabel(updateLabelDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "更新失败", null);
        }
    }

    //更新标签状态
    @PostMapping("/updateLabelStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateLabelStatus(@Validated @RequestBody UpdateLabelStatusDto updateLabelStatusDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int labelID = updateLabelStatusDto.getId();
        List<LabelEntity> labelList = configService.getLabelByID(labelID);
        if (labelList.size() < 1) {
            return Result.showInfo("00000002", "查无此标签", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateLabelStatusDto.setUpdateTime(now);
        boolean isSuccess = configService.updateLabelStatus(updateLabelStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000004", "更新失败", null);
        }
    }
}