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
public class DeleteCourseContentDto implements Serializable {

    private static final long serialVersionUID = -8644048130656564152L;

    @NotNull(message = "课程测评唯一编号不能为空")
    private int id;
}