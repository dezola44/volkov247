package ru.zelmex.prac52;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label resultLabel;

    @FXML
    private TextField x1Field;

    @FXML
    private TextField y1Field;

    @FXML
    private TextField x2Field;

    @FXML
    private TextField y2Field;

    @FXML
    private void calculate() {
        double x1 = Double.parseDouble(x1Field.getText());
        double y1 = Double.parseDouble(y1Field.getText());
        double x2 = Double.parseDouble(x2Field.getText());
        double y2 = Double.parseDouble(y2Field.getText());

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        resultLabel.setText("Расстояние: " + distance);
    }
}