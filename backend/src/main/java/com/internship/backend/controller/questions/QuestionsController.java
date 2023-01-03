package com.internship.backend.controller.questions;

import com.internship.backend.pojo.Questions;
import com.internship.backend.pojo.table.QuestionsResult;
import com.internship.backend.service.questions.QuestionsService;
import com.internship.backend.service.questions.UseranswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class QuestionsController {
    @Autowired
    private QuestionsService questionsService;
    @Autowired
    private UseranswerService useranswerService;

    @GetMapping("/questions/query")
    public QuestionsResult getQuestions(){
        List<Questions> list = questionsService.getQuestions();
        return new QuestionsResult(0, list.size(), list);
    }

    @PostMapping("/questions/answer/add")
    public Map<String, String> addUseranswer(@RequestParam Map<String, String> data){
        return useranswerService.addUseranswer(data);
    }
}
