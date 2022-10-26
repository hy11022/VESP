package com.admin.pojo.dto.config;

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
public class AddClassCourseDto implements Serializable {

    private static final long serialVersionUID = -912311911136804669L;

    @NotBlank(message = "学期不能为空")
    private String termID;

    @NotBlank(message = "课程不能为空")
    private String courseID;

    @NotBlank(message = "班级不能为空")
    private String classID;

    private String createTime;
}