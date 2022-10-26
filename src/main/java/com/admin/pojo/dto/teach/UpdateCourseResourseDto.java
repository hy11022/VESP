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
public class UpdateCourseResourseDto implements Serializable {

    private static final long serialVersionUID = -7512665932238571377L;

    @NotNull(message = "课程资源唯一编号不能为空")
    private int id;

    @NotBlank(message = "课程资源名称不能为空")
    private String name;

    private String cover;

    private String type;

    private String path;

    private String introduction;

    private String updateTime;
}