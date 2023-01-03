package com.internship.backend.controller.skip;

import com.internship.backend.pojo.Questions;
import com.internship.backend.service.questions.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/question")
public class Question {
    @Autowired
    private QuestionsService questionsService;
    @RequestMapping("/list")
    public String list(Model model){
        List<Questions> questionList = questionsService.getQuestions();
        //将查询存储到Model中，带到页面上
        model.addAttribute("questionList",questionList);
        return "questions";
    }
}
