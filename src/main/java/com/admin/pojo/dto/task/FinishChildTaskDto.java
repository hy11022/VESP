package com.admin.pojo.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinishChildTaskDto implements Serializable {

    private static final long serialVersionUID = 6157158852102158256L;

    @NotNull(message = "子任务不能为空")
    private int childTaskID;

    @NotBlank(message = "答题内容不能为空")
    private String questionCopyList;
}