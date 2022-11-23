package com.internship.backend.controller.skip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@Controller
@RequestMapping("/test")
public class test {
    @RequestMapping("/1")
    public String test1(){
        return "success";
    }
}
