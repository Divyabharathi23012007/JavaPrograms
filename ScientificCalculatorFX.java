import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class ScientificCalculatorFX extends Application {
    TextField display;

    @Override
    public void start(Stage stage) {
        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 20px; -fx-alignment: center-right;");
        display.setPrefHeight(60);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setPadding(new Insets(10));

        String[][] buttons = {
                {"7","8","9","/","sqrt"},
                {"4","5","6","*","pow"},
                {"1","2","3","-","log"},
                {"0",".","=","+","C"},
                {"sin","cos","tan"}
        };

        int row = 0;
        for (String[] line : buttons) {
            int col = 0;
            for (String text : line) {
                Button btn = new Button(text);
                btn.setPrefSize(80, 50);
                btn.setStyle("-fx-font-size: 16px;");
                btn.setOnAction(e -> handleButton(text));
                grid.add(btn, col++, row);
            }
            row++;
        }

        VBox root = new VBox(10, display, grid);
        root.setPadding(new Insets(10));

        stage.setScene(new Scene(root));
        stage.setTitle("Scientific Calculator");
        stage.show();
    }

    void handleButton(String text) {
        try {
            switch (text) {
                case "C":
                    display.clear();
                    break;
                case "=":
                    evaluate();
                    break;
                case "sqrt":
                    display.appendText("Math.sqrt(");
                    break;
                case "pow":
                    display.appendText("Math.pow(");
                    break;
                case "sin":
                    display.appendText("Math.sin(");
                    break;
                case "cos":
                    display.appendText("Math.cos(");
                    break;
                case "tan":
                    display.appendText("Math.tan(");
                    break;
                case "log":
                    display.appendText("Math.log10(");
                    break;
                default:
                    display.appendText(text);
            }
        } catch (Exception e) {
            display.setText("Error");
        }
    }

    void evaluate() {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            String expr = display.getText();
            Object result = engine.eval(expr);
            display.setText(String.valueOf(result));
        } catch (Exception e) {
            display.setText("Error");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
