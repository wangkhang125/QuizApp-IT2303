package com.thk.services;

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

public class QuestionServices {
    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int questionId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next()) questionId = r.getInt(1);

            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";

            for (var c : q.getChoices()) {
                stm = conn.prepareCall(sql);
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);

                stm.executeUpdate();
            }

            conn.commit();
        } else conn.rollback();
    }

    public List<Question> getQuestions() throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();

        Statement stm = connection.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question");

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Question> getQuestions(String kw) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();
        String sql = "SELECT * FROM question WHERE content like concat('%', ?, '%')";
        PreparedStatement stm = connection.prepareCall(sql);
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            Question q = new Question.Builder(rs.getInt("id"), rs.getString("content")).build();
            questions.add(q);
        }
        return questions;
    }

    public boolean deleteQuestion(int questionId) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();
        PreparedStatement stm = connection.prepareCall("DELETE FROM question WHERE id=?");
        stm.setInt(1, questionId);
        return stm.executeUpdate() > 0;
    }

    public List<Question> getQuestions(int num) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();

        PreparedStatement stm =
                connection.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ?");
        stm.setInt(1, num);
        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");

            Question q = new Question.Builder(id, content).addChoices(this.getChoicesByQuestionId(id)).build();
            questions.add(q);
        }
        return questions;
    }

    public List<Choice> getChoicesByQuestionId(int id) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();

        PreparedStatement stm = connection.prepareCall("SELECT * FROM choice WHERE question_id=?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        List<Choice> choices = new ArrayList<>();
        while (rs.next()) {
            choices.add(new Choice(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_correct")));
        }
        return choices;
    }
}
