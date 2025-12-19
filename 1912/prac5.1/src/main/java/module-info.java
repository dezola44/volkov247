module ru.zelmex.prac5 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.prac5 to javafx.fxml;
    exports ru.zelmex.prac5;
    exports ru.zelmex.prac5.controller;
    opens ru.zelmex.prac5.controller to javafx.fxml;
}