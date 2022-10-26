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
public class CourseContentFilterDto implements Serializable {

    private static final long serialVersionUID = 4659392083046358862L;

    @NotNull(message = "页数不能为空")
    private int pageNum;

    @NotNull(message = "页码不能为空")
    private int pageSize;

    private String courseID;

    private String type;

    private String releaseMethod;

    private String createMethod;

    private String createrAccount;

    private String status;
}