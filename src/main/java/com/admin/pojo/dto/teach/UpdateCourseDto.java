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
public class UpdateCourseDto implements Serializable {

    private static final long serialVersionUID = -1538960295196268403L;

    @NotNull(message = "课程唯一编号不能为空")
    private int id;

    @NotBlank(message = "课程名称不能为空")
    private String name;

    @NotBlank(message = "所属部门不能为空")
    private String departmentID;

    private String teacherAccount;

    private String labelIDs;

    private String introduction;

    private String cover;

    private String updateTime;
}