package com.admin.pojo.dto.teach;

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
public class AddCourseQuestionDto implements Serializable {

    private static final long serialVersionUID = -5364586880839764622L;

    @NotBlank(message = "课程题目不能为空")
    private String courseID;

    @NotBlank(message = "课程题目题型不能为空")
    private String type;

    private String content;

    private String selectList;

    private String createTime;
}