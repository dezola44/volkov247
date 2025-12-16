module ru.zelmex.prac1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.prac1 to javafx.fxml;
    exports ru.zelmex.prac1;
}