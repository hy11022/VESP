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
public class UpdateSchoolDto implements Serializable {
    @NotNull(message = "学校唯一编号不能为空")
    private int id;

    private String name;

    private String status;

    private String updateTime;
}