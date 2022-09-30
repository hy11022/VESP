package com.admin.pojo.dto.experiment;

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
public class UpdateExperimentTeamMemberDto implements Serializable {

    private static final long serialVersionUID = 7764110195054410511L;

    @NotNull(message = "实验唯一编号不能为空")
    private int id;

    private String teamMember;

    private String updateTime;
}