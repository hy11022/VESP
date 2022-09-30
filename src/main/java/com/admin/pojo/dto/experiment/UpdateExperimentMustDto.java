package com.admin.pojo.dto.experiment;

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
public class UpdateExperimentMustDto implements Serializable {

    private static final long serialVersionUID = -6679071814501473883L;

    @NotNull(message = "实验唯一编号不能为空")
    private int id;

    private String mustRead;

    private String updateTime;
}