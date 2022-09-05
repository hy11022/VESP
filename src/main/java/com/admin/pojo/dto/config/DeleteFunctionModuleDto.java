package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteFunctionModuleDto implements Serializable {
    @NotNull(message = "功能模块唯一编号不能为空")
    private int id;
}