package com.admin.pojo.dto.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteDto implements Serializable {

    private static final long serialVersionUID = -5320183543195580970L;

    private int authLevel;

    private int belongID;
}