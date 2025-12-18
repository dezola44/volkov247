module ru.zelmex.prac4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.prac4 to javafx.fxml;
    exports ru.zelmex.prac4;
}