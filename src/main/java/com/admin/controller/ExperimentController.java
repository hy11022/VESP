package com.admin.controller;

import com.admin.pojo.dto.experiment.*;
import com.admin.pojo.entity.ExperimentEntity;
import com.admin.pojo.entity.ExperimentItemEntity;
import com.admin.pojo.entity.LabelEntity;
import com.admin.pojo.vo.experiment.ExperimentFilterVo;
import com.admin.pojo.vo.experiment.ExperimentInfoVo;
import com.admin.pojo.vo.experiment.ExperimentItemFilterVo;
import com.admin.pojo.vo.experiment.ExperimentItemInfoVo;
import com.admin.service.ExperimentService;
import com.admin.util.CommonUtils;
import com.admin.util.JWTUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import com.admin.util.Result;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/experiment")
public class ExperimentController {

    @Autowired
    private ExperimentService experimentService;

    //获取实验列表
    @PostMapping("/getExperimentList")
    public Result getExperimentList(@Validated @RequestBody ExperimentFilterDto experimentFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ExperimentFilterVo> experimentList = experimentService.getExperimentList(experimentFilterDto);
        for (ExperimentFilterVo experimentFilterVo : experimentList) {
            JSONArray ja = new JSONArray();
            String labelIDs = experimentFilterVo.getLabelIDs();
            List<LabelEntity> labelList = experimentService.getLabelList(labelIDs);
            ja.addAll(labelList);
            experimentFilterVo.setLabelList(ja);
        }
        int totalCount = experimentService.getExperimentListTotalCount(experimentFilterDto);
        return Result.showList("00000000", "Success", experimentList, totalCount);
    }

    //新增实验
    @PostMapping("/addExperiment")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addExperiment(@Validated @RequestBody AddExperimentDto addExperimentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ExperimentEntity> experimentList = experimentService.checkExperimentByDto(addExperimentDto);
        if (experimentList.size() > 0) {
            return Result.showInfo("00000002", "该实验名称已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addExperimentDto.setCreateTime(now);
        boolean isSuccess = experimentService.addExperiment(addExperimentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除实验
    @PostMapping("/deleteExperiment")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteExperiment(@Validated @RequestBody DeleteExperimentDto deleteExperimentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentID = deleteExperimentDto.getId();
        List<ExperimentEntity> experimentList = experimentService.getExperimentByID(experimentID);
        if (experimentList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验", null);
        }
        boolean isSuccess = experimentService.deleteExperiment(deleteExperimentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //更新实验（基本信息）
    @PostMapping("/updateExperiment")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateExperiment(@Validated @RequestBody UpdateExperimentDto updateExperimentDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentID = updateExperimentDto.getId();
        List<ExperimentEntity> experimentList = experimentService.getExperimentByID(experimentID);
        if (experimentList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateExperimentDto.setUpdateTime(now);
        boolean isSuccess = experimentService.updateExperiment(updateExperimentDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更新实验状态(发布)
    @PostMapping("/updateExperimentStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateExperimentStatus(@Validated @RequestBody UpdateExperimentStatusDto updateExperimentStatusDto, @RequestHeader Map headers,BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentID = updateExperimentStatusDto.getId();
        List<ExperimentEntity> experimentList = experimentService.getExperimentByID(experimentID);
        if (experimentList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验", null);
        }
        JSONObject authorization;
        try {
            authorization = JSONObject.parseObject(headers.get("authorization").toString());
        } catch (Exception e) {
            return Result.showInfo("00000002", "入参有误", null);
        }
        String accessToken = authorization.getString("accessToken");
        String account = JWTUtils.getAccount(accessToken);
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateExperimentStatusDto.setUpdateTime(now);
        if(updateExperimentStatusDto.getStatus().equals("1")){
            updateExperimentStatusDto.setLastPublisherAccount(account);
            updateExperimentStatusDto.setLastPublishTime(now);
        }
        boolean isSuccess = experimentService.updateExperimentStatus(updateExperimentStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更新实验小组成员
    @PostMapping("/updateExperimentTeamMember")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateExperimentTeamMember(@Validated @RequestBody UpdateExperimentTeamMemberDto updateExperimentTeamMemberDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentID = updateExperimentTeamMemberDto.getId();
        List<ExperimentEntity> experimentList = experimentService.getExperimentByID(experimentID);
        if (experimentList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateExperimentTeamMemberDto.setUpdateTime(now);
        boolean isSuccess = experimentService.updateExperimentTeamMember(updateExperimentTeamMemberDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }
    //更新实验简介
    @PostMapping("/updateExperimentIntro")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateExperimentIntro(@Validated @RequestBody UpdateExperimentIntroDto updateExperimentIntroDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentID = updateExperimentIntroDto.getId();
        List<ExperimentEntity> experimentList = experimentService.getExperimentByID(experimentID);
        if (experimentList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateExperimentIntroDto.setUpdateTime(now);
        boolean isSuccess = experimentService.updateExperimentIntro(updateExperimentIntroDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更新实验必读
    @PostMapping("/updateExperimentMust")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateExperimentMust(@Validated @RequestBody UpdateExperimentMustDto updateExperimentMustDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentID = updateExperimentMustDto.getId();
        List<ExperimentEntity> experimentList = experimentService.getExperimentByID(experimentID);
        if (experimentList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateExperimentMustDto.setUpdateTime(now);
        boolean isSuccess = experimentService.updateExperimentMust(updateExperimentMustDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //获取实验详情
    @PostMapping("/getExperimentInfo")
    public Result getExperimentInfo(@Validated @RequestBody ExperimentInfoDto experimentInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ExperimentInfoVo> experimentInfo = experimentService.getExperimentInfo(experimentInfoDto);
        if(experimentInfo.size()<1){
            return Result.showInfo("00000002", "查无此实验", null);
        }
        if(experimentInfoDto.getVisitSource()==2){
            if(experimentInfo.get(0).getStatus().equals("2")){
                return Result.showInfo("00000003", "该实验不存在",null);
            }else{
                experimentService.updateExperimentReadCount(experimentInfoDto);
            }
        }
        for (ExperimentInfoVo experimentFilterVo : experimentInfo) {
            JSONArray ja = new JSONArray();
            String labelIDs = experimentFilterVo.getLabelIDs();
            List<LabelEntity> labelList = experimentService.getLabelList(labelIDs);
            ja.addAll(labelList);
            experimentFilterVo.setLabelList(ja);
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(experimentInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }

    //获取实验分项列表
    @PostMapping("/getExperimentItemList")
    public Result getExperimentItemList(@Validated @RequestBody ExperimentItemFilterDto experimentItemFilterDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ExperimentItemFilterVo> experimentItemList = experimentService.getExperimentItemList(experimentItemFilterDto);
        int totalCount = experimentService.getExperimentItemListTotalCount(experimentItemFilterDto);
        return Result.showList("00000000", "Success", experimentItemList, totalCount);
    }

    //新增实验分项
    @PostMapping("/addExperimentItem")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result addExperimentItem(@Validated @RequestBody AddExperimentItemDto addExperimentItemDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ExperimentItemEntity> experimentItemList = experimentService.checkExperimentItemByDto(addExperimentItemDto);
        if (experimentItemList.size() > 0) {
            return Result.showInfo("00000002", "该分项名称已存在", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        addExperimentItemDto.setCreateTime(now);
        boolean isSuccess = experimentService.addExperimentItem(addExperimentItemDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "新增失败", null);
        }
    }

    //删除实验分项
    @PostMapping("/deleteExperimentItem")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result deleteExperimentItem(@Validated @RequestBody DeleteExperimentItemDto deleteExperimentItemDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentItemID = deleteExperimentItemDto.getId();
        List<ExperimentItemEntity> experimentItemList = experimentService.getExperimentItemByID(experimentItemID);
        if (experimentItemList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验分项", null);
        }
        boolean isSuccess = experimentService.deleteExperimentItem(deleteExperimentItemDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "删除失败", null);
        }
    }

    //更新实验分项（基本信息）
    @PostMapping("/updateExperimentItem")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateExperimentItem(@Validated @RequestBody UpdateExperimentItemDto updateExperimentItemDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentItemID = updateExperimentItemDto.getId();
        List<ExperimentItemEntity> experimentItemList = experimentService.getExperimentItemByID(experimentItemID);
        if (experimentItemList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验分项", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateExperimentItemDto.setUpdateTime(now);
        boolean isSuccess = experimentService.updateExperimentItem(updateExperimentItemDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //更新实验分项状态
    @PostMapping("/updateExperimentItemStatus")
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result updateExperimentItemStatus(@Validated @RequestBody UpdateExperimentItemStatusDto updateExperimentItemStatusDto,BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        int experimentItemID = updateExperimentItemStatusDto.getId();
        List<ExperimentItemEntity> experimentItemList = experimentService.getExperimentItemByID(experimentItemID);
        if (experimentItemList.size() < 1) {
            return Result.showInfo("00000002", "查无此实验分项", null);
        }
        String now = CommonUtils.getTime(0);    //获取当前时间
        updateExperimentItemStatusDto.setUpdateTime(now);
        boolean isSuccess = experimentService.updateExperimentItemStatus(updateExperimentItemStatusDto);
        if (isSuccess) {
            return Result.showInfo("00000000", "Success", null);
        } else {
            return Result.showInfo("00000003", "更新失败", null);
        }
    }

    //获取实验分项详情
    @PostMapping("/getExperimentItemInfo")
    public Result getExperimentItemInfo(@Validated @RequestBody ExperimentItemInfoDto experimentItemInfoDto, BindingResult bindingResult) {
        //会把校验失败情况下的信息反馈到前端
        if (bindingResult.hasErrors()) {
            return Result.showInfo("00000001", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage(), null);
        }
        List<ExperimentItemInfoVo> experimentItemInfo = experimentService.getExperimentItemInfo(experimentItemInfoDto);
        if (experimentItemInfo.isEmpty()) {
            return Result.showInfo("00000002", "查无此实验分项", null);
        }
        if(experimentItemInfo.get(0).getStatus().equals("2")){
            return Result.showInfo("00000003", "该实验分项不存在",null);
        }
        JSONObject resInfo = (JSONObject)JSONObject.toJSON(experimentItemInfo.get(0));//Entity转JSONObject
        return Result.showInfo("00000000", "Success", resInfo);
    }
}