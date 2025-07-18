/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.thk.quizapp;

import com.thk.pojo.Choice;
import com.thk.pojo.Question;
import com.thk.services.exam.ExamStrategy;
import com.thk.services.exam.ExamTypes;
import com.thk.services.exam.FixedExamStrategy;
import com.thk.services.exam.SpecificExamStrategy;
import com.thk.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class ExamController implements Initializable {

    @FXML
    private ComboBox<ExamTypes> cbExamTypes;
    @FXML
    private TextField txtExamAmount;
    @FXML
    private ListView<Question> lstQuestions;

    private ExamStrategy es;
    private List<Question> examQuestions;
    private Map<Integer, Choice> examResults = new HashMap<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbExamTypes.setItems(FXCollections.observableArrayList(ExamTypes.values()));
        this.txtExamAmount.setVisible(false);
        this.cbExamTypes.getSelectionModel().selectedItemProperty().addListener(e -> {
            if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC) {
                this.txtExamAmount.setVisible(true);
            } else {
                this.txtExamAmount.setVisible(false);
            }
        });
    }

    public void handleStartExam(ActionEvent ev) throws SQLException {
        if (this.cbExamTypes.getSelectionModel().getSelectedItem() == ExamTypes.SPECIFIC) {
            es = new SpecificExamStrategy(Integer.parseInt(this.txtExamAmount.getText()));
        } else {
            es = new FixedExamStrategy();
        }

        examQuestions = es.getExamQuestion();
        this.lstQuestions.setItems(FXCollections.observableList(examQuestions));

        this.lstQuestions.setCellFactory(params -> new ListCell<>() {
            @Override
            protected void updateItem(Question q, boolean empty) {
                super.updateItem(q, empty);

                if (q == null || empty == true) {
                    this.setGraphic(null);
                } else {

                    VBox vb = new VBox(5);
                    vb.setStyle("-fx-border-width:3; -fx-border-color: violet; -fx-padding:5");

                    Text txt = new Text(q.getContent());
                    vb.getChildren().add(txt);
                    ToggleGroup toggle = new ToggleGroup();

                    for (var ch : q.getChoices()) {
                        RadioButton rdoBtn = new RadioButton(ch.getContent());
                        rdoBtn.setToggleGroup(toggle);

                        if (examResults.get(q.getId()) == ch)
                            rdoBtn.setSelected(true);
                        
                        rdoBtn.setOnAction(e -> {
                            if (rdoBtn.isSelected()) {
                                examResults.put(q.getId(), ch);
                            }
                        });

                        vb.getChildren().add(rdoBtn);
                    }

                    this.setGraphic(vb);
                }
            }
        });
    }

    public void handleFinishExam(ActionEvent ev) {
        if (!examResults.isEmpty()) {
            int count = 0;
            for (var ch : examResults.values()) {
                if (ch.isCorrect() == true)
                    count++;
            }
            MyAlert.getInstance().showMsg(String.format("BẠN ĐÃ LÀM ĐÚNG %d/%d CÂU!", count, examQuestions.size()), Alert.AlertType.INFORMATION);
        }
    }

    public void handleSaveExam(ActionEvent ev) {
        if (examQuestions == null || examQuestions.isEmpty())
            MyAlert.getInstance().showMsg("KHÔNG CÓ CÂU HỎI NÀO ĐỂ LƯU!", Alert.AlertType.WARNING);
        else{
            try{
                es.saveExam(examQuestions);
                MyAlert.getInstance().showMsg("LƯU CÂU HỎI THÀNH CÔNG!", Alert.AlertType.INFORMATION);
            }catch(SQLException ex){
                MyAlert.getInstance().showMsg("HỆ THỐNG BỊ LỖI: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

}
