package com.admin.pojo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
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
public class LoginDto implements Serializable {

    private static final long serialVersionUID = -8409292382767020294L;

    @NotBlank(message = "账户不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String status;

    private String lastLoginTime;
}