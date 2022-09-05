package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddFunctionModuleDto implements Serializable {
    @NotBlank(message = "院系名称不能为空")
    private String name;

    private String remark;

    private String coverImg;

    @NotBlank(message = "封面不能为空")
    private String path;

    private String status;
}