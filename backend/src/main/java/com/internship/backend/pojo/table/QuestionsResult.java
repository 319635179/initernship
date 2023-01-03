package com.internship.backend.pojo.table;

import com.internship.backend.pojo.Questions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionsResult {
    private Integer code;
    private Integer count;
    private List<Questions> data;
}
