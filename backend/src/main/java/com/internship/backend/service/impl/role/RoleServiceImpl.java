package com.internship.backend.service.impl.role;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.RoleMapper;
import com.internship.backend.pojo.Role;
import com.internship.backend.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRole() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("del_flag", 0);
        List<Role> list = roleMapper.selectList(queryWrapper);
        return list;
    }
}
