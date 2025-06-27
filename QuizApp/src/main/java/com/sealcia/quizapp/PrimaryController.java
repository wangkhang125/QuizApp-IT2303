package com.sealcia.quizapp;

import com.sealcia.utils.MyAlert;
import com.sealcia.utils.MyStage;
import com.sealcia.utils.theme.Theme;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class PrimaryController implements Initializable {
    @FXML private ComboBox<Theme> cbThemes;
    
    public void handleQuestionManagement(ActionEvent event) throws IOException {
        MyStage.getInstance().showStage("question.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbThemes.setItems(FXCollections.observableArrayList(Theme.values()));
    }

    public void changeTheme(ActionEvent event) {
        switch(this.cbThemes.getSelectionModel().getSelectedItem()) {
            case DEFAULT:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("styles.css").toExternalForm());
                break;
            case DARK:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("dark.css").toExternalForm());
                break;
            case LIGHT:
                this.cbThemes.getScene().getRoot().getStylesheets().clear();
                this.cbThemes.getScene().getRoot().getStylesheets().add(App.class.getResource("light.css").toExternalForm());
                break;
        }
    }
    
    public void handlePractice(ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }
}
