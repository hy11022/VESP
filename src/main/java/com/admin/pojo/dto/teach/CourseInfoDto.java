package com.admin.pojo.dto.teach;

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
public class CourseInfoDto implements Serializable {

    private static final long serialVersionUID = 1675889183239387324L;

    @NotNull(message = "课程唯一编号不能为空")
    private int id;
}