package com.internship.backend.controller.skip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@Controller
@RequestMapping("/index")
public class skipController {

    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
