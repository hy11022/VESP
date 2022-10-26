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
public class UpdateCourseContentDto implements Serializable {

    private static final long serialVersionUID = 5899527507817019853L;

    @NotNull(message = "课程测评唯一编号不能为空")
    private int id;

    private String title;

    private String type;

    private String courseID;

    private String questionIDs;

    private String isAllObject;

    private String duration;

    private String scoreArray;

    private String remark;

    private String createMethod;

    private String updateTime;
}