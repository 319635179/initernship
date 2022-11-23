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
//    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createTime;
//    @JsonFormat(shape =JsonFormat.Shape.STRING,pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date updateTime;
}
