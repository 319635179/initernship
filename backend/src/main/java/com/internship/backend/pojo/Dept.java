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
public class Dept {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String  deptName;
    private char status;
    private char delFlag;
    private String  createWith;
    private Date createDate;
    private String  updateWith;
    private Date updateDate;
}
