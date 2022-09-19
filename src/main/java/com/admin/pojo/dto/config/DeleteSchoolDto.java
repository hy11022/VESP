package com.admin.pojo.dto.config;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteSchoolDto implements Serializable {

    private static final long serialVersionUID = -8346577946179297693L;

    @NotNull(message = "学校唯一编号不能为空")
    private int id;
}