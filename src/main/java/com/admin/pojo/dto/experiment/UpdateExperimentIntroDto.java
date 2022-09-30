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
public class UpdateExperimentIntroDto implements Serializable {

    private static final long serialVersionUID = -2273084807548838109L;

    @NotNull(message = "实验唯一编号不能为空")
    private int id;

    private String intro;

    private String updateTime;
}