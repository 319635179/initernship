package com.internship.backend.service.impl.dept;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.DeptMapper;
import com.internship.backend.pojo.Dept;
import com.internship.backend.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Dept> getDept() {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>();
        queryWrapper.eq("dept_id", 1);
        Dept dept = deptMapper.selectOne(queryWrapper);
        List<Dept> list = new ArrayList<>();
        list.add(dept);
        return list;
    }
}
