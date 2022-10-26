package com.admin.pojo.dto.teach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCourseDto implements Serializable {

    private static final long serialVersionUID = -991784686609320359L;

    @NotBlank(message = "实验名称不能为空")
    private String name;

    @NotBlank(message = "所属部门不能为空")
    private String departmentID;

    private String teacherAccount;

    private String labelIDs;

    private String introduction;

    private String cover;

    private String createTime;
}