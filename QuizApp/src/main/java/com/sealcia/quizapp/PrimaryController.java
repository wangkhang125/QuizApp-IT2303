package com.sealcia.quizapp;

import com.sealcia.utils.MyAlert;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {
    public void handleQuestionManagement(ActionEvent event) throws IOException {
        Scene scene = new Scene(new FXMLLoader(App.class.getResource("question.fxml")).load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("QUIZ APP");
        stage.show();
    }
    
    public void handlePractice(ActionEvent event) {
        MyAlert.getInstance().showMsg("Coming soon...");
    }
}
