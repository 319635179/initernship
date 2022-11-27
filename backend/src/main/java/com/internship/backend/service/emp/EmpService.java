package com.internship.backend.service.emp;

import com.internship.backend.pojo.Emp;

import java.util.List;

/**
*
* @author: 汪子涵
* @date: 2022/11/24
*/
public interface EmpService {
    public List<Emp> getAllEmp();
    public Emp getLoginEmp(String username);
}
