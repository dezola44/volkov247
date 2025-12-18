module ru.zelmex.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.demo to javafx.fxml;
    exports ru.zelmex.demo;
}