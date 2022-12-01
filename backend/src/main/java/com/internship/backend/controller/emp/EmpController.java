package com.internship.backend.controller.emp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.DeptMapper;
import com.internship.backend.mapper.EmpMapper;
import com.internship.backend.mapper.RoleMapper;
import com.internship.backend.pojo.Dept;
import com.internship.backend.pojo.Emp;
import com.internship.backend.pojo.Role;
import com.internship.backend.pojo.table.EmpTable;
import com.internship.backend.pojo.table.LoginResult;
import com.internship.backend.service.emp.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
*
* @author: 汪子涵
* @date: 2022/11/24
*/
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;
    @Autowired
    private HttpSession session;
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private DeptMapper deptMapper;

    @PostMapping("/get/getlogin")
    public LoginResult getlogin(String username,String password) {
        Emp emp = empService.getLoginEmp(username);
        if(emp==null || emp.getIsDel()=='1' || !emp.getPassword().equals(password)) {
            return new LoginResult(2, "用户名或密码错误", 0, null);
        }else if(emp.getStatus()=='1'){
            return new LoginResult(1, "该员工已离职", 0, null);
        }
        session.setAttribute("loginEmp", emp);
        return new LoginResult(0, "success", 1, emp);
    }

    @GetMapping("/get/getemp")
    public EmpTable getemp(Integer page, Integer limit, String searchName){
        if(searchName==null){
            return new EmpTable(0,
                    empService.getEmp().toArray().length,
                    empService.getEmp().subList((page - 1) * limit,
                            Math.min((page * limit),empService.getEmp().toArray().length)));
        } else {
            return new EmpTable(0,
                    empService.getEmp(searchName).toArray().length,
                    empService.getEmp(searchName).subList((page - 1) * limit,
                            Math.min((page * limit), empService.getEmp(searchName).toArray().length)));
        }
    }

    @PostMapping("/get/getempid")
    public Integer getempid(String empName){
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_name", empName);
        if(empMapper.selectOne(queryWrapper)!=null){
            return empMapper.selectOne(queryWrapper).getId();
        }
        return -1;
    }

    /**
     *
     * @param empName
     * @return is added emp success
     */
    @PostMapping("/add/addemp")
    public String addemp(String empName, char sex, char status, String tel,
                         String email, String roleName, String deptName){
        Emp emp = (Emp) session.getAttribute("loginEmp");
        Emp e = new Emp();
        e.setEmpName(empName);
        e.setSex(sex);
        e.setStatus(status);
        e.setTel(tel);
        e.setEmail(email);
        e.setCreateBy(emp.getEmpName());
        e.setCreateTime(new Date());
        e.setUpdateTime(new Date());
        e.setUpdateBy(emp.getEmpName());
        e.setIsDel('0');
        Integer roleId = roleMapper.selectOne(new QueryWrapper<Role>().eq("role_name", roleName)).getId();
        Integer deptId = deptMapper.selectOne(new QueryWrapper<Dept>().eq("dept_name", deptName)).getId();
        e.setRoleId(roleId);
        e.setDeptId(deptId);
        System.out.println(e);
        return empService.addEmp(e);
    }

    /**
     *
     * @param id
     * @return is delete emp success
     */
    @PostMapping("/delete/deleteemp")
    public String deleteemp(Integer id){
        return empService.deleteEmp(id);
    }

    @PostMapping("/edit/editemp")
    public String editemp(Integer id, String empName, String status){
        Emp emp = (Emp) session.getAttribute("loginEmp");
        Emp e = new Emp();
        e.setId(id);
        e.setEmpName(empName);
        e.setUpdateTime(new Date());
        e.setUpdateBy(e.getEmpName());
        if(status.equals("可用")) {
            e.setStatus('0');
        }else{
            e.setStatus('1');
        }
        return empService.editEmp(e);
    }
}
