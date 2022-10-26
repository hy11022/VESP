package com.admin.pojo.dto.teach;

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
public class PublishCourseContentDto implements Serializable {

    private static final long serialVersionUID = -882158527694682165L;

    @NotNull(message = "课程测评唯一编号不能为空")
    private int id;

    @NotBlank(message = "发布方式不能为空")
    private String releaseMethod;

    private String questionIDs;

    private String startTime;

    private String endTime;

    private String updateTime;

    private String status;
}