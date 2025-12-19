package ru.zelmex.prac54;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label resultLabel;

    @FXML
    private TextField aField;

    @FXML
    private TextField xField;

    @FXML
    private TextField cField;

    @FXML
    private TextField wField;

    @FXML
    private TextField tField;

    @FXML
    private void calculate() {
        double a = Double.parseDouble(aField.getText());
        double x = Double.parseDouble(xField.getText());
        double c = Double.parseDouble(cField.getText());
        double w = Double.parseDouble(wField.getText());
        double t = Double.parseDouble(tField.getText());

        double g = c + 24.8;
        double numerator = 5 * Math.pow(x, 3) + Math.sqrt(a * x) + Math.sqrt(Math.abs(a + x));
        double denominator = 4 * Math.pow(Math.cos(a * x), 2);
        double y = numerator / denominator + g * x + w * t;

        resultLabel.setText("Результат: " + y);
    }
}