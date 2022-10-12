package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddClassDto implements Serializable {

    private static final long serialVersionUID = 8212221888222105855L;

    @NotBlank(message = "院系名称不能为空")
    private String name;

    @NotBlank(message = "专业不能为空")
    private String specialityID;

    private String code;

    private String beginYear;

    private String createTime;

    private String status;
}