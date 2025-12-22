module ru.zelmex.demo20 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.demo20 to javafx.fxml;
    exports ru.zelmex.demo20;
}