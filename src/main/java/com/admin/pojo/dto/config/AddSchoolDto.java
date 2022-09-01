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
public class AddSchoolDto implements Serializable {
    @NotBlank(message = "学校名称不能为空")
    private String name;

    private String updateTime;

    private String status;
}