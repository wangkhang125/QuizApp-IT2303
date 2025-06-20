package com.sealcia.services;

import com.sealcia.pojo.Category;
import com.sealcia.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryServices {

    public List<Category> getCategories() throws SQLException {
        List<Category> cates = new ArrayList<>();

        Connection conn = JdbcConnector.getInstance().connect();
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM category");

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            cates.add(new Category(id, name));
        }
        return cates;
    }
}
