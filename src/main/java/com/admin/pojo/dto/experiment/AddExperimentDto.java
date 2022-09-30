package com.admin.pojo.dto.experiment;

import com.alibaba.fastjson.JSONArray;
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
public class AddExperimentDto implements Serializable {

    private static final long serialVersionUID = 4432861452417262588L;

    @NotBlank(message = "实验名称不能为空")
    private String name;

    private String coverImg;

    private String labelIDs;

    @NotBlank(message = "实验简介不能为空")
    private String introduction;

    @NotBlank(message = "作者不能为空")
    private String author;

    @NotNull(message = "实验所属院系不能为空")
    private int departmentID;

    private String introVideoPath;

    private String teachVideoPath;

    private String updateTime;
}