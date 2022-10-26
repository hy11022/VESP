package com.admin.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseQuestionCopyEntity implements Serializable {

    private static final long serialVersionUID = -5530979094834780331L;

    private int id;

    private String content;

    private List<QuestionSelectsCopyEntity> selectList;

    private int questionID;

    private String type;
}