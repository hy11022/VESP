package com.admin.mapper;

import com.admin.pojo.dto.experiment.*;
import com.admin.pojo.entity.ExperimentEntity;
import com.admin.pojo.entity.ExperimentItemEntity;
import com.admin.pojo.entity.LabelEntity;
import com.admin.pojo.vo.experiment.ExperimentFilterVo;
import com.admin.pojo.vo.experiment.ExperimentInfoVo;
import com.admin.pojo.vo.experiment.ExperimentItemFilterVo;
import com.admin.pojo.vo.experiment.ExperimentItemInfoVo;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ExperimentMapper {

    List<ExperimentFilterVo> getExperimentList(ExperimentFilterDto experimentFilterDto);

    List<LabelEntity> getLabelList(String labelIDs);

    @Select("SELECT * FROM experiments WHERE name = #{name} AND department_id = #{departmentID}")
    List<ExperimentEntity> checkExperimentByDto(AddExperimentDto addExperimentDto);

    @Insert("INSERT INTO experiments SET name =#{name},cover_img=#{coverImg},label_ids=#{labelIDs}," +
            "introduction=#{introduction},author=#{author},department_id=#{departmentID}," +
            "create_time=#{createTime},intro_video_path=#{introVideoPath},teach_video_path=#{teachVideoPath}")
    boolean addExperiment(AddExperimentDto addExperimentDto);

    @Select("SELECT * FROM experiments WHERE id=#{id}")
    List<ExperimentEntity> getExperimentByID(int experimentID);

    @Delete("DELETE FROM experiments WHERE id=#{id}")
    boolean deleteExperiment(DeleteExperimentDto deleteExperimentDto);

    @Update("UPDATE experiments SET name =#{name},cover_img=#{coverImg},label_ids=#{labelIDs}," +
            "introduction=#{introduction},author=#{author},department_id=#{departmentID}," +
            "update_time=#{updateTime},intro_video_path=#{introVideoPath},teach_video_path=#{teachVideoPath} WHERE id=#{id}")
    boolean updateExperiment(UpdateExperimentDto updateExperimentDto);

    boolean updateExperimentStatus(UpdateExperimentStatusDto updateExperimentStatusDto);

    @Select("SELECT a.*,b.name AS departmentName,c.name AS lastPublisherName FROM experiments a " +
            "LEFT JOIN departments b ON a.department_id = b.id " +
            "LEFT JOIN users c ON a.last_publisher_account = c.account WHERE a.id=#{id}")
    List<ExperimentInfoVo> getExperimentInfo(ExperimentInfoDto experimentInfoDto);

    @Update("UPDATE experiments SET read_count=read_count+1 WHERE id=#{id}")
    boolean updateExperimentReadCount(ExperimentInfoDto experimentInfoDto);

    List<ExperimentItemFilterVo> getExperimentItemList(ExperimentItemFilterDto experimentItemFilterDto);

    @Select("SELECT * FROM experiment_items WHERE name = #{name} AND experiment_id = #{experimentID}")
    List<ExperimentItemEntity> checkExperimentItemByDto(AddExperimentItemDto addExperimentItemDto);

    @Insert("INSERT INTO experiment_items SET experiment_id=#{experimentID},name =#{name}," +
            "cover_img=#{coverImg},is_teach=#{isTeach},teach_content=#{teachContent}," +
            "teach_video=#{teachVideo},is_train=#{isTrain},train_path=#{trainPath}," +
            "is_check=#{isCheck}, check_path=#{checkPath},create_time=#{createTime}")
    boolean addExperimentItem(AddExperimentItemDto addExperimentItemDto);

    @Select("SELECT * FROM experiment_items WHERE id=#{id}")
    List<ExperimentItemEntity> getExperimentItemByID(int experimentItemID);

    @Delete("DELETE FROM experiment_items WHERE id=#{id}")
    boolean deleteExperimentItem(DeleteExperimentItemDto deleteExperimentItemDto);

    @Update("UPDATE experiment_items SET experiment_id=#{experimentID},name =#{name},cover_img=#{coverImg}," +
            "is_teach=#{isTeach},teach_content=#{teachContent},teach_video=#{teachVideo}," +
            "is_train=#{isTrain},train_path=#{trainPath},is_check=#{isCheck}," +
            "check_path=#{checkPath},update_time=#{updateTime} WHERE id=#{id}")
    boolean updateExperimentItem(UpdateExperimentItemDto updateExperimentItemDto);

    @Update("UPDATE experiment_items SET status=#{status},update_time=#{updateTime} WHERE id=#{id}")
    boolean updateExperimentItemStatus(UpdateExperimentItemStatusDto updateExperimentItemStatusDto);

    @Select("SELECT a.*,b.name AS experimentName FROM experiment_items a " +
            "LEFT JOIN experiments b ON a.experiment_id = b.id WHERE a.id=#{id}")
    List<ExperimentItemInfoVo> getExperimentItemInfo(ExperimentItemInfoDto experimentItemInfoDto);

    @Update("UPDATE experiments SET team_member=#{teamMember},update_time=#{updateTime} WHERE id=#{id}")
    boolean updateExperimentTeamMember(UpdateExperimentTeamMemberDto updateExperimentTeamMemberDto);

    @Update("UPDATE experiments SET intro=#{intro},update_time=#{updateTime} WHERE id=#{id}")
    boolean updateExperimentIntro(UpdateExperimentIntroDto updateExperimentIntroDto);

    @Update("UPDATE experiments SET must_read=#{mustRead},update_time=#{updateTime} WHERE id=#{id}")
    boolean updateExperimentMust(UpdateExperimentMustDto updateExperimentMustDto);
}