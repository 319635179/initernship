package com.internship.backend.pojo.table;

import com.internship.backend.pojo.Emp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpTable {
    private Integer code;
    private Integer count;
    private List<Emp> data;
}