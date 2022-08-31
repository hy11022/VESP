package com.admin.pojo.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SourceDto implements Serializable {

    @NotBlank(message = "图片不能为空")
    private String imgB64;

    @NotBlank(message = "类别不能为空")
    private String module;
}
