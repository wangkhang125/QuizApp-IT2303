package com.sealcia.quizapp;

import com.sealcia.pojo.Category;
import com.sealcia.pojo.Level;
import com.sealcia.services.CategoryServices;
import com.sealcia.services.LevelServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class QuestionController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private VBox vbox;
    
    private CategoryServices categoryServices = new CategoryServices();
    private LevelServices levelServices = new LevelServices();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cbCates.setItems(FXCollections.observableList(categoryServices.getCategories()));
            cbLevels.setItems(FXCollections.observableList(levelServices.getLevels()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void addChoice(ActionEvent event) {
        HBox hbox = new HBox();
        RadioButton rdoBtn = new RadioButton();
    }
}
