module ru.zelmex.zad3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.zelmex.zad3 to javafx.fxml;
    exports ru.zelmex.zad3;
}