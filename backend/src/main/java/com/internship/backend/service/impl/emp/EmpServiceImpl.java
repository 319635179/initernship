package com.internship.backend.service.impl.emp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.EmpMapper;
import com.internship.backend.pojo.Emp;
import com.internship.backend.service.emp.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
*
* @author: 汪子涵
* @date: 2022/11/24
*/
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Emp> getAllEmp() {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        System.out.println(empMapper.selectById(1).getEmpName());
        return empMapper.selectList(queryWrapper);
    }

    @Override
    public Emp getLoginEmp(String username) {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return empMapper.selectOne(queryWrapper);
    }
}
