/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services.questions;

import com.thk.pojo.Question;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public class LimitQuestionServicesDecorator extends QuestionDecorator {
    private int num;

    public LimitQuestionServicesDecorator(BaseQuestionServices decorator, int num) {
        super(decorator);
        this.num = num;
    }

    @Override
    public List<Question> list() throws SQLException {
        List<Question> questions = super.list();
        
        for (var q: questions)
            q.setChoices(this.getChoicesByQuestionId(q.getId()));
        
        return questions;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " ORDER BY rand() LIMIT ?";
        params.add(this.num);
        return sql;
    }
    
}
