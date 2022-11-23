package com.internship.backend.controller.skip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@Controller
@RequestMapping("/request")
public class loginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
