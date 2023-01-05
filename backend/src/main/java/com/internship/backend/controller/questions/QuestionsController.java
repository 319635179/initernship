package com.internship.backend.controller.questions;

import com.internship.backend.pojo.Questions;
import com.internship.backend.pojo.Useranswer;
import com.internship.backend.pojo.table.QuestionsResult;
import com.internship.backend.pojo.table.UseranswerResult;
import com.internship.backend.service.questions.QuestionsService;
import com.internship.backend.service.questions.UseranswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @GetMapping("/questions/query/answer")
    public UseranswerResult getUseranswer(){
        List<Useranswer> list = useranswerService.getUseranswer();
        return new UseranswerResult(0, list.size(), list);
    }

    @PostMapping("/questions/answer/add")
    public Map<String, String> addUseranswer(@RequestParam Map<String, String> data){
        return useranswerService.addUseranswer(data);
    }

    @PostMapping("/questions/answer/getbyid")
    public Map<String, String> getUseranswerById(@RequestParam String username, @RequestParam String tel){
        Useranswer useranswer = useranswerService.getAnswerById(username, tel);
        Map<String, String> map = new HashMap<>();
        if(useranswer==null){
            map.put("error_message", "null");
        }else{
            map.put("error_message", "success");
            map.put("data", useranswer.toString());
        }
        return map;
    }

    @PostMapping("/questions/answer/delete")
    public Map<String, String> deleteUseranswer(@RequestParam String username, @RequestParam String tel){
        return useranswerService.deletAnswer(username, tel);
    }
}
