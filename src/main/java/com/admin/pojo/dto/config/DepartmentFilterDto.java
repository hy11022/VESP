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
public class DepartmentFilterDto implements Serializable {

    private static final long serialVersionUID = -8533552328047104081L;

    @NotNull(message = "页数不能为空")
    private int pageNum;

    @NotNull(message = "页码不能为空")
    private int pageSize;

    @NotNull(message = "学校不能为空")
    private int schoolID;

    private String name;

    private String code;

    private String status;
}