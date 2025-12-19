module ru.zelmex.prac52 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.prac52 to javafx.fxml;
    exports ru.zelmex.prac52;
}