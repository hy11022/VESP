package com.admin.pojo.dto.experiment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddExperimentItemDto implements Serializable {

    private static final long serialVersionUID = -6077196141644722207L;

    @NotNull(message = "所属实验不能为空")
    private int experimentID;

    @NotBlank(message = "分项名称不能为空")
    private String name;

    private String coverImg;

    @NotBlank(message = "教学开关不能为空")
    private String isTeach;

    private String teachContent;

    private String teachVideo;

    @NotBlank(message = "训练开关不能为空")
    private String isTrain;

    private String trainPath;

    @NotBlank(message = "考核开关不能为空")
    private String isCheck;

    private String checkPath;

    private String createTime;
}