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
public class UpdateCourseQuestionStatusDto implements Serializable {

    private static final long serialVersionUID = 9206291134372269578L;

    @NotNull(message = "课程题目唯一编号不能为空")
    private int id;

    @NotBlank(message = "状态不能为空")
    private String status;

    private String updateTime;
}