package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangeClassDto implements Serializable {

    private static final long serialVersionUID = 3314808135629057260L;

    @NotNull(message = "班级编号不能为空")
    private int id;

    @NotBlank(message = "状态不能为空")
    private String status;

    private String updateTime;
}