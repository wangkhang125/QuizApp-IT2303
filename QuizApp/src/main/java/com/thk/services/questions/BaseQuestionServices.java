/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services.questions;

import com.thk.pojo.Choice;
import com.thk.pojo.Question;
import com.thk.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class BaseQuestionServices {
    public abstract String getSQL(List<Object> params);
    
    public List<Question> list() throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        
        List<Object> params = new ArrayList<>();
        PreparedStatement pStm = conn.prepareCall(this.getSQL(params));
        for (int i = 0; i < params.size(); i++)
            pStm.setObject(i+1, params.get(i));
        
        ResultSet rs = pStm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }
    
        public List<Choice> getChoicesByQuestionId(int id) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();

        PreparedStatement pStm = connection.prepareCall("SELECT * FROM choice WHERE question_id=?");
        pStm.setInt(1, id);
        ResultSet rs = pStm.executeQuery();

        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            choices.add(new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct")));
        }
        return choices;
    }
}
