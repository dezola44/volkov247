package ru.zelmex.zad5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private Button OkButton;

    @FXML
    private Button addButton;

    @FXML
    private Label answerLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<String> dataListView;

    @FXML
    private TextField numberTextField;

    private boolean calculated = false;

    @FXML
    void OkButtonOnAction(ActionEvent event) {
        if (calculated) {
            return;
        }

        dataListView.getItems().clear();
        dataListView.getItems().add("Последовательность a_i:");
        dataListView.getItems().add("a₀ = 1");
        dataListView.getItems().add("a₁ = 1");

        double[] a = new double[15];
        a[0] = 1.0;
        a[1] = 1.0;

        double product = a[0] * a[1];

        for (int i = 2; i <= 14; i++) {
            a[i] = a[i-2] + a[i-1] / Math.pow(2, i-1);
            product *= a[i];

            String aValue = String.format("%.10f", a[i]);
            dataListView.getItems().add("a" + i + " = " + aValue);
        }

        String result = String.format("%.10f", product);
        answerLabel.setText(result);
        calculated = true;

        addButton.setDisable(true);
        numberTextField.setDisable(true);

        showAlert("Результат", "Произведение a₀·a₁·...·a₁₄ = " + result);
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {
        String text = numberTextField.getText().trim();
        if (text.isEmpty()) return;

        try {
            int num = Integer.parseInt(text);

            if (num == 15) {
                answerLabel.setText("Нажмите ОК");
                showAlert("Инструкция", "Нажмите кнопку ОК для вычисления произведения последовательности");
                addButton.setDisable(true);
                numberTextField.setDisable(true);
            } else {
                showAlert("Информация", "Введите число 15 для продолжения");
            }

        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите число 15");
        }

        numberTextField.clear();
        numberTextField.requestFocus();
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        dataListView.getItems().clear();
        calculated = false;
        answerLabel.setText("нет ответа");
        numberTextField.clear();
        addButton.setDisable(false);
        numberTextField.setDisable(false);
        numberTextField.requestFocus();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}