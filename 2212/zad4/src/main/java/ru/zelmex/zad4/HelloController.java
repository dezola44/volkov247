package ru.zelmex.zad4;

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

    private int N = 0;

    @FXML
    void OkButtonOnAction(ActionEvent event) {
        if (N == 0) {
            answerLabel.setText("Введите N");
            showAlert("Ошибка", "Сначала введите число N (> 0)");
            return;
        }

        dataListView.getItems().clear();
        dataListView.getItems().add("N = " + N);
        dataListView.getItems().add("----------------");

        double sum = 1.0;
        double factorial = 1.0;

        for (int i = 1; i <= N; i++) {
            factorial *= i;
            double term = 1.0 / factorial;
            sum += term;

            dataListView.getItems().add("i=" + i + ": 1/" + i + "! = " + String.format("%.10f", term) +
                    ", сумма = " + String.format("%.10f", sum));
        }

        answerLabel.setText(String.format("e ≈ %.10f", sum));

        showAlert("Результат", "Приближение константы e при N = " + N +
                "\ne ≈ " + String.format("%.10f", sum) +
                "\nТочное значение e = 2.7182818284...");
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {
        String text = numberTextField.getText().trim();
        if (text.isEmpty()) return;

        try {
            int num = Integer.parseInt(text);

            if (num <= 0) {
                showAlert("Ошибка", "N должно быть > 0");
                return;
            }

            N = num;
            dataListView.getItems().add("N = " + N);
            answerLabel.setText("N = " + N);
            addButton.setDisable(true);
            numberTextField.setDisable(true);

        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите целое число");
        }

        numberTextField.clear();
        numberTextField.requestFocus();
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        dataListView.getItems().clear();
        N = 0;
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