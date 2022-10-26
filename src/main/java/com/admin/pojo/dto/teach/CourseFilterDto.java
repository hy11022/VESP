package com.admin.pojo.dto.teach;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseFilterDto implements Serializable {

    private static final long serialVersionUID = -1476209704636858838L;

    @NotNull(message = "页数不能为空")
    private int pageNum;

    @NotNull(message = "页码不能为空")
    private int pageSize;

    private String name;

    private String departmentID;

    private String teacherAccount;

    private String status;
}