package ru.zelmex.prac5.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Label resultLabel;

    @FXML
    private TextField rField;

    @FXML
    private void calculate() {
        double r = Double.parseDouble(rField.getText());

        double l = 2 * 3.14 * r;
        double s = 3.14 * r * r;

        resultLabel.setText("Длина: " + l + ", Площадь: " + s);
    }
}