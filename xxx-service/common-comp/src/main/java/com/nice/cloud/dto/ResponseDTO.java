package com.nice.cloud.dto;

import lombok.Data;

@Data
public class ResponseDTO<T> {

    private HeaderDTO header;

    private T body;

    private ResultDTO result;

}
