module mine.calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens mine.calculator to javafx.fxml;
    exports mine.calculator;
}