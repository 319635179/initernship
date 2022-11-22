package com.internship.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class dept {
    @TableId(type = IdType.AUTO)
    private int deptId;
    private String  deptName;
    private char status;
    private char deleteFlag;
    private String  createBy;
    private String  updateBy;
    private Date updateTime;
}
