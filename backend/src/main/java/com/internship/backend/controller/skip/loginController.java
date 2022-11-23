package com.internship.backend.controller.skip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/request")
public class loginController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
