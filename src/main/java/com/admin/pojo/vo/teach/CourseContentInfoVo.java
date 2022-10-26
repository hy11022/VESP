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
public class CourseContentInfoVo implements Serializable {

    private static final long serialVersionUID = 7373399010189905645L;

    private int id;

    private String title;

    private String type;

    private String courseID;

    private String courseName;

    private String questionIDs;

    private JSONArray questionList;

    private String isAllObject;

    private String duration;

    private JSONArray scoreArray;

    private String scoresArray;

    private String remark;

    private String releaseMethod;

    private String releaseTime;

    private String createMethod;

    private String createTime;

    private String createrAccount;

    private String createrName;

    private String updateTime;

    private String status;
}