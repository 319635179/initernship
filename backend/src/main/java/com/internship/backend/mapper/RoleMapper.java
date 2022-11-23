package com.internship.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.internship.backend.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
