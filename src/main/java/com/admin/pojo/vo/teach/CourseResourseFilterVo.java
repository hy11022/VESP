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
public class CourseResourseFilterVo implements Serializable {

    private static final long serialVersionUID = 8870732940902428091L;

    private int id;

    private String name;

    private int courseID;

    private String courseName;

    private String type;

    private String cover;

    private String introduction;

    private String path;

    private String createTime;

    private String updateTime;

    private String status;
}