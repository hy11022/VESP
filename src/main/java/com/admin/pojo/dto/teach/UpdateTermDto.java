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
public class UpdateTermDto implements Serializable {

    private static final long serialVersionUID = -2967344921904213147L;

    @NotNull(message = "学期唯一编号不能为空")
    private int id;

    @NotBlank(message = "学期名称不能为空")
    private String name;

    private String updateTime;
}