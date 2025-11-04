import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class RealDataVisualization extends Application {

    private XYChart.Series<Number, Number> series;
    private Timeline timeline;
    private int timeCounter = 0;
    private Random random = new Random();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Real-Time Data Visualization - Chennai Humidity");

        // X & Y Axis
        NumberAxis xAxis = new NumberAxis(0, 60, 5);
        xAxis.setLabel("Time (seconds)");

        NumberAxis yAxis = new NumberAxis(40, 90, 5);
        yAxis.setLabel("Humidity (%)");

        // Chart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Real-Time Humidity (Chennai)");
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(true);

        series = new XYChart.Series<>();
        series.setName("Humidity (%)");
        lineChart.getData().add(series);

        // START button
        Button startBtn = new Button("Start Updates");
        startBtn.setStyle("-fx-font-size: 15px; -fx-padding: 6;");
        startBtn.setOnAction(e -> startUpdates());

        // STOP button
        Button stopBtn = new Button("Stop Updates");
        stopBtn.setStyle("-fx-font-size: 15px; -fx-padding: 6;");
        stopBtn.setOnAction(e -> stopUpdates());

        HBox buttons = new HBox(10, startBtn, stopBtn);
        VBox root = new VBox(10, lineChart, buttons);

        Scene scene = new Scene(root, 900, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startUpdates() {
        stopUpdates();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // Generate realistic humidity fluctuations
            double humidity = 65 + Math.sin(timeCounter * 0.3) * 10 + random.nextDouble() * 5; 
            series.getData().add(new XYChart.Data<>(timeCounter++, humidity));

            // Keep max 60 seconds
            if (series.getData().size() > 60) {
                series.getData().remove(0);
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
