package com.internship.backend.service.emp;

import com.internship.backend.pojo.Emp;

import java.util.List;

/**
*
* @author: 汪子涵
* @date: 2022/11/24
*/
public interface EmpService {
    public List<Emp> getEmp();
    public List<Emp> getEmp(String searchName);

    public String addEmp(Emp emp);

    public String deleteEmp(Integer id);

    public String editEmp(Emp emp);
    public Emp getLoginEmp(String username);
}
