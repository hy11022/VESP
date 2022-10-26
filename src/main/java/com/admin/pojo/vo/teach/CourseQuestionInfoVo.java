package com.admin.pojo.vo.teach;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseQuestionInfoVo implements Serializable {

    private static final long serialVersionUID = 1698838090541006301L;

    private int id;

    private int courseID;

    private String courseName;

    private String type;

    private String content;

    private JSONArray selectList;

    private String createTime;

    private String updateTime;

    private String status;
}