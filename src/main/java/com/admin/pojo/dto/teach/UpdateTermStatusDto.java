package com.admin.pojo.dto.teach;

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
public class UpdateTermStatusDto implements Serializable {

    private static final long serialVersionUID = 5102359184876862428L;

    @NotNull(message = "学期唯一编号不能为空")
    private int id;

    @NotBlank(message = "状态不能为空")
    private String status;

    private String updateTime;
}