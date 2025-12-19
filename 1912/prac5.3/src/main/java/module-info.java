module ru.zelmex.prac53 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.prac53 to javafx.fxml;
    exports ru.zelmex.prac53;
    exports ru.zelmex.prac53.controller;
    opens ru.zelmex.prac53.controller to javafx.fxml;
}