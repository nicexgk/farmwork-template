package com.huawei.cloud.dto;

import lombok.Data;

import javax.xml.transform.Result;

@Data
public class ResponseDTO<T> {

    private HeaderDTO header;

    private T body;

    private ResultDTO result;

}
