module com.sealcia.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.sealcia.quizapp to javafx.fxml;
    exports com.sealcia.quizapp;
}
