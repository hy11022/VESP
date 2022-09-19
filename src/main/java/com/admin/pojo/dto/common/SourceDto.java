package com.admin.pojo.dto.common;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SourceDto implements Serializable {

    private static final long serialVersionUID = 9044514695246901279L;

    @NotBlank(message = "图片不能为空")
    private String imgB64;

    @NotBlank(message = "类别不能为空")
    private String module;
}