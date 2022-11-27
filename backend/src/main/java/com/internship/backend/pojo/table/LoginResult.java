package com.internship.backend.pojo.table;

import com.internship.backend.pojo.Emp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*
* @author: 汪子涵
* @date: 2022/11/27
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {
    private Integer code;
    private String msg;
    private Integer count;
    private Emp emp;
}
