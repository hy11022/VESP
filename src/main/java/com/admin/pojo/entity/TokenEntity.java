package com.admin.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @BelongsProject: VESP
 * @BelongsPackage: com.admin.entity
 * @Author: Du Rongjun
 * @CreateTime: 2022-06-21  19:21
 * @Description: 验证
 * @Version: 1.0
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenEntity implements Serializable {

    private static final long serialVersionUID = -8644080350073924483L;

    private String accountID;

    private String accessToken;

    private String accessTokenExpireTime;

    private String refreshToken;

    private String refreshTokenExpireTime;
}