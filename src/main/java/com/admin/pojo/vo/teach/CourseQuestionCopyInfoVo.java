package com.admin.pojo.vo.teach;

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
public class CourseQuestionCopyInfoVo implements Serializable {

    private static final long serialVersionUID = -5106419419763353740L;

    private int id;

    private String courseID;

    private String courseName;

    private String type;

    private String content;

    private List<QuestionSelectsCopyVo> selectList;

    private String createTime;

    private String updateTime;

    private String status;
}