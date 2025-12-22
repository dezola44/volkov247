package ru.zelmex.zad3;

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

    private int A = 0;
    private int B = 0;
    private int step = 0;

    @FXML
    void OkButtonOnAction(ActionEvent event) {
        if (step < 2) {
            answerLabel.setText("Введите A и B");
            showAlert("Ошибка", "Сначала введите числа A и B (A < B)");
            return;
        }

        if (A >= B) {
            answerLabel.setText("A должно быть < B");
            showAlert("Ошибка", "Число A должно быть меньше B");
            return;
        }

        dataListView.getItems().clear();
        dataListView.getItems().add("A = " + A);
        dataListView.getItems().add("B = " + B);
        dataListView.getItems().add("----------------");

        long product = 1;
        String expression = "";

        for (int i = A; i <= B; i++) {
            product *= i;
            if (i == A) {
                expression = String.valueOf(i);
            } else {
                expression += " * " + i;
            }
            dataListView.getItems().add("Число " + i + ": произведение = " + product);
        }

        answerLabel.setText("Произведение = " + product);

        showAlert("Результат", "Произведение чисел от " + A + " до " + B + " = " + product +
                "\nВычислено: " + expression);
    }

    @FXML
    void addButtonOnAction(ActionEvent event) {
        String text = numberTextField.getText().trim();
        if (text.isEmpty()) return;

        try {
            int num = Integer.parseInt(text);

            if (step == 0) {
                A = num;
                dataListView.getItems().add("A = " + A);
                step = 1;
                answerLabel.setText("Введите B (B > " + A + ")");
            } else if (step == 1) {
                if (num <= A) {
                    showAlert("Ошибка", "B должно быть больше A (" + A + ")");
                    return;
                }
                B = num;
                dataListView.getItems().add("B = " + B);
                step = 2;
                answerLabel.setText("A = " + A + ", B = " + B);
                addButton.setDisable(true);
                numberTextField.setDisable(true);
            }

        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите целое число");
        }

        numberTextField.clear();
        numberTextField.requestFocus();
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        dataListView.getItems().clear();
        A = 0;
        B = 0;
        step = 0;
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