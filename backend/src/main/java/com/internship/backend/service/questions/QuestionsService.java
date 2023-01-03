package com.internship.backend.service.questions;

import com.internship.backend.pojo.Questions;

import java.util.List;
import java.util.Map;

public interface QuestionsService {
    List<Questions> getQuestions();

    List<Questions> getQuestions(String search);

    Map<String, String> addQuestions(Map<String,String> data);

    Map<String, String> modifyQuestions(Map<String,String> data);

    Map<String, String> removeQuestions(Map<String,String> data);
}
