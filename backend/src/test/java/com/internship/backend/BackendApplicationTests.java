package com.internship.backend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.DeptMapper;
import com.internship.backend.pojo.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    DeptMapper deptMapper;
    @Test
    void contextLoads() {
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>();
        queryWrapper.eq("id", 1);
        Dept dept = deptMapper.selectOne(queryWrapper);
        System.out.println(dept);
    }

}
