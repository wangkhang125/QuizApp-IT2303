/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services.questions;

import com.thk.pojo.Question;
import com.thk.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author admin
 */
public class UpdateQuestionServices {
    public void addQuestion(Question q) throws SQLException {
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUES(?, ?, ?, ?, ?)";

        PreparedStatement pStm = conn.prepareCall(sql);
        pStm.setString(1, q.getContent());
        pStm.setString(2, q.getHint());
        pStm.setString(3, q.getImage());
        pStm.setInt(4, q.getCategory().getId());
        pStm.setInt(5, q.getLevel().getId());

        if (pStm.executeUpdate() > 0) {
            int questionId = -1;
            ResultSet r = pStm.getGeneratedKeys();
            if (r.next()) questionId = r.getInt(1);

            sql = "INSERT INTO choice(content, is_correct, question_id) VALUES(?, ?, ?)";

            for (var c : q.getChoices()) {
                pStm = conn.prepareCall(sql);
                pStm.setString(1, c.getContent());
                pStm.setBoolean(2, c.isCorrect());
                pStm.setInt(3, questionId);

                pStm.executeUpdate();
            }

            conn.commit();
        } else conn.rollback();
    }

    public boolean deleteQuestion(int questionId) throws SQLException {
        Connection connection = JdbcConnector.getInstance().connect();
        PreparedStatement pStm = connection.prepareCall("DELETE FROM question WHERE id = ?");
        pStm.setInt(1, questionId);
        return pStm.executeUpdate() > 0;
    }
}
