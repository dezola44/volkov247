package ru.zelmex.zad2;

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

    private int sum = 0;
    private int totalNumbers = 0;

    @FXML
    void OkButtonOnAction(ActionEvent event) {
        answerLabel.setText(String.valueOf(sum));

        showAlert("Результат", "Сумма чисел, кратных 6 и оканчивающихся на 4: " + sum +
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
                showAlert("Ввод завершен", "Нажмите OK для расчета суммы");
                return;
            }

            if (num < 0) {
                showAlert("Ошибка", "Введите натуральное число");
                return;
            }

            if (num > 30000) {
                showAlert("Ошибка", "Число должно быть ≤ 30000");
                return;
            }

            if (totalNumbers >= 1000) {
                showAlert("Ошибка", "Превышено максимальное количество чисел (1000)");
                return;
            }

            totalNumbers++;
            dataListView.getItems().add("Число " + totalNumbers + ": " + num);

            if (num % 6 == 0 && num % 10 == 4) {
                sum += num;
            }

            answerLabel.setText("Текущая сумма: " + sum);

        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите целое число");
        }

        numberTextField.clear();
        numberTextField.requestFocus();
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        dataListView.getItems().clear();
        sum = 0;
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