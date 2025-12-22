module ru.zelmex.zad5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.zelmex.zad5 to javafx.fxml;
    exports ru.zelmex.zad5;
}