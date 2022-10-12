package com.admin.pojo.dto.config;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddDepartmentDto implements Serializable {

    private static final long serialVersionUID = 5084292058332134753L;

    @NotBlank(message = "院系名称不能为空")
    private String name;

    @NotBlank(message = "学校不能为空")
    private String schoolID;

    @NotBlank(message = "院系编码不能为空")
    private String code;

    private String createTime;

    private String status;
}