module ru.zelmex.zad4 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.zelmex.zad4 to javafx.fxml;
    exports ru.zelmex.zad4;
}