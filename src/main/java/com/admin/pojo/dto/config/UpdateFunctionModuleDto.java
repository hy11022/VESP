package com.admin.pojo.dto.config;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFunctionModuleDto implements Serializable {

    private static final long serialVersionUID = 3016316310446401099L;

    @NotNull(message = "功能模块唯一编号不能为空")
    private int id;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String remark;

    @NotBlank(message = "封面不能为空")
    private String coverImg;

    private String headImg;

    @NotBlank(message = "路由不能为空")
    private String path;

    @NotBlank(message = "路由类型不能为空")
    private String pathType;

    @NotBlank(message = "是否需要登录不能为空")
    private String isNeedLogin;

    private String roleLimits;

    private String updateTime;
}