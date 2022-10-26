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
public class DeleteCourseQuestionDto implements Serializable {

    private static final long serialVersionUID = 1336439824743759504L;

    @NotNull(message = "课程题目唯一编号不能为空")
    private int id;
}