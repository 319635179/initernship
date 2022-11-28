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
    public List<Dept> getDept(String searchName) {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>();
        queryWrapper.eq("del_flag", 0);
        List<Dept> list = new ArrayList<>(), listResult = new ArrayList<>();
        list = deptMapper.selectList(queryWrapper);
        for(Dept dept:list){
            if(dept.getDeptName().indexOf(searchName) != -1 ||
                    dept.getCreateWith().indexOf(searchName) != -1 ||
                    dept.getUpdateWith().indexOf(searchName) != -1){
                listResult.add(dept);
            }
        }
        return listResult;
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

    @Override
    public String editDept(Dept dept) {
        QueryWrapper<Dept> queryWrapper1 = new QueryWrapper<>(), queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.eq("id", dept.getId());
        queryWrapper2.eq("dept_name", dept.getDeptName());
        if(deptMapper.selectOne(queryWrapper1)!=null && deptMapper.selectOne(queryWrapper2) == null){
            Dept nowDept = deptMapper.selectOne(queryWrapper1);
            nowDept.setDeptName(dept.getDeptName());
            nowDept.setStatus(dept.getStatus());
            nowDept.setUpdateDate(dept.getUpdateDate());
            nowDept.setUpdateWith(dept.getUpdateWith());
            deptMapper.updateById(nowDept);
            return "success";
        }
        return "error";
    }
}
