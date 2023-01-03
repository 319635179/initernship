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
public class Questions {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer questionNum;
    private String question;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
}
