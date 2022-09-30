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
public class DeleteExperimentDto implements Serializable {

    private static final long serialVersionUID = -4731665243837144750L;

    @NotNull(message = "实验唯一编号不能为空")
    private int id;
}