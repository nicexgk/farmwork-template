package com.nice.cloud.nice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nice")
public class NiceController {

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

}
