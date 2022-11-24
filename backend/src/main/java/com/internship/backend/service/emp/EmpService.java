package com.internship.backend.service.emp;

import com.internship.backend.pojo.Emp;

import java.util.List;

/**
*
* @author: 汪子涵
* @date: 2022/11/24
*/
public interface EmpService {
    //获取所有用户
    public List<Emp> getAllEmp();
    //获取某个员工
    public Emp getLoginEmp(String username);
}
