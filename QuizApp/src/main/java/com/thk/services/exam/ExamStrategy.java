/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services.exam;

import com.thk.pojo.Exam;
import com.thk.pojo.Question;
import com.thk.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author admin
 */
public abstract class ExamStrategy {
    public abstract List<Question> getExamQuestion() throws SQLException;
    
    public void saveExam(List<Question> questions) throws SQLException{
        Connection conn = JdbcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        Exam exm = new Exam(questions);
        
        String sql = "INSERT INTO exam(tittle, created_date) VALUES(?, ?)";
        PreparedStatement pStm = conn.prepareCall(sql);
        pStm.setString(1, exm.getTittle());
        pStm.setString(2, exm.getCreatedDate().toString());
        
        if(pStm.executeUpdate() > 0){
            int exID = -1;
            ResultSet rs = pStm.getGeneratedKeys();
            if(rs.next())
                exID = rs.getInt(1);
            
            sql = "INSERT INTO exam_question(exam_id, question_id) VALUES (?, ?)";
            pStm = conn.prepareCall(sql);
            
            for(var q: questions){
                pStm.setInt(1, exID);
                pStm.setInt(2, q.getId());
                pStm.executeUpdate();
            }
            
            conn.commit();
        } else
            conn.rollback();
    }
}
