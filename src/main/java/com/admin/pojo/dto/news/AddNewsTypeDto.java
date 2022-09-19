package com.admin.pojo.dto.news;

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
public class AddNewsTypeDto implements Serializable {

    private static final long serialVersionUID = 2131506630174747326L;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String updateTime;
}