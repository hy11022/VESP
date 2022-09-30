package com.admin.service.impl;

import com.admin.mapper.ExperimentMapper;
import com.admin.pojo.dto.experiment.*;
import com.admin.pojo.entity.ExperimentEntity;
import com.admin.pojo.entity.ExperimentItemEntity;
import com.admin.pojo.entity.LabelEntity;
import com.admin.pojo.vo.experiment.ExperimentFilterVo;
import com.admin.pojo.vo.experiment.ExperimentInfoVo;
import com.admin.pojo.vo.experiment.ExperimentItemFilterVo;
import com.admin.pojo.vo.experiment.ExperimentItemInfoVo;
import com.admin.service.ExperimentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class ExperimentServiceImpl implements ExperimentService {

    @Autowired
    ExperimentMapper experimentMapper;

    @Override
    public List<LabelEntity> getLabelList(String labelIDs) {
        return experimentMapper.getLabelList(labelIDs);
    }

    @Override
    public List<ExperimentEntity> getExperimentByID(int experimentID) {
        return experimentMapper.getExperimentByID(experimentID);
    }

    @Override
    public List<ExperimentItemEntity> getExperimentItemByID(int experimentItemID) {
        return experimentMapper.getExperimentItemByID(experimentItemID);
    }

    @Override
    public List<ExperimentItemInfoVo> getExperimentItemInfo(ExperimentItemInfoDto experimentItemInfoDto) {
        return experimentMapper.getExperimentItemInfo(experimentItemInfoDto);
    }

    @Override
    public List<ExperimentEntity> checkExperimentByDto(AddExperimentDto addExperimentDto) {
        return experimentMapper.checkExperimentByDto(addExperimentDto);
    }

    @Override
    public List<ExperimentItemEntity> checkExperimentItemByDto(AddExperimentItemDto addExperimentItemDto) {
        return experimentMapper.checkExperimentItemByDto(addExperimentItemDto);
    }

    @Override
    public List<ExperimentFilterVo> getExperimentList(ExperimentFilterDto experimentFilterDto) {
        PageHelper.startPage(experimentFilterDto.getPageNum(), experimentFilterDto.getPageSize());
        return experimentMapper.getExperimentList(experimentFilterDto);
    }

    @Override
    public List<ExperimentInfoVo> getExperimentInfo(ExperimentInfoDto experimentInfoDto) {
        return experimentMapper.getExperimentInfo(experimentInfoDto);
    }

    @Override
    public List<ExperimentItemFilterVo> getExperimentItemList(ExperimentItemFilterDto experimentItemFilterDto) {
        PageHelper.startPage(experimentItemFilterDto.getPageNum(), experimentItemFilterDto.getPageSize());
        return experimentMapper.getExperimentItemList(experimentItemFilterDto);
    }

    @Override
    public int getExperimentItemListTotalCount(ExperimentItemFilterDto experimentItemFilterDto) {
        return experimentMapper.getExperimentItemList(experimentItemFilterDto).size();
    }
    @Override
    public int getExperimentListTotalCount(ExperimentFilterDto experimentFilterDto) {
        return experimentMapper.getExperimentList(experimentFilterDto).size();
    }

    @Override
    public boolean addExperiment(AddExperimentDto addExperimentDto) {
        return experimentMapper.addExperiment(addExperimentDto);
    }

    @Override
    public boolean addExperimentItem(AddExperimentItemDto addExperimentItemDto) {
        return experimentMapper.addExperimentItem(addExperimentItemDto);
    }

    @Override
    public boolean deleteExperiment(DeleteExperimentDto deleteExperimentDto) {
        return experimentMapper.deleteExperiment(deleteExperimentDto);
    }

    @Override
    public boolean deleteExperimentItem(DeleteExperimentItemDto deleteExperimentItemDto) {
        return experimentMapper.deleteExperimentItem(deleteExperimentItemDto);
    }

    @Override
    public boolean updateExperiment(UpdateExperimentDto updateExperimentDto) {
        return experimentMapper.updateExperiment(updateExperimentDto);
    }

    @Override
    public boolean updateExperimentTeamMember(UpdateExperimentTeamMemberDto updateExperimentTeamMemberDto) {
        return experimentMapper.updateExperimentTeamMember(updateExperimentTeamMemberDto);
    }

    @Override
    public boolean updateExperimentIntro(UpdateExperimentIntroDto updateExperimentIntroDto) {
        return experimentMapper.updateExperimentIntro(updateExperimentIntroDto);
    }

    @Override
    public boolean updateExperimentMust(UpdateExperimentMustDto updateExperimentMustDto) {
        return experimentMapper.updateExperimentMust(updateExperimentMustDto);
    }

    @Override
    public boolean updateExperimentItem(UpdateExperimentItemDto updateExperimentItemDto) {
        return experimentMapper.updateExperimentItem(updateExperimentItemDto);
    }

    @Override
    public boolean updateExperimentStatus(UpdateExperimentStatusDto updateExperimentStatusDto) {
        return experimentMapper.updateExperimentStatus(updateExperimentStatusDto);
    }

    @Override
    public boolean updateExperimentItemStatus(UpdateExperimentItemStatusDto updateExperimentItemStatusDto) {
        return experimentMapper.updateExperimentItemStatus(updateExperimentItemStatusDto);
    }

    @Override
    public boolean updateExperimentReadCount(ExperimentInfoDto experimentInfoDto) {
        return experimentMapper.updateExperimentReadCount(experimentInfoDto);
    }
}
