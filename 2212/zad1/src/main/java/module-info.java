module ru.zelmex.zad1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.zelmex.zad1 to javafx.fxml;
    exports ru.zelmex.zad1;
}