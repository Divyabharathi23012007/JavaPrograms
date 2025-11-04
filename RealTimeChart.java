//Name: Divya Bharathi  I 
//Reg no: 2117240020096
//Real time chart in JavaFx


import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class RealTimeChart extends Application {

    private Timeline timeline;
    private int xValue = 0;
    private Random random = new Random();

    @Override
    public void start(Stage stage) {

        // Axes
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time (seconds)");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Data Value");

        // Create LineChart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Real-Time Data Visualization");

        // Series for dynamic data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Live Data");
        lineChart.getData().add(series);

        // Start Button
        Button startBtn = new Button("Start Updates");
        startBtn.setOnAction(e -> startUpdates(series));

        // Stop Button
        Button stopBtn = new Button("Stop Updates");
        stopBtn.setOnAction(e -> stopUpdates());

        // Arrange UI
        VBox root = new VBox(10, lineChart, startBtn, stopBtn);
        Scene scene = new Scene(root, 800, 500);

        stage.setTitle("Real-Time Chart in JavaFX");
        stage.setScene(scene);
        stage.show();
    }

    private void startUpdates(XYChart.Series<Number, Number> series) {
        if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) return;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            try {
                // Simulate real numeric data
                int yValue = random.nextInt(200) - 50; // -50 to 150 (Out of range case)

                // Handle non-numeric input simulation
                if (random.nextInt(20) == 0) throw new NumberFormatException("Invalid data!");

                series.getData().add(new XYChart.Data<>(xValue++, yValue));

                // Auto-remove oldest values to avoid memory growth
                if (series.getData().size() > 30) {
                    series.getData().remove(0);
                }

            } catch (Exception ex) {
                System.out.println("Handled invalid input: " + ex.getMessage());
            }

        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void stopUpdates() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

