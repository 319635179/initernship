package com.internship.backend.pojo.table;

import com.internship.backend.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleTable {
    private Integer code;
    private List<Role> data;
}
