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
* @date: 2022/11/23
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String roleName;
    private char status;
    private char delFlag;
    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
}
