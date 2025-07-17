package com.thk.quizapp;

import com.thk.pojo.Category;
import com.thk.pojo.Level;
import com.thk.pojo.Question;
import com.thk.services.FlyweightFactory;
import com.thk.services.questions.BaseQuestionServices;
import com.thk.services.questions.CategoryQuestionServicesDecorator;
import com.thk.services.questions.LevelQuestionServicesDecorator;
import com.thk.services.questions.LimitQuestionServicesDecorator;
import com.thk.utils.Configs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PracticeController implements Initializable {
    @FXML private VBox vboxQuestions;
    @FXML private Text txtQuestionContent;
    @FXML private Text txtQuestionResult;
    @FXML private TextField txtQuestionAmount;
    @FXML private ComboBox<Category> cbPracticeCates;
    @FXML private ComboBox<Level> cbPracticeLevels;

    private List<Question> questions;
    private int currentQuestion = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbPracticeCates.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.categoryServices, "categories")));
            this.cbPracticeLevels.setItems(FXCollections.observableList(FlyweightFactory.getData(Configs.levelServices, "levels")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void handleStart(ActionEvent ev){
        try {
            
            BaseQuestionServices bqs = Configs.questionServices;
            
            Category cate = this.cbPracticeCates.getSelectionModel().getSelectedItem();
            if (cate != null)
                bqs = new CategoryQuestionServicesDecorator(bqs, cate);
            
            Level lvl = this.cbPracticeLevels.getSelectionModel().getSelectedItem();
            if (lvl != null)
                bqs = new LevelQuestionServicesDecorator(bqs, lvl);
            
            bqs = new LimitQuestionServicesDecorator(bqs, Integer.parseInt(this.txtQuestionAmount.getText()));
            this.questions = bqs.list();
            this.loadPracticeQuestion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void handleNext(ActionEvent ev){
        if (this.currentQuestion < this.questions.size()-1){
            this.currentQuestion++;
            this.loadPracticeQuestion();
            this.txtQuestionResult.setText("");
        }
    }
    
    public void handleCheck(ActionEvent ev){
        Question q = this.questions.get(this.currentQuestion);
        for (int i = 0; i < q.getChoices().size(); i++){
            if (q.getChoices().get(i).isCorrect()){
                RadioButton rdo = (RadioButton) this.vboxQuestions.getChildren().get(i);
                this.txtQuestionResult.getStyleClass().clear();
                if (rdo.isSelected()){
                    this.txtQuestionResult.setText("Congratulation! You are correct!");
                    this.txtQuestionResult.getStyleClass().add("Correct");
                } else{
                    this.txtQuestionResult.setText("Sorry! You are wrong!");
                    this.txtQuestionResult.getStyleClass().add("Wrong");
                }
                break;
            }
        }
    }

    private void loadPracticeQuestion() {
        Question q = this.questions.get(this.currentQuestion);
        this.txtQuestionContent.setText(q.getContent());
        
        this.vboxQuestions.getChildren().clear();
        
        ToggleGroup toggleChoices = new ToggleGroup();
        for (var c : q.getChoices()) {
            RadioButton rdoChoice = new RadioButton(c.getContent());
            rdoChoice.setToggleGroup(toggleChoices);
            this.vboxQuestions.getChildren().add(rdoChoice);
        }
    }
}
