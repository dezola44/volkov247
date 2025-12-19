module ru.zelmex.prac54 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.prac54 to javafx.fxml;
    exports ru.zelmex.prac54;
}