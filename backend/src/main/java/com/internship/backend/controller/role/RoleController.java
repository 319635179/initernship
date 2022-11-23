package com.internship.backend.controller.role;

import com.internship.backend.pojo.Role;
import com.internship.backend.pojo.table.RoleTable;
import com.internship.backend.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @GetMapping("/role/getrole")
    public RoleTable getRole(){
        RoleTable roleTable = new RoleTable(0, roleService.getRole());
        return roleTable;
    }
}
