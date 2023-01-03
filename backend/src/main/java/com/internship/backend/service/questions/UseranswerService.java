package com.internship.backend.service.questions;

import com.internship.backend.pojo.Useranswer;

import java.util.List;
import java.util.Map;

public interface UseranswerService {
    List<Useranswer> getUseranswer();

    Map<String, String> addUseranswer(Map<String, String> data);
}
