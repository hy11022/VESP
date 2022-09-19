package com.admin.pojo.dto.news;

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
public class UpdateNewsTypeDto implements Serializable {

    private static final long serialVersionUID = -8580707722439987412L;

    @NotNull(message = "唯一编号不能为空")
    private int id;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String updateTime;
}