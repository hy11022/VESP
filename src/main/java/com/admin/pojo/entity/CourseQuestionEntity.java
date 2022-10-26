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
public class CourseQuestionEntity implements Serializable {

    private static final long serialVersionUID = 1005926168591154457L;

    private int id;

    private String content;

    private int courseID;

    private String type;

    private List<QuestionSelectsEntity> selectList;

    private String createTime;

    private String updateTime;

    private String status;
}