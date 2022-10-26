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
public class AddCourseContentDto implements Serializable {

    private static final long serialVersionUID = 4369308723420632346L;

    @NotBlank(message = "课程测评标题不能为空")
    private String title;

    @NotBlank(message = "课程测评类型不能为空")
    private String type;

    @NotBlank(message = "课程不能为空")
    private String courseID;

    private String questionIDs;

    private String isAllObject;

    private String duration;

    private String scoreArray;

    private String remark;

    private String createMethod;

    private String createrAccount;

    private String createTime;

    private int count;
}