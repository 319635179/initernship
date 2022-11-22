package com.internship.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@RestController
@Controller
@RequestMapping("/test")
public class test {
    @RequestMapping("/1")
    public String test1(){
        return "success";
    }
}
