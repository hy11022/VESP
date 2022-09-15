package com.admin.pojo.dto.config;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddSchoolDto implements Serializable {

    private static final long serialVersionUID = -5407479380784277677L;

    @NotBlank(message = "学校名称不能为空")
    private String name;

    @NotBlank(message = "学校编码不能为空")
    private String code;

    private String updateTime;

    private String status;
}