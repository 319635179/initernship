package com.internship.backend.controller.role;

import com.internship.backend.pojo.table.RoleTable;
import com.internship.backend.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @GetMapping("/get/getrole")
    public RoleTable getRole(){
        RoleTable roleTable = new RoleTable(0, roleService.getRole());
        return roleTable;
    }
}
