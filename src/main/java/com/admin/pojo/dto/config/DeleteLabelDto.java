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
public class DeleteLabelDto implements Serializable {

    private static final long serialVersionUID = -1153752090497021469L;

    @NotNull(message = "标签唯一编号不能为空")
    private int id;
}