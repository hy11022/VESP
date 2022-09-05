package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFunctionModuleDto implements Serializable {
    @NotNull(message = "功能模块唯一编号不能为空")
    private int id;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String remark;

    private String coverImg;

    private String headImg;

    @NotBlank(message = "封面不能为空")
    private String path;
}