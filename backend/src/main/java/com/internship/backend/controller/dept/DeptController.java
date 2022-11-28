package com.internship.backend.controller.dept;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.DeptMapper;
import com.internship.backend.pojo.Dept;
import com.internship.backend.pojo.Emp;
import com.internship.backend.pojo.table.DeptTable;
import com.internship.backend.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private HttpSession session;
    @Autowired
    private DeptMapper deptMapper;

    /**
     *
     * @return get all dept
     */
    @GetMapping("/get/getdept")
    public DeptTable getdept(Integer page, Integer limit, String searchName){
        System.out.println(searchName);
        if(searchName==null){
            DeptTable deptTable = new DeptTable(0,
                    deptService.getDept().toArray().length,
                    deptService.getDept().subList((page - 1) * limit,
                            Math.min((page * limit),deptService.getDept().toArray().length)));
            return deptTable;
        } else {
            DeptTable deptTable = new DeptTable(0,
                    deptService.getDept(searchName).toArray().length,
                    deptService.getDept(searchName).subList((page - 1) * limit,
                            Math.min((page * limit), deptService.getDept(searchName).toArray().length)));
            return deptTable;
        }
    }

    @PostMapping("/get/getdeptid")
    public Integer getdeptid(String deptName){
        QueryWrapper<Dept>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dept_name", deptName);
        if(deptMapper.selectOne(queryWrapper)!=null){
            return deptMapper.selectOne(queryWrapper).getId();
        }
        return -1;
    }

    /**
     *
     * @param deptName
     * @return is added dept success
     */
    @PostMapping("/add/adddpet")
    public String adddept(String deptName){
        Emp emp = (Emp) session.getAttribute("loginEmp");
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        dept.setCreateWith(emp.getEmpName());
        dept.setCreateDate(new Date());
        dept.setUpdateDate(new Date());
        dept.setUpdateWith(emp.getEmpName());
        dept.setStatus('0');
        dept.setDelFlag('0');
        return deptService.addDept(dept);
    }

    /**
     *
     * @param id
     * @return is delete dept success
     */
    @PostMapping("/delete/deletedept")
    public String deletedept(Integer id){
        return deptService.deleteDept(id);
    }

    @PostMapping("/edit/editdept")
    public String editdept(Integer id, String deptName, String status){
        Emp emp = (Emp) session.getAttribute("loginEmp");
        Dept dept = new Dept();
        dept.setId(id);
        dept.setDeptName(deptName);
        dept.setUpdateDate(new Date());
        dept.setUpdateWith(emp.getEmpName());
        if(status=="可用") {
            dept.setStatus('0');
        }else{
            dept.setStatus('1');
        }
        return deptService.editDept(dept);
    }
}
