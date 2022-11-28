package com.internship.backend.service.dept;

import com.internship.backend.pojo.Dept;

import java.util.List;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
public interface DeptService {
    public List<Dept> getDept();
    public List<Dept> getDept(String searchName);

    public String addDept(Dept dept);

    public String deleteDept(Integer id);

    public String editDept(Dept dept);
}
