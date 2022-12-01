package com.internship.backend.controller.skip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@Controller
@RequestMapping("/table")
public class Table {
    @RequestMapping("/dept")
    public String dept(){
        return "dept";
    }

    @RequestMapping("/role")
    public String role(){
        return "role";
    }

    @RequestMapping("/emp")
    public String emp(){
        return "emp";
    }
}
