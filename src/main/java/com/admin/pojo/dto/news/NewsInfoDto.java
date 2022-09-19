package com.admin.pojo.dto.news;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewsInfoDto implements Serializable {

    private static final long serialVersionUID = -1699274204812687929L;

    @NotNull(message = "唯一编号不能为空")
    private int id;

    private int visitSource;
}