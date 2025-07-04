package com.sealcia.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MyAlert {
    private static MyAlert instance;
    private final Alert alert;

    private MyAlert() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("QUIZ APP");
    }

    public static MyAlert getInstance() {
        if (instance == null) {
            instance = new MyAlert();
        }
        return instance;
    }

    public void showMsg(String message) {
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    public Optional<ButtonType> showMsg(String message, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
