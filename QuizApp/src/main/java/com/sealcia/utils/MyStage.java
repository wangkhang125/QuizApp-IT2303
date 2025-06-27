package com.sealcia.utils;

import com.sealcia.quizapp.App;
import com.sealcia.utils.theme.ThemeFactory;
import com.sealcia.utils.theme.ThemeManager;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MyStage {
    private static MyStage instance;
    private final Stage stage;
    private static Scene scene;
    
    private MyStage() {
        stage = new Stage();
        stage.setTitle("QUIZ APP");
    }
    
    public static MyStage getInstance() {
        if (instance == null) {
            instance = new MyStage();
        }
        return instance;
    }
    
    public void showStage(String fxml) throws IOException {
        if (!this.stage.isShowing()) {
            if (scene == null) {
                scene = new Scene(new FXMLLoader(App.class.getResource(fxml)).load());
            } else {
                scene.setRoot(new FXMLLoader(App.class.getResource(fxml)).load());
            }
            ThemeManager.applyTheme(scene);
            this.stage.setScene(scene);
            this.stage.show();
        }
    }
}
