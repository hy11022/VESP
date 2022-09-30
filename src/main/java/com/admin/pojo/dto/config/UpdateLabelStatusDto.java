package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateLabelStatusDto implements Serializable {

    private static final long serialVersionUID = 3848006008372189069L;

    @NotNull(message = "标签唯一编号不能为空")
    private int id;

    private String status;

    private String updateTime;
}