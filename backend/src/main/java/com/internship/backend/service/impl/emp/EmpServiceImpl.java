package com.internship.backend.service.impl.emp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.EmpMapper;
import com.internship.backend.pojo.Emp;
import com.internship.backend.service.emp.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private HttpSession session;
    @Override
    public List<Emp> getEmp() {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", 0);
        List<Emp> list = empMapper.selectList(queryWrapper);
        return empMapper.selectList(queryWrapper);
    }

    @Override
    public List<Emp> getEmp(String searchName) {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", 0);
        List<Emp> list = empMapper.selectList(queryWrapper), listResult = new ArrayList<>();
        for(Emp r:list){
            if(r.getEmpName().contains(searchName) ||
                    r.getCreateBy().contains(searchName) ||
                    r.getUpdateBy().contains(searchName) ||
                    r.getEmail().contains(searchName) ||
                    r.getTel().contains(searchName) ||
                    r.getRoleId().toString().contains(searchName) ||
                    r.getDeptId().toString().contains(searchName)){
                listResult.add(r);
            }
        }
        return listResult;
    }

    @Override
    public String addEmp(Emp emp) {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_name", emp.getEmpName());
        if(empMapper.selectOne(queryWrapper)==null){
            empMapper.insert(emp);
            return "success";
        }
        return "error";
    }

    @Override
    public String deleteEmp(Integer id) {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        Emp e = (Emp) session.getAttribute("loginEmp");
        queryWrapper.eq("id", id);
        if(empMapper.selectOne(queryWrapper)!=null){
            Emp emp = empMapper.selectOne(queryWrapper);
            emp.setIsDel('1');
            emp.setUpdateTime(new Date());
            emp.setUpdateBy(e.getEmpName());
            empMapper.updateById(emp);
            return "success";
        }
        return "error";
    }

    @Override
    public String editEmp(Emp emp) {
        QueryWrapper<Emp> queryWrapper1 = new QueryWrapper<>(), queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.eq("id", emp.getId());
        queryWrapper2.eq("emp_name", emp.getEmpName());
        if(empMapper.selectOne(queryWrapper1)!=null && empMapper.selectOne(queryWrapper2) == null){
            Emp nowEmp = empMapper.selectOne(queryWrapper1);
            nowEmp.setEmpName(emp.getEmpName());
            nowEmp.setStatus(emp.getStatus());
            nowEmp.setUpdateTime(emp.getUpdateTime());
            nowEmp.setUpdateBy(emp.getUpdateBy());
            nowEmp.setSex(emp.getSex());
            nowEmp.setBirthday(emp.getBirthday());
            nowEmp.setTel(emp.getTel());
            nowEmp.setEmail(emp.getEmail());
            empMapper.updateById(nowEmp);
            return "success";
        }
        return "error";
    }

    @Override
    public Emp getLoginEmp(String username) {
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return empMapper.selectOne(queryWrapper);
    }
}
