package ru.zelmex.prac53.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Label resultLabel;

    @FXML
    private TextField xField;

    @FXML
    private TextField aField;

    @FXML
    private TextField yField;

    @FXML
    private void calculate() {
        double x = Double.parseDouble(xField.getText());
        double a = Double.parseDouble(aField.getText());
        double y = Double.parseDouble(yField.getText());

        double price1 = a / x;
        double priceY = price1 * y;

        resultLabel.setText("1 кг: " + price1 + " руб, " + y + " кг: " + priceY + " руб");
    }
}