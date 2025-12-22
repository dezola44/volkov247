module ru.zelmex.zad2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.zelmex.zad2 to javafx.fxml;
    exports ru.zelmex.zad2;
}