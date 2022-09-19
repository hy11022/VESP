package com.admin.pojo.dto.config;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteFunctionModuleDto implements Serializable {

    private static final long serialVersionUID = 4078083412106961167L;

    @NotNull(message = "功能模块唯一编号不能为空")
    private int id;
}