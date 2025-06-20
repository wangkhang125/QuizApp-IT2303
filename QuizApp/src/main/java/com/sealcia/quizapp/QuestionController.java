package com.sealcia.quizapp;

import com.sealcia.pojo.Category;
import com.sealcia.services.CategoryServices;
import com.sealcia.utils.JdbcConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class QuestionController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    private CategoryServices categoryServices = new CategoryServices();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cbCates.setItems(FXCollections.observableList(categoryServices.getCategories()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }    
    
}
