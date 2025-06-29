package com.sealcia.quizapp;

import com.sealcia.pojo.Category;
import com.sealcia.pojo.Level;
import com.sealcia.pojo.Question;
import com.sealcia.services.CategoryServices;
import com.sealcia.services.LevelServices;
import com.sealcia.utils.MyAlert;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private VBox vboxChoices;
    @FXML private Button addBtn;
    @FXML private TextField txtContent;

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
        hbox.getStyleClass().add("Main");

        RadioButton rdoBtn = new RadioButton();
        TextField txt = new TextField();
        txt.getStyleClass().add("Input");
        hbox.getChildren().addAll(rdoBtn, txt);

        this.vboxChoices.getChildren().add(hbox);
    }

    public void addQuestion(ActionEvent event) {
        try {
            Question.Builder builder = new Question.Builder(this.txtContent.getText(),
                            this.cbCates.getSelectionModel().getSelectedItem(),
                            this.cbLevels.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            MyAlert.getInstance().showMsg("Du lieu khong hop le");
        }
    }
}
