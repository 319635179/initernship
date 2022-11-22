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
public class Dept {
    @TableId(type = IdType.AUTO)
    private int dept_id;
    private String  dept_name;
    private char status;
    private char delete_flag;
    private String  create_by;
    private String  update_by;
    private Date update_time;
}
