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
public class UpdateSpecialityDto implements Serializable {

    private static final long serialVersionUID = 8673813542503038751L;

    @NotNull(message = "学校唯一编号不能为空")
    private int id;

    private String name;

    private String code;

    private String updateTime;
}