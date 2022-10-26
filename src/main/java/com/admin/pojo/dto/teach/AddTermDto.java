package com.admin.pojo.dto.teach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddTermDto implements Serializable {

    private static final long serialVersionUID = 7344619943424458074L;

    @NotBlank(message = "实验名称不能为空")
    private String name;

    private String createTime;

    private String status;
}