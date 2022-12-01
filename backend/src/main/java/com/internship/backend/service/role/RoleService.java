package com.internship.backend.service.role;

import com.internship.backend.pojo.Role;

import java.util.List;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
public interface RoleService {
    public List<Role> getRole();

    public List<Role> getRole(String searchName);

    public String addRole(Role role);

    public String deleteRole(Integer id);

    public String editRole(Role role);

    public String getRoleNameById(Integer id);
}
