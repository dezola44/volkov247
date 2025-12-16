package ru.zelmex.prac1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
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
        Tab tab4 = new Tab("Задание 4", createTask4());
        Tab tab5 = new Tab("Доп. задание", createAdditionalTask());

        // не закрываемые
        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);
        tab5.setClosable(false);

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5);

        Scene scene = new Scene(tabPane, 600, 500);
        primaryStage.setTitle("Практическая работа №1 - Вариант 7");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // задание 1
    private VBox createTask1() {
        Label titleLabel = new Label("Задание 1: Окружность и круг");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        TextField radiusField = new TextField();
        radiusField.setPromptText("Введите радиус R");
        radiusField.setPrefWidth(200);

        Button calculateButton = new Button("Рассчитать");
        calculateButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");

        Label lengthLabel = new Label("Длина окружности L: ");
        Label areaLabel = new Label("Площадь круга S: ");
        lengthLabel.setStyle("-fx-font-size: 14px;");
        areaLabel.setStyle("-fx-font-size: 14px;");

        calculateButton.setOnAction(event -> {
            try {
                double radius = Double.parseDouble(radiusField.getText());
                final double PI = 3.14;

                double length = 2 * PI * radius;
                double area = PI * Math.pow(radius, 2);

                lengthLabel.setText(String.format("Длина окружности L: %.2f", length));
                areaLabel.setText(String.format("Площадь круга S: %.2f", area));

                // уведомление об успехе
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Результат");
                alert.setHeaderText(null);
                alert.setContentText("Расчет выполнен успешно!");
                alert.show();

            } catch (NumberFormatException e) {
                lengthLabel.setText("Длина окружности L: Ошибка!");
                areaLabel.setText("Площадь круга S: Ошибка!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Пожалуйста, введите корректное число!");
                alert.show();
            }
        });

        VBox vbox = new VBox(20, titleLabel,
                new HBox(10, new Label("Радиус R:"), radiusField),
                calculateButton, lengthLabel, areaLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // задание 2
    private VBox createTask2() {
        Label titleLabel = new Label("Задание 2: Расстояние между точками");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // первая точка
        TextField x1Field = new TextField();
        TextField y1Field = new TextField();
        x1Field.setPromptText("x1");
        y1Field.setPromptText("y1");

        // вторая точка
        TextField x2Field = new TextField();
        TextField y2Field = new TextField();
        x2Field.setPromptText("x2");
        y2Field.setPromptText("y2");

        Button calculateButton = new Button("Найти расстояние");
        calculateButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("Расстояние: ");
        resultLabel.setStyle("-fx-font-size: 14px;");

        calculateButton.setOnAction(event -> {
            try {
                double x1 = Double.parseDouble(x1Field.getText());
                double y1 = Double.parseDouble(y1Field.getText());
                double x2 = Double.parseDouble(x2Field.getText());
                double y2 = Double.parseDouble(y2Field.getText());

                double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

                resultLabel.setText(String.format("Расстояние: %.2f", distance));

            } catch (NumberFormatException e) {
                resultLabel.setText("Расстояние: Ошибка ввода!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите корректные числа для всех координат!");
                alert.show();
            }
        });

        VBox vbox = new VBox(20, titleLabel,
                new HBox(10, new Label("Точка 1:"), x1Field, new Label("y1:"), y1Field),
                new HBox(10, new Label("Точка 2:"), x2Field, new Label("y2:"), y2Field),
                calculateButton, resultLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // задание 3
    private VBox createTask3() {
        Label titleLabel = new Label("Задание 3: Стоимость конфет");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        TextField weightField = new TextField();
        TextField priceField = new TextField();
        TextField yWeightField = new TextField();

        weightField.setPromptText("X кг");
        priceField.setPromptText("A рублей");
        yWeightField.setPromptText("Y кг");

        Button calculateButton = new Button("Рассчитать стоимость");
        calculateButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");

        Label pricePerKgLabel = new Label("Цена за 1 кг: ");
        Label priceForYLabel = new Label("Стоимость Y кг: ");

        calculateButton.setOnAction(event -> {
            try {
                double X = Double.parseDouble(weightField.getText());
                double A = Double.parseDouble(priceField.getText());
                double Y = Double.parseDouble(yWeightField.getText());

                double pricePerKg = A / X;
                double priceForY = pricePerKg * Y;

                pricePerKgLabel.setText(String.format("Цена за 1 кг: %.2f рублей", pricePerKg));
                priceForYLabel.setText(String.format("Стоимость Y кг: %.2f рублей", priceForY));

            } catch (NumberFormatException e) {
                pricePerKgLabel.setText("Цена за 1 кг: Ошибка!");
                priceForYLabel.setText("Стоимость Y кг: Ошибка!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите корректные числа!");
                alert.show();
            }
        });

        VBox vbox = new VBox(20, titleLabel,
                new HBox(10, new Label("X кг ="), weightField),
                new HBox(10, new Label("A рублей ="), priceField),
                new HBox(10, new Label("Y кг ="), yWeightField),
                calculateButton, pricePerKgLabel, priceForYLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // задание 4
    private VBox createTask4() {
        Label titleLabel = new Label("Задание 4: Вычисление выражения");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label formulaLabel = new Label("a = 1 + ln|tg(x/cos(x))| + b², где b = 10^x + lg(x)");
        formulaLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");

        TextField xField = new TextField();
        xField.setPromptText("Введите x (x > 0)");
        xField.setPrefWidth(200);

        Button calculateButton = new Button("Вычислить");
        calculateButton.setStyle("-fx-background-color: #9b59b6; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("Результат a: ");
        resultLabel.setStyle("-fx-font-size: 14px;");

        calculateButton.setOnAction(event -> {
            try {
                double x = Double.parseDouble(xField.getText());

                if (x <= 0) {
                    throw new IllegalArgumentException("x должен быть больше 0");
                }


                double b = Math.pow(10, x) + Math.log10(x);


                double inner = x / Math.cos(x);

                if (Math.cos(inner) == 0) {
                    throw new IllegalArgumentException("Недопустимое значение x для тангенса");
                }

                double a = 1 + Math.log(Math.abs(Math.tan(inner))) + Math.pow(b, 2);

                resultLabel.setText(String.format("Результат a: %.4f", a));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успех");
                alert.setHeaderText(null);
                alert.setContentText("Вычисление выполнено успешно!");
                alert.show();

            } catch (NumberFormatException e) {
                resultLabel.setText("Результат a: Ошибка ввода числа!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите корректное число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("Результат a: " + e.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(20, titleLabel, formulaLabel,
                new HBox(10, new Label("x ="), xField),
                calculateButton, resultLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // доп задание
    private VBox createAdditionalTask() {
        Label titleLabel = new Label("Дополнительное задание: Система уравнений");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label formulaLabel = new Label("A₁x + B₁y = C₁\nA₂x + B₂y = C₂");
        formulaLabel.setStyle("-fx-font-size: 14px;");

        // первое
        TextField a1Field = new TextField();
        TextField b1Field = new TextField();
        TextField c1Field = new TextField();

        // второе
        TextField a2Field = new TextField();
        TextField b2Field = new TextField();
        TextField c2Field = new TextField();

        a1Field.setPromptText("A₁");
        b1Field.setPromptText("B₁");
        c1Field.setPromptText("C₁");
        a2Field.setPromptText("A₂");
        b2Field.setPromptText("B₂");
        c2Field.setPromptText("C₂");

        Button solveButton = new Button("Решить систему");
        solveButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");

        Label xLabel = new Label("x = ");
        Label yLabel = new Label("y = ");

        solveButton.setOnAction(event -> {
            try {
                double A1 = Double.parseDouble(a1Field.getText());
                double B1 = Double.parseDouble(b1Field.getText());
                double C1 = Double.parseDouble(c1Field.getText());
                double A2 = Double.parseDouble(a2Field.getText());
                double B2 = Double.parseDouble(b2Field.getText());
                double C2 = Double.parseDouble(c2Field.getText());

                // определитель D
                double D = A1 * B2 - A2 * B1;

                if (D == 0) {
                    throw new ArithmeticException("Система не имеет единственного решения (D = 0)");
                }

                // x и y по формулам 
                double x = (C1 * B2 - C2 * B1) / D;
                double y = (A1 * C2 - A2 * C1) / D;

                xLabel.setText(String.format("x = %.4f", x));
                yLabel.setText(String.format("y = %.4f", y));

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Решение системы");
                alert.setHeaderText(null);
                alert.setContentText("Система решена успешно!");
                alert.show();

            } catch (NumberFormatException e) {
                xLabel.setText("x = Ошибка!");
                yLabel.setText("y = Ошибка!");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Введите корректные коэффициенты!");
                alert.show();
            } catch (ArithmeticException e) {
                xLabel.setText("x = " + e.getMessage());
                yLabel.setText("y = " + e.getMessage());

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.show();
            }
        });

        VBox vbox = new VBox(20, titleLabel, formulaLabel,
                new HBox(10, new Label("A₁:"), a1Field, new Label("B₁:"), b1Field, new Label("C₁:"), c1Field),
                new HBox(10, new Label("A₂:"), a2Field, new Label("B₂:"), b2Field, new Label("C₂:"), c2Field),
                solveButton, xLabel, yLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}