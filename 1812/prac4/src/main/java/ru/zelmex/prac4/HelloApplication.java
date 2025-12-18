package ru.zelmex.prac4;

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


        Tab tab1 = new Tab("задание 1", createTask1());
        Tab tab2 = new Tab("задание 2", createTask2());
        Tab tab3 = new Tab("задание 3", createTask3());
        Tab tab4 = new Tab("задание 4", createTask4());
        Tab tab5 = new Tab("задание 5", createTask5());
        Tab tab6 = new Tab("задание 6", createTask6());


        for (Tab tab : new Tab[]{tab1, tab2, tab3, tab4, tab5, tab6}) {
            tab.setClosable(false);
        }

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6);

        Scene scene = new Scene(tabPane, 300, 400);
        primaryStage.setTitle("практическая 4 вариант 7");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //задание 1
    private VBox createTask1() {
        Label titleLabel = new Label("задание 1");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("единицы длины пронумерованы:\n" +
                "1 — дм, 2 — км, 3 — м, 4 — мм, 5 — см.\n" +
                "дан номер единицы и длина отрезка. найти длину отрезка в метрах.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);


        TextField unitField = new TextField();
        TextField lengthField = new TextField();

        unitField.setPromptText("номер единицы (1-5)");
        lengthField.setPromptText("длина отрезка");

        Button calculateButton = new Button("конвертировать в метры");
        calculateButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("длина в метрах: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        calculateButton.setOnAction(event -> {
            try {
                int unit = Integer.parseInt(unitField.getText());
                double length = Double.parseDouble(lengthField.getText());

                if (unit < 1 || unit > 5) {
                    throw new IllegalArgumentException("номер единицы должен быть от 1 до 5");
                }

                double lengthInMeters;
                String unitName = "";


                switch (unit) {
                    case 1:
                        lengthInMeters = length / 10.0;
                        unitName = "дм";
                        break;
                    case 2:
                        lengthInMeters = length * 1000.0;
                        unitName = "км";
                        break;
                    case 3:
                        lengthInMeters = length;
                        unitName = "метр";
                        break;
                    case 4:
                        lengthInMeters = length / 1000.0;
                        unitName = "мм";
                        break;
                    case 5:
                        lengthInMeters = length / 100.0;
                        unitName = "см";
                        break;
                    default:
                        throw new IllegalArgumentException("неизвестная единица длины");
                }

                resultLabel.setText(String.format("длина в метрах: %.4f м", lengthInMeters));
                resultLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");

                explanationLabel.setText(String.format("%.2f %s = %.4f метров", length, unitName, lengthInMeters));

            } catch (NumberFormatException e) {
                resultLabel.setText("длина в метрах: ОШИБКА ВВОДА!!!!!!");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ОШИБКА");
                alert.setHeaderText(null);
                alert.setContentText("введите корректные числа!!!!!!");
                alert.show();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("длина в метрах: " + e.getMessage());
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ОШИБКА");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("номер единицы:"), unitField),
                new HBox(10, new Label("длина отрезка:"), lengthField),
                calculateButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // задание 2
    private VBox createTask2() {
        Label titleLabel = new Label("задание 2");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("дано целое число в диапазоне 20–69, определяющее возраст.\n" +
                "вывести строку-описание возраста с правильным согласованием\n" +
                "со словом «год» (например: 20 — «двадцать лет»).");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField ageField = new TextField();
        ageField.setPromptText("возраст (20-69)");

        Button convertButton = new Button("преобразовать в текст");
        convertButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("описание возраста: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        convertButton.setOnAction(event -> {
            try {
                int age = Integer.parseInt(ageField.getText());

                if (age < 20 || age > 69) {
                    throw new IllegalArgumentException("возраст должен быть в диапазоне 20-69");
                }


                int tens = age / 10;
                int units = age % 10;

                String tensStr = "";
                String unitsStr = "";
                String wordEnding = "";


                switch (tens) {
                    case 2: tensStr = "двадцать"; break;
                    case 3: tensStr = "тридцать"; break;
                    case 4: tensStr = "сорок"; break;
                    case 5: tensStr = "пятьдесят"; break;
                    case 6: tensStr = "шестьдесят"; break;
                }


                switch (units) {
                    case 0: unitsStr = ""; break;
                    case 1: unitsStr = "один"; break;
                    case 2: unitsStr = "два"; break;
                    case 3: unitsStr = "три"; break;
                    case 4: unitsStr = "четыре"; break;
                    case 5: unitsStr = "пять"; break;
                    case 6: unitsStr = "шесть"; break;
                    case 7: unitsStr = "семь"; break;
                    case 8: unitsStr = "восемь"; break;
                    case 9: unitsStr = "девять"; break;
                }

                if (units == 1) {
                    wordEnding = "год";
                } else if (units >= 2 && units <= 4) {
                    wordEnding = "года";
                } else {
                    wordEnding = "лет";
                }

                String result;
                if (units == 0) {
                    result = tensStr + " " + wordEnding;
                } else {
                    result = tensStr + " " + unitsStr + " " + wordEnding;
                }

                resultLabel.setText("описание возраста: " + result);
                resultLabel.setStyle("-fx-text-fill: #9b59b6; -fx-font-weight: bold;");

                explanationLabel.setText("число: " + age + " = " + tens + "0 + " + units +
                        "\nДесятки: " + tensStr + ", Единицы: " + unitsStr +
                        "\nОкончание: " + wordEnding);

            } catch (NumberFormatException e) {
                resultLabel.setText("описание возраста: ОШИБКА ВВОДА!");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ОШИЬКА");
                alert.setHeaderText(null);
                alert.setContentText("введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("описание возраста: " + e.getMessage());
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("возраст:"), ageField),
                convertButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    //задание 3
    private VBox createTask3() {
        Label titleLabel = new Label("задание 3");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("элементы окружности:\n" +
                "1 — радиус R, 2 — диаметр D = 2·R\n" +
                "3 — длина L = 2·π·R, 4 — площадь S = π·R²\n" +
                "дан номер элемента и его значение.\n" +
                "вывести значения остальных элементов (π = 3.14).");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField elementField = new TextField();
        TextField valueField = new TextField();

        elementField.setPromptText("номер элемента (1-4)");
        valueField.setPromptText("значение элемента");

        Button calculateButton = new Button("вычислить остальные элементы");
        calculateButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");

        Label radiusLabel = new Label("радиус R: ");
        Label diameterLabel = new Label("диаметр D: ");
        Label lengthLabel = new Label("длина L: ");
        Label areaLabel = new Label("площадь S: ");

        calculateButton.setOnAction(event -> {
            try {
                int element = Integer.parseInt(elementField.getText());
                double value = Double.parseDouble(valueField.getText());

                if (element < 1 || element > 4) {
                    throw new IllegalArgumentException("номер элемента должен быть от 1 до 4");
                }
                if (value <= 0) {
                    throw new IllegalArgumentException("значение должно быть положительным");
                }

                final double PI = 3.14;
                double R = 0, D = 0, L = 0, S = 0;

                switch (element) {
                    case 1:
                        R = value;
                        D = 2 * R;
                        L = 2 * PI * R;
                        S = PI * R * R;
                        break;
                    case 2:
                        D = value;
                        R = D / 2;
                        L = 2 * PI * R;
                        S = PI * R * R;
                        break;
                    case 3:
                        L = value;
                        R = L / (2 * PI);
                        D = 2 * R;
                        S = PI * R * R;
                        break;
                    case 4:
                        S = value;
                        R = Math.sqrt(S / PI);
                        D = 2 * R;
                        L = 2 * PI * R;
                        break;
                }

                radiusLabel.setText(String.format("радиус R: %.4f", R));
                diameterLabel.setText(String.format("диаметр D: %.4f", D));
                lengthLabel.setText(String.format("длина L: %.4f", L));
                areaLabel.setText(String.format("площадь S: %.4f", S));


                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("результат");
                alert.setHeaderText(null);
                alert.setContentText("элементы окружности вычислены успешно!");
                alert.show();

            } catch (NumberFormatException e) {
                radiusLabel.setText("радиус R: Ошибка ввода!");
                diameterLabel.setText("диаметр D: Ошибка ввода!");
                lengthLabel.setText("длина L: Ошибка ввода!");
                areaLabel.setText("площадь S: Ошибка ввода!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText("введите корректные числа!");
                alert.show();
            } catch (IllegalArgumentException e) {
                radiusLabel.setText("радиус R: " + e.getMessage());
                diameterLabel.setText("диаметр D: " + e.getMessage());
                lengthLabel.setText("длина L: " + e.getMessage());
                areaLabel.setText("площадь S: " + e.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("номер элемента:"), elementField),
                new HBox(10, new Label("значение:"), valueField),
                calculateButton, radiusLabel, diameterLabel, lengthLabel, areaLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // задание 4
    private VBox createTask4() {
        Label titleLabel = new Label("задание 4");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("дано целое число N (> 0). найти двойной факториал N:\n" +
                "N!! = N·(N−2)·(N−4)·...\n" +
                "(последний сомножитель равен 2, если N — четное, и 1, если N — нечетное).");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField nField = new TextField();
        nField.setPromptText("введите N (> 0)");

        Button calculateButton = new Button("вычислить двойной факториал");
        calculateButton.setStyle("-fx-background-color: #9b59b6; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("результат N!! = ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");
        explanationLabel.setWrapText(true);

        calculateButton.setOnAction(event -> {
            try {
                int N = Integer.parseInt(nField.getText());

                if (N <= 0) {
                    throw new IllegalArgumentException("N должно быть > 0");
                }


                double result = 1.0;
                int current = N;
                StringBuilder steps = new StringBuilder();

                while (current > 0) {
                    result *= current;
                    steps.append(current);
                    if (current > 2) {
                        steps.append(" × ");
                    }
                    current -= 2;
                }

                resultLabel.setText(String.format("результат N!! = %.0f", result));
                resultLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");

                explanationLabel.setText("вычисление: " + steps.toString() +
                        "\nN = " + N + ", результат = " + result);

            } catch (NumberFormatException e) {
                resultLabel.setText("результат N!! = ОШИБКА ВВОДА!");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ОШИБККА");
                alert.setHeaderText(null);
                alert.setContentText("введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("результат N!! = " + e.getMessage());
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ОШИБКА");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("N ="), nField),
                calculateButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    //задание 5
    private VBox createTask5() {
        Label titleLabel = new Label("задание 5");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("начальный вклад: 1000 руб.\n" +
                "каждый месяц вклад увеличивается на P%.\n" +
                "определить, через сколько месяцев размер вклада\n" +
                "превысит 1100 руб. (0 < P < 25).");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField pField = new TextField();
        pField.setPromptText("процент P (0 < P < 25)");

        Button calculateButton = new Button("рассчитать");
        calculateButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");

        Label monthsLabel = new Label("количество месяцев K: ");
        Label amountLabel = new Label("итоговый размер вклада S: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");
        explanationLabel.setWrapText(true);

        calculateButton.setOnAction(event -> {
            try {
                double P = Double.parseDouble(pField.getText());

                if (P <= 0 || P >= 25) {
                    throw new IllegalArgumentException("P должен быть в диапазоне 0 < P < 25");
                }

                double initialAmount = 1000.0;
                double targetAmount = 1100.0;
                double currentAmount = initialAmount;
                int months = 0;

                StringBuilder steps = new StringBuilder("месяц 0: " + initialAmount + " руб.\n");


                while (currentAmount <= targetAmount) {
                    months++;
                    currentAmount = currentAmount * (1 + P / 100.0);
                    steps.append("месяц ").append(months).append(": ").append(String.format("%.2f", currentAmount)).append(" руб.\n");


                    if (months > 1000) {
                        throw new RuntimeException("слишком много итераций, проверьте входные данные");
                    }
                }

                monthsLabel.setText("количество месяцев K: " + months);
                amountLabel.setText(String.format("итоговый размер вклада S: %.2f руб.", currentAmount));

                explanationLabel.setText(steps.toString());

            } catch (NumberFormatException e) {
                monthsLabel.setText("количество месяцев K: Ошибка ввода!");
                amountLabel.setText("ктоговый размер вклада S: Ошибка ввода!");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите число для процента!");
                alert.show();
            } catch (IllegalArgumentException e) {
                monthsLabel.setText("Количество месяцев K: " + e.getMessage());
                amountLabel.setText("Итоговый размер вклада S: " + e.getMessage());
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            } catch (RuntimeException e) {
                monthsLabel.setText("Количество месяцев K: Ошибка вычисления");
                amountLabel.setText("Итоговый размер вклада S: Ошибка вычисления");
                explanationLabel.setText(e.getMessage());
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("Процент P:"), pField),
                calculateButton, monthsLabel, amountLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    //задание 6
    private VBox createTask6() {
        Label titleLabel = new Label("задание 6");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("Дано целое число N (> 0).\n" +
                "С помощью операций деления нацело и взятия остатка\n" +
                "определить, имеется ли в записи числа N цифра «5».");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField nField = new TextField();
        nField.setPromptText("Введите N (> 0)");

        Button checkButton = new Button("Проверить наличие цифры 5");
        checkButton.setStyle("-fx-background-color: #1abc9c; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("Результат: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");
        explanationLabel.setWrapText(true);

        checkButton.setOnAction(event -> {
            try {
                int N = Integer.parseInt(nField.getText());

                if (N <= 0) {
                    throw new IllegalArgumentException("N должно быть > 0");
                }

                int temp = N;
                boolean hasFive = false;
                StringBuilder digits = new StringBuilder();
                StringBuilder steps = new StringBuilder();


                while (temp > 0) {
                    int digit = temp % 10;
                    digits.insert(0, digit + " ");

                    steps.append("Остаток от деления ").append(temp).append(" на 10 = ").append(digit);
                    if (digit == 5) {
                        steps.append(" ← найдена цифра 5!\n");
                        hasFive = true;
                    } else {
                        steps.append("\n");
                    }

                    temp = temp / 10;
                }

                if (hasFive) {
                    resultLabel.setText("Результат: true (цифра 5 найдена)");
                    resultLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
                } else {
                    resultLabel.setText("Результат: false (цифра 5 не найдена)");
                    resultLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                }

                explanationLabel.setText("Число: " + N + "\n" +
                        "Цифры (справа налево): " + digits.toString() + "\n" +
                        steps.toString());

            } catch (NumberFormatException e) {
                resultLabel.setText("Результат: Ошибка ввода!");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("Результат: " + e.getMessage());
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("N ="), nField),
                checkButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}