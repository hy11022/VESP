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
public class UpdateClassDto implements Serializable {

    private static final long serialVersionUID = -7981825797257694092L;

    private int id;

    private String code;

    private String name;

    private String beginYear;

    private String updateTime;

    private int specialityID;
}