package com.admin.service;

import com.admin.pojo.dto.experiment.*;
import com.admin.pojo.entity.ExperimentEntity;
import com.admin.pojo.entity.ExperimentItemEntity;
import com.admin.pojo.entity.LabelEntity;
import com.admin.pojo.vo.experiment.ExperimentFilterVo;
import com.admin.pojo.vo.experiment.ExperimentInfoVo;
import com.admin.pojo.vo.experiment.ExperimentItemFilterVo;
import com.admin.pojo.vo.experiment.ExperimentItemInfoVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExperimentService {

    List<LabelEntity> getLabelList(String labelIDs);

    List<ExperimentEntity> getExperimentByID(int experimentID);

    List<ExperimentItemEntity> getExperimentItemByID(int experimentItemID);

    List<ExperimentItemInfoVo> getExperimentItemInfo(ExperimentItemInfoDto experimentItemInfoDto);

    List<ExperimentEntity> checkExperimentByDto(AddExperimentDto addExperimentDto);

    List<ExperimentItemEntity> checkExperimentItemByDto(AddExperimentItemDto addExperimentItemDto);

    List<ExperimentFilterVo> getExperimentList(ExperimentFilterDto experimentFilterDto);

    List<ExperimentInfoVo> getExperimentInfo(ExperimentInfoDto experimentInfoDto);

    List<ExperimentItemFilterVo> getExperimentItemList(ExperimentItemFilterDto experimentItemFilterDto);

    int getExperimentItemListTotalCount(ExperimentItemFilterDto experimentItemFilterDto);

    int getExperimentListTotalCount(ExperimentFilterDto experimentFilterDto);

    boolean addExperiment(AddExperimentDto addExperimentDto);

    boolean addExperimentItem(AddExperimentItemDto addExperimentItemDto);

    boolean deleteExperiment(DeleteExperimentDto deleteExperimentDto);

    boolean deleteExperimentItem(DeleteExperimentItemDto deleteExperimentItemDto);

    boolean updateExperiment(UpdateExperimentDto updateExperimentDto);

    boolean updateExperimentTeamMember(UpdateExperimentTeamMemberDto updateExperimentTeamMemberDto);

    boolean updateExperimentIntro(UpdateExperimentIntroDto updateExperimentIntroDto);

    boolean updateExperimentMust(UpdateExperimentMustDto updateExperimentMustDto);

    boolean updateExperimentItem(UpdateExperimentItemDto updateExperimentItemDto);

    boolean updateExperimentStatus(UpdateExperimentStatusDto updateExperimentStatusDto);

    boolean updateExperimentItemStatus(UpdateExperimentItemStatusDto updateExperimentItemStatusDto);

    void updateExperimentReadCount(ExperimentInfoDto experimentInfoDto);
}