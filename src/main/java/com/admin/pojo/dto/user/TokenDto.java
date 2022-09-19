package com.admin.pojo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class TokenDto implements Serializable {

    private static final long serialVersionUID = 841457404138935852L;

    private String account;

    private String accessToken;

    private String accessTokenExpireTime;

    private String refreshToken;

    private String refreshTokenExpireTime;
}