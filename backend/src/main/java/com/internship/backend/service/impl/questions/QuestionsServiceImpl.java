package com.internship.backend.service.impl.questions;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.internship.backend.mapper.QuestionsMapper;
import com.internship.backend.pojo.Questions;
import com.internship.backend.service.questions.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsMapper questionsMapper;

    @Override
    public List<Questions> getQuestions() {
        QueryWrapper<Questions> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        return questionsMapper.selectList(queryWrapper);
    }

    @Override
    public List<Questions> getQuestions(String search) {
        return null;
    }

    @Override
    public Map<String, String> addQuestions(Map<String, String> data) {
        return null;
    }

    @Override
    public Map<String, String> modifyQuestions(Map<String, String> data) {
        return null;
    }

    @Override
    public Map<String, String> removeQuestions(Map<String, String> data) {
        return null;
    }
}
