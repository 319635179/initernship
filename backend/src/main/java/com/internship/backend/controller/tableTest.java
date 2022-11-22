package com.internship.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table")
public class tableTest {

    @RequestMapping("/test")
    public String test(){
        return "page2";
    }
}
