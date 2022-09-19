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
public class TokenInfoDto implements Serializable {

    private static final long serialVersionUID = -5882162558940065583L;

    private String token;
}