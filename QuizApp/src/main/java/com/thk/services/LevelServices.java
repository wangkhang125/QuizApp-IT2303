package com.thk.services;

import com.thk.pojo.Level;
import com.thk.utils.JdbcConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LevelServices {
    public List<Level> getLevels() throws SQLException {
        List<Level> levels = new ArrayList<>();

        Connection conn = JdbcConnector.getInstance().connect();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM level");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String note = rs.getString("note");
            levels.add(new Level(id, name, note));
        }
        return levels;
    }
}
