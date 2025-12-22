package ru.zelmex.zad1;

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

    private int count = 0;
    private int totalNumbers = 0;

    @FXML
    protected void onHelloButtonClick() {
        // Оставляем для совместимости
    }

    @FXML
    void OkButtonOnAction(ActionEvent event) {
        answerLabel.setText(String.valueOf(count));

        showAlert("Результат", "Количество четных чисел, кратных 7: " + count +
                "\nВсего чисел введено: " + totalNumbers);
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {
        String text = numberTextField.getText().trim();
        if (text.isEmpty()) return;

        try {
            int num = Integer.parseInt(text);

            if (num == 0) {
                dataListView.getItems().add("0 - конец ввода");
                answerLabel.setText("Ввод завершен");
                addButton.setDisable(true);
                numberTextField.setDisable(true);
                showAlert("Ввод завершен", "Нажмите OK для подсчета");
                return;
            }

            if (Math.abs(num) > 30000) {
                showAlert("Ошибка", "Число по модулю должно быть ≤ 30000");
                return;
            }

            if (totalNumbers >= 1000) {
                showAlert("Ошибка", "Превышено максимальное количество чисел (1000)");
                return;
            }

            totalNumbers++;
            dataListView.getItems().add("Число " + totalNumbers + ": " + num);

            if (num % 2 == 0 && num % 7 == 0) {
                count++;
            }

            answerLabel.setText("Четных кратных 7: " + count);

        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите целое число");
        }

        numberTextField.clear();
        numberTextField.requestFocus();
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        dataListView.getItems().clear();
        count = 0;
        totalNumbers = 0;
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