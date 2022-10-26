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
public class DeleteCourseResourseDto implements Serializable {

    private static final long serialVersionUID = 7693086357827418663L;

    @NotNull(message = "课程资源唯一编号不能为空")
    private int id;
}