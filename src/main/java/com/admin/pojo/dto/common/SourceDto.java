package com.admin.pojo.dto.common;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

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