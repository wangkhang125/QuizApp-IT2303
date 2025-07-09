module com.thk.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires lombok;

    opens com.thk.quizapp to
            javafx.fxml;

    exports com.thk.quizapp;
    exports com.thk.pojo;
}
