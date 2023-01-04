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
public class Useranswer {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String tel;
    private String answer;
    private Date time;
    private Integer redNum;
    private Integer blueNum;
    private Integer yellowNum;
    private Integer greenNum;
}
