module ru.zelmex.demo2012 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.zelmex.demo2012 to javafx.fxml;
    exports ru.zelmex.demo2012;
}