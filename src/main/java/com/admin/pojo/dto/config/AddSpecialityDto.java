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
public class AddSpecialityDto implements Serializable {

    private static final long serialVersionUID = -4826255229176510073L;

    @NotBlank(message = "所属部门不能为空")
    private String departmentID;

    @NotBlank(message = "专业编号不能为空")
    private String code;

    @NotBlank(message = "专业名称不能为空")
    private String name;

    private String createTime;

    private String status;
}