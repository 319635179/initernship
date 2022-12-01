package com.internship.backend.controller.role;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.RoleMapper;
import com.internship.backend.pojo.Emp;
import com.internship.backend.pojo.Role;
import com.internship.backend.pojo.table.RoleTable;
import com.internship.backend.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private HttpSession session;
    @GetMapping("/get/getrole")
    public RoleTable getRole(Integer page, Integer limit, String searchName){
        if(searchName==null){
            return new RoleTable(0,
                    roleService.getRole().toArray().length,
                    roleService.getRole().subList((page - 1) * limit,
                            Math.min((page * limit),roleService.getRole().toArray().length)));
        } else {
            return new RoleTable(0,
                    roleService.getRole(searchName).toArray().length,
                    roleService.getRole(searchName).subList((page - 1) * limit,
                            Math.min((page * limit), roleService.getRole(searchName).toArray().length)));
        }
    }

    @PostMapping("/get/getroleid")
    public Integer getroleid(String roleName){
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        if(roleMapper.selectOne(queryWrapper)!=null){
            return roleMapper.selectOne(queryWrapper).getId();
        }
        return -1;
    }

    @PostMapping("/add/addrole")
    public String adddept(String roleName){
        Emp emp = (Emp) session.getAttribute("loginEmp");
        Role role = new Role();
        role.setRoleName(roleName);
        role.setCreateBy(emp.getEmpName());
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setUpdateBy(emp.getEmpName());
        role.setStatus('0');
        role.setDelFlag('0');
        return roleService.addRole(role);
    }

    @PostMapping("/delete/deleterole")
    public String deletedept(Integer id){
        return roleService.deleteRole(id);
    }

    @PostMapping("/edit/editrole")
    public String editrole(Integer id, String roleName, String status){
        Emp emp = (Emp) session.getAttribute("loginEmp");
        Role role = new Role();
        role.setId(id);
        role.setRoleName(roleName);
        role.setUpdateTime(new Date());
        role.setUpdateBy(emp.getEmpName());
        if(status.equals("可用")) {
            role.setStatus('0');
        }else{
            role.setStatus('1');
        }
        return roleService.editRole(role);
    }

    @PostMapping("/get/rolename")
    public String getRoleNameById(Integer id){
        return roleService.getRoleNameById(id);
    }
}
