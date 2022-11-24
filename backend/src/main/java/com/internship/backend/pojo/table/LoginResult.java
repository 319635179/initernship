package com.internship.backend.pojo.table;

import com.internship.backend.pojo.Emp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResult {
    private Integer code;
    private String msg;
    private Integer count;
    private Emp emp;
}
