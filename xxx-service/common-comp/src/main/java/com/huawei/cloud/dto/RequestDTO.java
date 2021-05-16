package com.huawei.cloud.dto;

import com.netflix.ribbon.proxy.annotation.Http;
import lombok.Data;

@Data
public class RequestDTO<T> {

    private HeaderDTO header;

    private T body;

}
