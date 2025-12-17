package ru.zelmex.prac3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;

        TabPane tabPane = new TabPane();

        //вкладки
        Tab tab1 = new Tab("задание 1", createTask1());
        Tab tab2 = new Tab("задание 2", createTask2());
        Tab tab3 = new Tab("задание 3", createTask3());
        Tab tab4 = new Tab("задание 4", createTask4());
        Tab tab5 = new Tab("задание 5", createTask5());
        Tab tab6 = new Tab("доп 1", createAdditionalTask1());
        Tab tab7 = new Tab("доп 2", createAdditionalTask2());

        tab1.setClosable(false);
        tab2.setClosable(false);
        tab3.setClosable(false);
        tab4.setClosable(false);
        tab5.setClosable(false);
        tab6.setClosable(false);
        tab7.setClosable(false);

        tabPane.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6, tab7);

        Scene scene = new Scene(tabPane, 300, 400);
        primaryStage.setTitle("практическая 3, вариант 7");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // задание 1
    private VBox createTask1() {
        Label titleLabel = new Label("заадание 1:");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("является ли сумма цифр двухзначного числа нечетной");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField numberField = new TextField();
        numberField.setPromptText("введите двухначное число 10-99");
        numberField.setPrefWidth(200);

        Button checkButton = new Button("проверить");
        checkButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("результат: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        checkButton.setOnAction(event -> {
            try {
                int number = Integer.parseInt(numberField.getText());

                if (number < 10 || number > 99) {
                    throw new IllegalArgumentException("число должно быть двузначным!!!!!!!!!!!!!!!!!!!!");
                }


                int tens = number / 10;
                int units = number % 10;
                int sum = tens + units;

                boolean isOdd = (sum % 2 != 0);

                if (isOdd) {
                    resultLabel.setText("результат: сумма цифр НЕЧЕТНАЯ (" + tens + " + " + units + " = " + sum + ")");
                    resultLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                } else {
                    resultLabel.setText("результат: сумма цифр ЧЕТНАЯ (" + tens + " + " + units + " = " + sum + ")");
                    resultLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
                }

                explanationLabel.setText("расчет: " + tens + " (десятки) + " + units + " (единицы) = " + sum + "\n" +
                        sum + " % 2 = " + (sum % 2) + " → " + (isOdd ? "нечетное" : "четное"));

            } catch (NumberFormatException e) {
                resultLabel.setText("результат: ОШИБКА ВВОДА(((((((");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText("введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("результат: " + e.getMessage());
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
                new HBox(10, new Label("число:"), numberField),
                checkButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    //задание 2
    private VBox createTask2() {
        Label titleLabel = new Label("задание 2");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("даны два числа. вывести порядковый номер меньшего из них.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField num1Field = new TextField();
        TextField num2Field = new TextField();
        num1Field.setPromptText("первое число");
        num2Field.setPromptText("второе число");

        Button compareButton = new Button("сравнить");
        compareButton.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("результат: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        compareButton.setOnAction(event -> {
            try {
                double num1 = Double.parseDouble(num1Field.getText());
                double num2 = Double.parseDouble(num2Field.getText());

                if (num1 < num2) {
                    resultLabel.setText("результат: меньшее число ПЕРВОЕ (" + num1 + " < " + num2 + ")");
                    resultLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                } else if (num2 < num1) {
                    resultLabel.setText("результат: меньшее число ВТОРОЕ (" + num2 + " < " + num1 + ")");
                    resultLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
                } else {
                    resultLabel.setText("результат: Числа РАВНЫ (" + num1 + " = " + num2 + ")");
                    resultLabel.setStyle("-fx-text-fill: #3498db; -fx-font-weight: bold;");
                }

                explanationLabel.setText("сравнение: " + num1 + " и " + num2);

            } catch (NumberFormatException e) {
                resultLabel.setText("результат: ошибка блин((((");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText("введите числа!");
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("первое:"), num1Field),
                new HBox(10, new Label("второе:"), num2Field),
                compareButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    //задание 3
    private VBox createTask3() {
        Label titleLabel = new Label("задание 3");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("даны три числа. найти среднее из них\n(число, расположенное между наименьшим и наибольшим).");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField num1Field = new TextField();
        TextField num2Field = new TextField();
        TextField num3Field = new TextField();

        num1Field.setPromptText("первое число");
        num2Field.setPromptText("второе число");
        num3Field.setPromptText("третье число");

        Button findButton = new Button("найти среднее");
        findButton.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("результат");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        findButton.setOnAction(event -> {
            try {
                double a = Double.parseDouble(num1Field.getText());
                double b = Double.parseDouble(num2Field.getText());
                double c = Double.parseDouble(num3Field.getText());


                double min = Math.min(Math.min(a, b), c);
                double max = Math.max(Math.max(a, b), c);
                double middle;

                if ((a >= b && a <= c) || (a <= b && a >= c)) {
                    middle = a;
                } else if ((b >= a && b <= c) || (b <= a && b >= c)) {
                    middle = b;
                } else {
                    middle = c;
                }

                resultLabel.setText("результат: среднее число = " + middle);
                resultLabel.setStyle("-fx-text-fill: #9b59b6; -fx-font-weight: bold;");

                explanationLabel.setText("числа: " + a + ", " + b + ", " + c + "\n" +
                        "наименьшее: " + min + ", наибольшее: " + max + "\n" +
                        "среднее: " + middle);

            } catch (NumberFormatException e) {
                resultLabel.setText("результат: ошибка ввода((");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText("введите числа!");
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("первое:"), num1Field),
                new HBox(10, new Label("второе:"), num2Field),
                new HBox(10, new Label("третье:"), num3Field),
                findButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    // задание 4
    private VBox createTask4() {
        Label titleLabel = new Label("задание 4");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("вычислить значение функции:\n" +
                "y = sin(x) + 2, при k = 3\n" +
                "y = cos(x²), при k = 20\n" +
                "y = tg(x) + sin(x), при k = 10 или k = 15\n" +
                "В остальных случаях значение y не определено.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField xField = new TextField();
        TextField kField = new TextField();

        xField.setPromptText("введите x");
        kField.setPromptText("введите k");

        Button calculateButton = new Button("вычислить y");
        calculateButton.setStyle("-fx-background-color: #9b59b6; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("результат y = ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        calculateButton.setOnAction(event -> {
            try {
                double x = Double.parseDouble(xField.getText());
                int k = Integer.parseInt(kField.getText());

                double y;
                String formula = "";

                if (k == 3) {
                    y = Math.sin(x) + 2;
                    formula = "sin(" + x + ") + 2";
                } else if (k == 20) {
                    y = Math.cos(x * x);
                    formula = "cos(" + x + "²) = cos(" + (x*x) + ")";
                } else if (k == 10 || k == 15) {
                    y = Math.tan(x) + Math.sin(x);
                    formula = "tg(" + x + ") + sin(" + x + ")";
                } else {
                    resultLabel.setText("результат y = не определено");
                    resultLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                    explanationLabel.setText("k = " + k + " не соответствует условию (допустимо: 3, 10, 15, 20)");
                    return;
                }

                resultLabel.setText(String.format("результат y = %.4f", y));
                resultLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");

                explanationLabel.setText("при k = " + k + ", x = " + x + "\n" +
                        "формула: " + formula + " = " + y);

            } catch (NumberFormatException e) {
                resultLabel.setText("результат y = ошибка ввода!");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText("введите корректные числа!");
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("x ="), xField),
                new HBox(10, new Label("k ="), kField),
                calculateButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    //задание 5
    private VBox createTask5() {
        Label titleLabel = new Label("задание 5");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("дана точка на плоскости с координатами (x, y).\n" +
                "определить, лежит ли точка внутри заштрихованной области,\n" +
                "вне заштрихованной области или на ее границе.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);


        Group areaGraphic = new Group();
        Rectangle areaBorder = new Rectangle(200, 150);
        areaBorder.setStroke(Color.BLACK);
        areaBorder.setFill(Color.TRANSPARENT);
        areaBorder.setStrokeWidth(2);


        Rectangle leftSquare = new Rectangle(0, 0, 100, 150);
        leftSquare.setFill(Color.LIGHTBLUE);
        leftSquare.setOpacity(0.3);
        leftSquare.setStroke(Color.BLUE);


        Circle rightCircle = new Circle(150, 75, 50);
        rightCircle.setFill(Color.LIGHTBLUE);
        rightCircle.setOpacity(0.3);
        rightCircle.setStroke(Color.BLUE);


        Rectangle clipRect = new Rectangle(100, 0, 100, 150);
        rightCircle.setClip(clipRect);

        areaGraphic.getChildren().addAll(leftSquare, rightCircle, areaBorder);

        TextField xField = new TextField();
        TextField yField = new TextField();
        xField.setPromptText("координата x");
        yField.setPromptText("координата y");

        Button checkButton = new Button("проверить точку");
        checkButton.setStyle("-fx-background-color: #f39c12; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("результат: ");
        Label explanationLabel = new Label("область: левый квадрат [0≤x≤100, 0≤y≤150] + правый полукруг радиусом 50");
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");
        explanationLabel.setWrapText(true);

        checkButton.setOnAction(event -> {
            try {
                double x = Double.parseDouble(xField.getText());
                double y = Double.parseDouble(yField.getText());



                boolean inRectangle = (x >= 0 && x <= 100 && y >= 0 && y <= 150);
                boolean inCircle = false;

                if (x >= 100 && x <= 200) {
                    double distanceSquared = Math.pow(x - 150, 2) + Math.pow(y - 75, 2);
                    inCircle = (distanceSquared <= 2500); // 50² = 2500
                }

                boolean onBorder = false;


                if (x == 0 || x == 100 || y == 0 || y == 150) {
                    onBorder = true;
                }


                if (x >= 100 && x <= 200) {
                    double distanceSquared = Math.pow(x - 150, 2) + Math.pow(y - 75, 2);
                    if (Math.abs(distanceSquared - 2500) < 0.0001) {
                        onBorder = true;
                    }
                }

                if (inRectangle || inCircle) {
                    if (onBorder) {
                        resultLabel.setText("результат: НА ГРАНИЦЕ области");
                        resultLabel.setStyle("-fx-text-fill: #f39c12; -fx-font-weight: bold;");
                    } else {
                        resultLabel.setText("результат: ДА (внутри области)");
                        resultLabel.setStyle("-fx-text-fill: #2ecc71; -fx-font-weight: bold;");
                    }
                } else {
                    resultLabel.setText("результат: НЕТ (вне области)");
                    resultLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-weight: bold;");
                }

            } catch (NumberFormatException e) {
                resultLabel.setText("результат: Ошибка ввода!");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText("введите числа для координат!");
                alert.show();
            }
        });

        VBox graphicBox = new VBox(10, areaGraphic);
        graphicBox.setAlignment(Pos.CENTER);
        graphicBox.setPadding(new Insets(10));

        VBox vbox = new VBox(15, titleLabel, descriptionLabel, graphicBox,
                new HBox(10, new Label("x ="), xField),
                new HBox(10, new Label("y ="), yField),
                checkButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_CENTER);

        return vbox;
    }

    //доп 1
    private VBox createAdditionalTask1() {
        Label titleLabel = new Label("доп 1");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("дано целое число. вывести его строку-описание вида:\n" +
                "«отрицательное четное число», «нулевое число»,\n" +
                "«положительное нечетное число» и т.д.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField numberField = new TextField();
        numberField.setPromptText("введите целое число");
        numberField.setPrefWidth(200);

        Button describeButton = new Button("описать число");
        describeButton.setStyle("-fx-background-color: #1abc9c; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("описание: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        describeButton.setOnAction(event -> {
            try {
                int number = Integer.parseInt(numberField.getText());

                StringBuilder description = new StringBuilder();

                if (number > 0) {
                    description.append("положительное ");
                } else if (number < 0) {
                    description.append("отрицательное ");
                } else {
                    description.append("нулевое ");
                }


                if (number != 0) {
                    if (number % 2 == 0) {
                        description.append("четное ");
                    } else {
                        description.append("нечетное ");
                    }
                }

                description.append("число");

                resultLabel.setText("описание: " + description.toString());
                resultLabel.setStyle("-fx-text-fill: #3498db; -fx-font-weight: bold;");

                explanationLabel.setText("число: " + number + "\n" +
                        "анализ: " + (number > 0 ? ">0" : number < 0 ? "<0" : "=0") +
                        ", " + (number % 2 == 0 ? "четное" : "нечетное"));

            } catch (NumberFormatException e) {
                resultLabel.setText("описание: ошибка ввода!");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setHeaderText(null);
                alert.setContentText("введите целое число!");
                alert.show();
            }
        });

        VBox vbox = new VBox(15, titleLabel, descriptionLabel,
                new HBox(10, new Label("число:"), numberField),
                describeButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    //доп 2
    private VBox createAdditionalTask2() {
        Label titleLabel = new Label("доп 2");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        Label descriptionLabel = new Label("дано целое число, лежащее в диапазоне 1–999.\n" +
                "вывести его строку-описание вида:\n" +
                "«четное двузначное число», «нечетное трехзначное число» и т.д.");
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d;");
        descriptionLabel.setWrapText(true);

        TextField numberField = new TextField();
        numberField.setPromptText("введите число (1-999)");
        numberField.setPrefWidth(200);

        Button describeButton = new Button("описать число");
        describeButton.setStyle("-fx-background-color: #34495e; -fx-text-fill: white; -fx-font-weight: bold;");

        Label resultLabel = new Label("описание: ");
        Label explanationLabel = new Label();
        explanationLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #95a5a6;");

        describeButton.setOnAction(event -> {
            try {
                int number = Integer.parseInt(numberField.getText());

                if (number < 1 || number > 999) {
                    throw new IllegalArgumentException("число должно быть в диапазоне 1-999");
                }

                StringBuilder description = new StringBuilder();


                if (number % 2 == 0) {
                    description.append("четное ");
                } else {
                    description.append("нечетное ");
                }


                if (number >= 1 && number <= 9) {
                    description.append("однозначное ");
                } else if (number >= 10 && number <= 99) {
                    description.append("двузначное ");
                } else if (number >= 100 && number <= 999) {
                    description.append("трехзначное ");
                }

                description.append("число");

                resultLabel.setText("описание: " + description.toString());
                resultLabel.setStyle("-fx-text-fill: #9b59b6; -fx-font-weight: bold;");


                int digits;
                if (number < 10) digits = 1;
                else if (number < 100) digits = 2;
                else digits = 3;

                explanationLabel.setText("число: " + number + "\n" +
                        "количество цифр: " + digits + "\n" +
                        "четность: " + (number % 2 == 0 ? "четное" : "нечетное"));

            } catch (NumberFormatException e) {
                resultLabel.setText("описание: ОШИБКА ВВОДА!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                resultLabel.setStyle("-fx-text-fill: #e74c3c;");
                explanationLabel.setText("");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ОШИБКА(((((((");
                alert.setHeaderText(null);
                alert.setContentText("введите целое число!");
                alert.show();
            } catch (IllegalArgumentException e) {
                resultLabel.setText("описание: " + e.getMessage());
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
                new HBox(10, new Label("Число:"), numberField),
                describeButton, resultLabel, explanationLabel);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    public static void main(String[] args) {
        launch();
    }
}