package com.internship.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.internship.backend.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
}
