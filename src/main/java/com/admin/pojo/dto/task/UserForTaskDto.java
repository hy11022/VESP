package com.admin.pojo.dto.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserForTaskDto implements Serializable {

    private static final long serialVersionUID = 880595107473606273L;

    private String role;

    private String authLevel;

    private String belongID;

    private String status;
}