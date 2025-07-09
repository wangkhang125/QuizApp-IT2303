package com.thk.quizapp;

import com.thk.pojo.Category;
import com.thk.pojo.Choice;
import com.thk.pojo.Level;
import com.thk.pojo.Question;
import com.thk.utils.Configs;
import com.thk.utils.MyAlert;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class QuestionController implements Initializable {
    @FXML private ComboBox<Category> cbCates;
    @FXML private ComboBox<Level> cbLevels;
    @FXML private VBox vboxChoices;
    @FXML private Button addBtn;
    @FXML private TextArea txtContent;
    @FXML private TableView<Question> tbQuestions;
    @FXML private TextField txtSearch;
    @FXML private ToggleGroup toggleChoice;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.cbCates.setItems(FXCollections.observableList(Configs.categoryServices.getCategories()));
            this.cbLevels.setItems(FXCollections.observableList(Configs.levelServices.getLevels()));

            this.loadColumn();
            this.loadQuestion(Configs.questionService.getQuestions());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.txtSearch
            .textProperty()
            .addListener(e -> {
                try {
                    this.loadQuestion(
                        Configs.questionService.getQuestions(this.txtSearch.getText()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
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

            Configs.questionService.addQuestion(builder.build());
            this.tbQuestions.getItems().add(0, builder.build());
            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!");
        } catch (SQLException ex) {
            MyAlert.getInstance().showMsg("Thêm câu hỏi thất bại!");
        } catch (Exception ex) {
            MyAlert.getInstance().showMsg("Dữ liệu không hợp lệ!");
        }
    }

    private void loadColumn() {
        TableColumn colId = new TableColumn("Id");
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colId.setPrefWidth(100);

        TableColumn colContent = new TableColumn("Nội dung câu hỏi");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        colContent.setPrefWidth(250);

        TableColumn colAction = new TableColumn();
        colAction.setCellFactory(
            e -> {
                TableCell cell = new TableCell();
                Button btn = new Button("Xóa");
                btn.setOnAction(
                    event -> {
                        Optional<ButtonType> t = MyAlert.getInstance().showMsg("Xóa câu hỏi thì các lựa chọn cũng bị xóa theo. Bạn chắc chắn không?",
                                                Alert.AlertType.CONFIRMATION);

                        if (t.isPresent() && t.get().equals(ButtonType.OK)) {
                            try {
                                Question q = (Question) cell.getTableRow().getItem();
                                Configs.questionService.deleteQuestion(q.getId());
                                this.tbQuestions.getItems().remove(q);
                                MyAlert.getInstance().showMsg("Xóa thành công");
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                MyAlert.getInstance().showMsg("Xóa thất bại!", Alert.AlertType.WARNING);
                            }
                        }
                    });

                cell.setGraphic(btn);
                return cell;
                });

        this.tbQuestions.getColumns().addAll(colId, colContent, colAction);
    }

    private void loadQuestion(List<Question> questions) {
        this.tbQuestions.setItems(FXCollections.observableList(questions));
    }
}
