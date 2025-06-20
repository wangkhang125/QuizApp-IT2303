module com.sealcia.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires lombok;
    opens com.sealcia.quizapp to javafx.fxml;
    exports com.sealcia.quizapp;
}
