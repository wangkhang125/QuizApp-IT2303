/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services.exam;

import com.thk.pojo.Question;
import com.thk.services.questions.BaseQuestionServices;
import com.thk.services.questions.LimitQuestionServicesDecorator;
import com.thk.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class SpecificExamStrategy extends ExamStrategy {

    private int num;

    public SpecificExamStrategy(int num) {
        this.num = num;
    }
    
    @Override
    public List<Question> getExamQuestion() throws SQLException {
        BaseQuestionServices bs = new LimitQuestionServicesDecorator(Configs.questionServices, this.num);
        return bs.list();
    }
    
}
