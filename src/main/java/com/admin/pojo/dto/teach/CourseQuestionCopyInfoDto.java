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
public class CourseQuestionCopyInfoDto implements Serializable {

    private static final long serialVersionUID = 6527162243507666315L;

    @NotNull(message = "唯一编号不能为空")
    private int id;

    private String op;
}