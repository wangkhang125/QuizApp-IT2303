/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services.exam;

import com.thk.pojo.Question;
import com.thk.services.questions.BaseQuestionServices;
import com.thk.services.questions.LevelQuestionServicesDecorator;
import com.thk.services.questions.LimitQuestionServicesDecorator;
import com.thk.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class FixedExamStrategy extends ExamStrategy {

    @Override
    public List<Question> getExamQuestion() throws SQLException {
        List<Question> examQuestions = new ArrayList<>();
        
        for (int i = 0; i < Configs.SPECIFIC_QUESTION_RATES.length; i++){
            BaseQuestionServices bs = 
                    new LimitQuestionServicesDecorator(new LevelQuestionServicesDecorator(Configs.questionServices, i+1), 
                            (int)(Configs.SPECIFIC_QUESTION_RATES[i]*Configs.FIXED_QUESTION_AMOUNT));
            examQuestions.addAll(bs.list());
        }
        
        return examQuestions;
    }
    
}
