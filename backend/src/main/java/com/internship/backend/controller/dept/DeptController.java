package com.internship.backend.controller.dept;

import com.internship.backend.pojo.Dept;
import com.internship.backend.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/getdept")
    public List<Dept> getdept(){
        return deptService.getDept();
    }
}
