package com.internship.backend.pojo.table;

import com.internship.backend.pojo.Useranswer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UseranswerResult {
    private Integer code;
    private Integer count;
    private List<Useranswer> data;
}
