package com.internship.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.internship.backend.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;

/**
*
* @author: 汪子涵
* @date: 2022/11/24
*/
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
}
