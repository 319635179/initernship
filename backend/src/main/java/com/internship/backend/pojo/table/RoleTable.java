package com.internship.backend.pojo.table;

import com.internship.backend.pojo.Role;
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
public class RoleTable {
    private Integer code;
    private Integer count;
    private List<Role> data;
}
