package com.internship.backend.controller.dept;

import com.internship.backend.pojo.table.DeptTable;
import com.internship.backend.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/dept/getdept")
    public DeptTable getdept(){
        DeptTable deptTable = new DeptTable(0, deptService.getDept());
        return deptTable;
    }
}
