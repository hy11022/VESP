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
public class CourseResourseFilterDto implements Serializable {

    private static final long serialVersionUID = -4858939902802698206L;

    @NotNull(message = "页数不能为空")
    private int pageNum;

    @NotNull(message = "页码不能为空")
    private int pageSize;

    private String name;

    private String courseID;

    private String type;

    private String status;
}