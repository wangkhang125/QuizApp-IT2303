//package com.thk.services;
//
//import com.thk.pojo.Choice;
//import com.thk.pojo.Question;
//import com.thk.utils.JdbcConnector;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class QuestionServices {
//    public void addQuestion(Question q) throws SQLException {
//        Connection conn = JdbcConnector.getInstance().connect();
//        conn.setAutoCommit(false);
//        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";
//
//        PreparedStatement pStm = conn.prepareCall(sql);
//        pStm.setString(1, q.getContent());
//        pStm.setString(2, q.getHint());
//        pStm.setString(3, q.getImage());
//        pStm.setInt(4, q.getCategory().getId());
//        pStm.setInt(5, q.getLevel().getId());
//
//        if (pStm.executeUpdate() > 0) {
//            int questionId = -1;
//            ResultSet r = pStm.getGeneratedKeys();
//            if (r.next()) questionId = r.getInt(1);
//
//            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";
//
//            for (var c : q.getChoices()) {
//                pStm = conn.prepareCall(sql);
//                pStm.setString(1, c.getContent());
//                pStm.setBoolean(2, c.isCorrect());
//                pStm.setInt(3, questionId);
//
//                pStm.executeUpdate();
//            }
//
//            conn.commit();
//        } else conn.rollback();
//    }
//
//    public List<Question> getQuestions() throws SQLException {
//        Connection connection = JdbcConnector.getInstance().connect();
//
//        Statement stm = connection.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM question");
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
//            questions.add(q);
//        }
//        return questions;
//    }
//
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
//
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
//
//    public List<Choice> getChoicesByQuestionId(int id) throws SQLException {
//        Connection connection = JdbcConnector.getInstance().connect();
//
//        PreparedStatement pStm = connection.prepareCall("SELECT * FROM choice WHERE question_id=?");
//        pStm.setInt(1, id);
//        ResultSet rs = pStm.executeQuery();
//
//        List<Choice> choices = new ArrayList<>();
//        while (rs.next()) {
//            choices.add(new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct")));
//        }
//        return choices;
//    }
//    
//    public boolean deleteQuestion(int questionId) throws SQLException {
//        Connection connection = JdbcConnector.getInstance().connect();
//        PreparedStatement pStm = connection.prepareCall("DELETE FROM question WHERE id = ?");
//        pStm.setInt(1, questionId);
//        return pStm.executeUpdate() > 0;
//    }
//}
