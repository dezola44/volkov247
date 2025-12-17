module ru.zelmex.prac3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.prac3 to javafx.fxml;
    exports ru.zelmex.prac3;
}