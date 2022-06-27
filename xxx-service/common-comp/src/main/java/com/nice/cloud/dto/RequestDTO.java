package com.nice.cloud.dto;

import lombok.Data;

@Data
public class RequestDTO<T> {

    private HeaderDTO header;

    private T body;

}
