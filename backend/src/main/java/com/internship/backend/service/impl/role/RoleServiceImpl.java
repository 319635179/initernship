package com.internship.backend.service.impl.role;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.RoleMapper;
import com.internship.backend.pojo.Role;
import com.internship.backend.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRole() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        return roleMapper.selectList(queryWrapper);
    }

    @Override
    public List<Role> getRole(String searchName) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<Role>();
        queryWrapper.eq("del_flag", 0);
        List<Role> list = new ArrayList<>(), listResult = new ArrayList<>();
        list = roleMapper.selectList(queryWrapper);
        for(Role r:list){
            if(r.getRoleName().contains(searchName) ||
                    r.getCreateBy().contains(searchName) ||
                    r.getUpdateBy().contains(searchName)){
                listResult.add(r);
            }
        }
        return listResult;
    }

    @Override
    public String addRole(Role role) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", role.getRoleName());
        if(roleMapper.selectOne(queryWrapper)==null){
            roleMapper.insert(role);
            return "success";
        }
        return "error";
    }

    @Override
    public String deleteRole(Integer id) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        if(roleMapper.selectOne(queryWrapper)!=null){
            Role role = roleMapper.selectOne(queryWrapper);
            role.setDelFlag('1');
            roleMapper.updateById(role);
            return "success";
        }
        return "error";
    }

    @Override
    public String editRole(Role role) {
        QueryWrapper<Role> queryWrapper1 = new QueryWrapper<>(), queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.eq("id", role.getId());
        queryWrapper2.eq("role_name", role.getRoleName());
        System.out.println(role);
        if(roleMapper.selectOne(queryWrapper1)!=null && roleMapper.selectOne(queryWrapper2) == null){
            Role nowRole = roleMapper.selectOne(queryWrapper1);
            nowRole.setRoleName(role.getRoleName());
            nowRole.setStatus(role.getStatus());
            nowRole.setUpdateTime(role.getUpdateTime());
            nowRole.setUpdateBy(role.getUpdateBy());
            roleMapper.updateById(nowRole);
            return "success";
        }
        return "error";
    }

    @Override
    public String getRoleNameById(Integer id) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        System.out.println("* "+ roleMapper.selectOne(queryWrapper).getRoleName());
        return roleMapper.selectOne(queryWrapper).getRoleName();
    }
}
