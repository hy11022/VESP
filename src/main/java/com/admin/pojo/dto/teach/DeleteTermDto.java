package com.admin.pojo.dto.teach;

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
public class DeleteTermDto implements Serializable {

    private static final long serialVersionUID = -9193442094547588158L;

    @NotNull(message = "学期唯一编号不能为空")
    private int id;
}