package com.internship.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.internship.backend.pojo.Questions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionsMapper extends BaseMapper<Questions> {
}
