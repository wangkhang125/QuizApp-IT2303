package com.sealcia.utils;

import javafx.scene.control.Alert;

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
}
