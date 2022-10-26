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
public class AddCourseResourseDto implements Serializable {

    private static final long serialVersionUID = -991784686609320359L;

    @NotBlank(message = "课程不能为空")
    private String courseID;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String cover;

    private String type;

    private String path;

    private String introduction;

    private String createTime;
}