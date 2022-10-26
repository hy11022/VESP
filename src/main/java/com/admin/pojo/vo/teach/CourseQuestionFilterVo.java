package com.admin.pojo.vo.teach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseQuestionFilterVo implements Serializable {

    private static final long serialVersionUID = -5288067679163706925L;

    private int id;

    private int courseID;

    private String courseName;

    private String type;

    private String content;

    private String createTime;

    private String updateTime;

    private String status;
}