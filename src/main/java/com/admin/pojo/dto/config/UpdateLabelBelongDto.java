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
public class UpdateLabelBelongDto implements Serializable {

    private static final long serialVersionUID = -4458208031706866976L;

    @NotNull(message = "唯一编号不能为空")
    private int id;

    private String name;

    private String updateTime;
}