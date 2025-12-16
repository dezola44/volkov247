module ru.zelmex.prac2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.prac2 to javafx.fxml;
    exports ru.zelmex.prac2;
}