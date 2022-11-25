package com.internship.backend.service.impl.dept;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.DeptMapper;
import com.internship.backend.pojo.Dept;
import com.internship.backend.service.dept.DeptService;
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
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;
    @Override
    public List<Dept> getDept() {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>();
        queryWrapper.eq("del_flag", 0);
        List<Dept> list = new ArrayList<>();
        list = deptMapper.selectList(queryWrapper);
        return list;
    }

    @Override
    public String addDept(Dept dept) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_name", dept.getDeptName());
        if(deptMapper.selectOne(queryWrapper)==null){
            deptMapper.insert(dept);
            return "success";
        }
        return "error";
    }

    @Override
    public String deleteDept(Integer id) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        if(deptMapper.selectOne(queryWrapper)!=null){
            Dept dept = deptMapper.selectOne(queryWrapper);
            dept.setDelFlag('1');
            deptMapper.updateById(dept);
            return "success";
        }
        return "error";
    }
}
