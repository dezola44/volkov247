package ru.zelmex.prac2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        TabPane tabPane = new TabPane();

        // вкладки
        Tab tab1 = new Tab("Задание 1", createTask1());
        Tab tab2 = new Tab("Задание 2", createTask2());
        Tab tab3 = new Tab("Задание 3", createTask3());
        Tab tab4 = new Tab("Доп. задание 1", createAdditionalTask1());
        Tab tab5 = new Tab("Доп. задание 2", createAdditionalTask2());

        // не закрываемые
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);
        tab5.setClosable(false);

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5);

        Scene scene = new Scene(tabPane, 500, 400);
        primaryStage.setTitle("Практическая работа №2 - Вариант 7");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // задание 1
    private VBox createTask1() {
        Label titleLabel = new Label("Задание 1: Двузначное число");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("Дано двузначное число. Найти сумму и произведение его цифр.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField numberField = new TextField();
        numberField.setPromptText("Введите двузначное число (10-99)");
        numberField.setPrefWidth(200);

        Button calculateButton = new Button("Вычислить");
        calculateButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");

        Label sumLabel = new Label("Сумма цифр: ");
        Label productLabel = new Label("Произведение цифр: ");

        calculateButton.setOnAction(event -> {
            try {
                int number = Integer.parseInt(numberField.getText());

                if (number < 10 || number > 99) {
                    throw new IllegalArgumentException("Число должно быть двузначным (10-99)");
                }

                // разделение
                int tens = number / 10;
                int units = number % 10;

                int sum = tens + units;
                int product = tens * units;

                sumLabel.setText("Сумма цифр: " + sum + " (" + tens + " + " + units + ")");
                productLabel.setText("Произведение цифр: " + product + " (" + tens + " × " + units + ")");

            } catch (NumberFormatException e) {
                sumLabel.setText("Сумма цифр: Ошибка ввода!");
                productLabel.setText("Произведение цифр: Ошибка ввода!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                sumLabel.setText("Сумма цифр: " + e.getMessage());
                productLabel.setText("Произведение цифр: " + e.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("Число:"), numberField),
                calculateButton, sumLabel, productLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // задание 2
    private VBox createTask2() {
        Label titleLabel = new Label("Задание 2: Время с начала суток");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("С начала суток прошло N секунд. Найти количество полных часов, прошедших с начала суток.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField secondsField = new TextField();
        secondsField.setPromptText("Введите количество секунд");
        secondsField.setPrefWidth(200);

        Button calculateButton = new Button("Вычислить");
        calculateButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        Label hoursLabel = new Label("Полных часов: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        calculateButton.setOnAction(event -> {
            try {
                int N = Integer.parseInt(secondsField.getText());

                if (N < 0) {
                    throw new IllegalArgumentException("Количество секунд не может быть отрицательным");
                }

                // полные часы
                int hours = N / 3600;
                int remainingSeconds = N % 3600;

                hoursLabel.setText("Полных часов: " + hours);
                explanationLabel.setText("Расчет: " + N + " сек ÷ 3600 = " + hours + " часов (остаток " + remainingSeconds + " сек)");

            } catch (NumberFormatException e) {
                hoursLabel.setText("Полных часов: Ошибка ввода!");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                hoursLabel.setText("Полных часов: " + e.getMessage());
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("N секунд:"), secondsField),
                calculateButton, hoursLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // 3
    private VBox createTask3() {
        Label titleLabel = new Label("Задание 3: День недели");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("Дни недели: 0-воскресенье, 1-понедельник, 2-вторник, 3-среда, 4-четверг, 5-пятница, 6-суббота.\n" +
                "1 января было четвергом (день недели = 4).\n" +
                "Определить номер дня недели для K-го дня года (K = 1-365).");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField dayField = new TextField();
        dayField.setPromptText("Введите K (1-365)");
        dayField.setPrefWidth(200);

        Button calculateButton = new Button("Определить день недели");
        calculateButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("День недели: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        // названия
        String[] weekDays = {"воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"};

        calculateButton.setOnAction(event -> {
            try {
                int K = Integer.parseInt(dayField.getText());

                if (K < 1 || K > 365) {
                    throw new IllegalArgumentException("K должно быть в диапазоне 1-365");
                }


                int januaryFirst = 4;


                int dayOfWeek = (januaryFirst + (K - 1)) % 7;

                resultLabel.setText("День недели: " + dayOfWeek + " (" + weekDays[dayOfWeek] + ")");
                explanationLabel.setText("Расчет: (4 + (" + K + " - 1)) % 7 = " + dayOfWeek);

            } catch (NumberFormatException e) {
                resultLabel.setText("День недели: Ошибка ввода!");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("День недели: " + e.getMessage());
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("K-й день:"), dayField),
                calculateButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // доп 1
    private VBox createAdditionalTask1() {
        Label titleLabel = new Label("Дополнительное задание 1: Квадраты на прямоугольнике");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("Даны целые положительные числа A, B, C.\n" +
                "На прямоугольнике размера A × B размещено максимально возможное количество\n" +
                "квадратов со стороной C (без наложений).\n" +
                "Найти количество квадратов и площадь незанятой части.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        // поля для ввода
        TextField aField = new TextField();
        TextField bField = new TextField();
        TextField cField = new TextField();

        aField.setPromptText("A (длина)");
        bField.setPromptText("B (ширина)");
        cField.setPromptText("C (сторона квадрата)");

        Button calculateButton = new Button("Рассчитать");
        calculateButton.setStyle("-fx-background-color: #9b59b6; -fx-text-fill: white; -fx-font-weight: bold;");

        Label squaresLabel = new Label("Количество квадратов: ");
        Label areaLabel = new Label("Незанятая площадь: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        calculateButton.setOnAction(event -> {
            try {
                int A = Integer.parseInt(aField.getText());
                int B = Integer.parseInt(bField.getText());
                int C = Integer.parseInt(cField.getText());

                if (A <= 0 || B <= 0 || C <= 0) {
                    throw new IllegalArgumentException("Все числа должны быть положительными");
                }

                if (C > A || C > B) {
                    throw new IllegalArgumentException("Сторона квадрата C должна быть ≤ A и ≤ B");
                }


                int squaresHorizontal = A / C;
                int squaresVertical = B / C;
                int totalSquares = squaresHorizontal * squaresVertical;


                int occupiedArea = totalSquares * C * C;
                int totalArea = A * B;
                int freeArea = totalArea - occupiedArea;

                squaresLabel.setText("Количество квадратов: " + totalSquares +
                        " (" + squaresHorizontal + "×" + squaresVertical + ")");
                areaLabel.setText("Незанятая площадь: " + freeArea +
                        " (вся площадь: " + totalArea + ", занято: " + occupiedArea + ")");
                explanationLabel.setText("Расчет: (" + A + "/" + C + ") × (" + B + "/" + C + ") = " +
                        squaresHorizontal + " × " + squaresVertical + " = " + totalSquares);

            } catch (NumberFormatException e) {
                squaresLabel.setText("Количество квадратов: Ошибка ввода!");
                areaLabel.setText("Незанятая площадь: Ошибка ввода!");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите целые числа!");
                alert.show();
            } catch (IllegalArgumentException e) {
                squaresLabel.setText("Количество квадратов: " + e.getMessage());
                areaLabel.setText("Незанятая площадь: " + e.getMessage());
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("A:"), aField),
                new HBox(10, new Label("B:"), bField),
                new HBox(10, new Label("C:"), cField),
                calculateButton, squaresLabel, areaLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // доп 2
    private VBox createAdditionalTask2() {
        Label titleLabel = new Label("Дополнительное задание 2: Определение столетия");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("Дан номер некоторого года (целое положительное число).\n" +
                "Определить соответствующий ему номер столетия, учитывая,\n" +
                "что, к примеру, началом 20 столетия был 1901 год.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField yearField = new TextField();
        yearField.setPromptText("Введите год");
        yearField.setPrefWidth(200);

        Button calculateButton = new Button("Определить столетие");
        calculateButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");

        Label centuryLabel = new Label("Столетие: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        calculateButton.setOnAction(event -> {
            try {
                int year = Integer.parseInt(yearField.getText());

                if (year <= 0) {
                    throw new IllegalArgumentException("Год должен быть положительным числом");
                }


                int century = (year - 1) / 100 + 1;

                centuryLabel.setText("Столетие: " + century + "-е");


                String romanCentury = convertToRoman(century);
                explanationLabel.setText("Год " + year + " относится к " + century + "-му (" + romanCentury + ") столетию.\n" +
                        "Расчет: (" + year + " - 1) / 100 + 1 = " + century);

            } catch (NumberFormatException e) {
                centuryLabel.setText("Столетие: Ошибка ввода!");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                centuryLabel.setText("Столетие: " + e.getMessage());
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("Год:"), yearField),
                calculateButton, centuryLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    
    private String convertToRoman(int number) {
        if (number < 1 || number > 3999) {
            return String.valueOf(number);
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[number / 1000] +
                hundreds[(number % 1000) / 100] +
                tens[(number % 100) / 10] +
                units[number % 10];
    }

    public static void main(String[] args) {
        launch();
    }
}