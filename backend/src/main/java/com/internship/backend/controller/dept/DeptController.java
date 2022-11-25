package com.internship.backend.controller.dept;

import com.internship.backend.pojo.Dept;
import com.internship.backend.pojo.table.DeptTable;
import com.internship.backend.service.dept.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     *
     * @return get all dept
     */
    @GetMapping("/get/getdept")
    public DeptTable getdept(){
        DeptTable deptTable = new DeptTable(0, deptService.getDept());
        return deptTable;
    }

    /**
     *
     * @param deptName
     * @return is add dept success
     */
    @PostMapping("/add/adddpet")
    public String adddept(String deptName){
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        dept.setCreateWith("wzh");
        dept.setUpdateDate(new Date());
        dept.setUpdateWith("wzh");
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
}
