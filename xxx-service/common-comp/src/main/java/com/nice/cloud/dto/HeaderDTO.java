package com.nice.cloud.dto;

import lombok.Data;

@Data
public class HeaderDTO {
    private Integer seq;
    private String versionId;
    private String token;
    private String language;
}
