package com.sealcia.quizapp;

import com.sealcia.pojo.Category;
import com.sealcia.pojo.Choice;
import com.sealcia.pojo.Level;
import com.sealcia.pojo.Question;
import com.sealcia.services.CategoryServices;
import com.sealcia.services.LevelServices;
import com.sealcia.services.QuestionService;
import com.sealcia.utils.MyAlert;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
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
    @FXML private TextArea txtContent;

    @FXML private ToggleGroup toggleChoice;
    private static final CategoryServices categoryServices = new CategoryServices();
    private static final LevelServices levelServices = new LevelServices();
    private static final QuestionService questionService = new QuestionService();

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
        rdoBtn.setToggleGroup(toggleChoice);
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

            for (var c : this.vboxChoices.getChildren()) {
                HBox h = (HBox) c;

                Choice choice = new Choice(((TextField) h.getChildren().get(1)).getText(),
                                ((RadioButton) h.getChildren().get(0)).isSelected());
                builder.addChoice(choice);
            }

            questionService.addQuestion(builder.build());
            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Thêm câu hỏi thất bại!");
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu không hợp lệ!");
        }
    }
}
