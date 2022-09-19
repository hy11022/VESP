package com.admin.pojo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @BelongsProject: VESP
 * @BelongsPackage: com.admin.pojo.dto
 * @Author: Du Rongjun
 * @CreateTime: 2022-07-12  16:18
 * @Description:
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserStatusDto implements Serializable {

    private static final long serialVersionUID = -6835375409079756869L;

    @NotNull(message = "唯一编号不能为空")
    private int id;

    @NotBlank(message = "状态不能为空")
    private String status;

    private String updateTime;
}