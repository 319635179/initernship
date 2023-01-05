package com.internship.backend.service.impl.questions;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
        QueryWrapper<Useranswer> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*");
        return useranswerMapper.selectList(queryWrapper);
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
        int red = 0, blue = 0, yellow = 0, green = 0;
        for(int i  = 0; i < 15; i++){
            if(answer.charAt(i) == 'A'){
                red++;
            }else if(answer.charAt(i) == 'B'){
                blue++;
            }else if(answer.charAt(i) == 'C'){
                yellow++;
            }else if(answer.charAt(i) == 'D'){
                green++;
            }else{
                map.put("error_message", "未全部完成");
                return map;
            }
        }
        for(int i  = 15; i < 30; i++){
            if(answer.charAt(i) == 'D'){
                red++;
            }else if(answer.charAt(i) == 'C'){
                blue++;
            }else if(answer.charAt(i) == 'B'){
                yellow++;
            }else if(answer.charAt(i) == 'A'){
                green++;
            }else{
                map.put("error_message", "未全部完成");
                return map;
            }
        }
        Date date = new Date();
        useranswerMapper.insert(new Useranswer(null, username, tel, answer, date, red, blue, yellow, green));
        map.put("error_message", "success");
        return map;
    }

    @Override
    public Useranswer getAnswerById(String username, String tel) {
        QueryWrapper<Useranswer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("tel", tel);
        return useranswerMapper.selectOne(queryWrapper);
    }

    @Override
    public Map<String, String> deletAnswer(String username, String tel) {
        QueryWrapper<Useranswer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username).eq("tel", tel);
        Useranswer useranswer = useranswerMapper.selectOne(queryWrapper);
        Map<String, String> map = new HashMap<>();
        if(useranswer == null){
            map.put("error_message", "查询不到此测试");
            return map;
        }
        useranswerMapper.deleteById(useranswer);
        map.put("error_message", "success");
        return map;
    }
}
