package com.internship.backend.service.impl.questions;

import com.internship.backend.mapper.UseranswerMapper;
import com.internship.backend.pojo.Useranswer;
import com.internship.backend.service.questions.UseranswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UseranswerServiceImpl implements UseranswerService {
    @Autowired
    private UseranswerMapper useranswerMapper;


    @Override
    public List<Useranswer> getUseranswer() {
        return null;
    }

    @Override
    public Map<String, String> addUseranswer(Map<String, String> data) {
        String username = data.get("username");
        String tel = data.get("tel");
        String answer = data.get("answer");
        Map<String, String> map = new HashMap<>();
        if(username == null || tel == null || answer == null){
            map.put("error_message", "提交失败");
            return map;
        }
        System.out.println(username + ' ' + tel + ' ' + answer);
        useranswerMapper.insert(new Useranswer(null, username, tel, answer, new Date()));
        map.put("error_message", "success");
        return map;
    }
}
