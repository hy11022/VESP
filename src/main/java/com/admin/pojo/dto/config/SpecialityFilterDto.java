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
public class SpecialityFilterDto implements Serializable {

    private static final long serialVersionUID = -1777068816795938505L;

    @NotNull(message = "页码不能为空")
    private int pageNum;

    @NotNull(message = "页数不能为空")
    private int pageSize;

    private String departmentID;

    private String code;

    private String name;

    private String status;
}