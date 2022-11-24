package com.internship.backend.controller.emp;

import com.internship.backend.pojo.Emp;
import com.internship.backend.pojo.table.LoginResult;
import com.internship.backend.service.emp.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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

    @PostMapping("/emp/getlogin")
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
}
