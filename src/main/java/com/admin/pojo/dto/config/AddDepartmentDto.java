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
public class AddDepartmentDto implements Serializable {
    @NotBlank(message = "院系名称不能为空")
    private String name;

    @NotBlank(message = "学校不能为空")
    private String schoolID;

    private String updateTime;

    private String status;
}