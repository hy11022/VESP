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
public class GetCourseQuestionDto implements Serializable {

    private static final long serialVersionUID = 4866790439236641589L;

    private String courseID;

    private String type;

    private int count;
}