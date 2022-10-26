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
public class UpdateCourseQuestionDto implements Serializable {

    private static final long serialVersionUID = -5338874137644221502L;

    @NotNull(message = "课程资源唯一编号不能为空")
    private int id;

    private String courseID;

    private String type;

    private String content;

    private String selectList;

    private String updateTime;
}