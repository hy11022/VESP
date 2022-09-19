package com.admin.pojo.dto.config;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDepartmentDto implements Serializable {

    private static final long serialVersionUID = -5675286886999971382L;

    @NotNull(message = "院系唯一编号不能为空")
    private int id;
}