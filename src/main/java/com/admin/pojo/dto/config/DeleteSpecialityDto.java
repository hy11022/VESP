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
public class DeleteSpecialityDto implements Serializable {

    private static final long serialVersionUID = -5675286886999971382L;

    @NotNull(message = "专业编号不能为空")
    private int id;
}