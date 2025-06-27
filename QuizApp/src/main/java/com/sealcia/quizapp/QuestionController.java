package com.sealcia.quizapp;

import com.sealcia.pojo.Category;
import com.sealcia.services.CategoryServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
