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

public class QuestionServices extends BaseQuestionServices {

    @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM question WHERE 1=1";
    }
    
    
//    public List<Question> getQuestions(String kw) throws SQLException {
//        Connection connection = JdbcConnector.getInstance().connect();
//        String sql = "SELECT * FROM question WHERE content like concat('%', ?, '%')";
//        PreparedStatement pStm = connection.prepareCall(sql);
//        pStm.setString(1, kw);
//        ResultSet rs = pStm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
//            questions.add(q);
//        }
//        return questions;
//    }

//    public List<Question> getQuestions(int num) throws SQLException {
//        Connection connection = JdbcConnector.getInstance().connect();
//        PreparedStatement pStm = connection.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
//        pStm.setInt(1, num);
//        ResultSet rs = pStm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//
//            Question q = new Question.Builder(id, content).addAllChoices(this.getChoicesByQuestionId(id)).build();
//            questions.add(q);
//        }
//        return questions;
//    }



    
}
