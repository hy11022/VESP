package com.admin.pojo.dto.config;

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
public class UpdateDepartmentDto implements Serializable {

    private static final long serialVersionUID = 8673813542503038751L;

    @NotNull(message = "学校唯一编号不能为空")
    private int id;

    private String name;

    private String status;

    private String updateTime;
}