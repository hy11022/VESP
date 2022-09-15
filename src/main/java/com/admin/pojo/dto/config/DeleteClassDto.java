package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteClassDto implements Serializable {

    private static final long serialVersionUID = -243905403605475970L;

    @NotNull(message = "班级唯一编号不能为空")
    private int id;
}