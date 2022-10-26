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
public class CourseQuestionInfoDto implements Serializable {

    private static final long serialVersionUID = -1576467984522602673L;

    @NotNull(message = "课程题目唯一编号不能为空")
    private int id;
}