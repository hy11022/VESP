package com.admin.pojo.dto.teach;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CopySelectsDto implements Serializable {

    private static final long serialVersionUID = 7182451416930916685L;

    private int id;

    private String content;

    private int questionCopyId;

    private String isCorrect;
}