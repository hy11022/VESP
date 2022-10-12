package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddLabelDto implements Serializable {

    private static final long serialVersionUID = 7402395720507393021L;

    private String name;

    private String type;

    private String createTime;
}
