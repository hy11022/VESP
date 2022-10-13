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
public class AddLabelBelongDto implements Serializable {

    private static final long serialVersionUID = -2373698364993409182L;

    private String name;

    private String createTime;
}
