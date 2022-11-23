package com.internship.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
*
* @author: 汪子涵
* @date: 2022/11/23
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptTable {
    private Integer code;
    private List<Dept> data;
}
