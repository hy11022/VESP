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
public class UpdateUserDto implements Serializable {

    private static final long serialVersionUID = -8409292382767020294L;

    @NotNull(message = "唯一编号不能为空")
    private int id;

    private String name;

    private String role;

    @NotBlank(message = "权限层级不能为空")
    private String authLevel;

    @NotBlank(message = "所属机构不能为空")
    private String belongID;

    private String status;

    private String updateTime;
}