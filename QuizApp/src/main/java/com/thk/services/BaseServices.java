 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thk.services;

import com.thk.pojo.Category;
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
public abstract class BaseServices<T> {
    public abstract PreparedStatement getStatements(Connection conn) throws SQLException;
    public abstract List<T> getResults(ResultSet rs) throws SQLException;
    
    public List<T> list() throws SQLException{

        Connection conn = JdbcConnector.getInstance().connect();
        PreparedStatement pStm = this.getStatements(conn);
        
        return this.getResults(pStm.executeQuery());
    }
}
