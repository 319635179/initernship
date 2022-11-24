package com.internship.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
*
* @author: 汪子涵
* @date: 2022/11/24
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String empName;
    private String username;
    private String password;
    private char sex;
    private Date birthday;
    private String tel;
    private String email;
    private char status;
    private char isDel;
    private String createBy;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private Integer roleId;
    private Integer deptId;
}
