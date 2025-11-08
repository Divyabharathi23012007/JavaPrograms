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
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RealHumidityChennai extends Application {

    private XYChart.Series<Number, Number> series;
    private Timeline timeline;
    private int timeCounter = 0;
    private final String API_KEY = "902943b2c73368b249514228bdad43a6"; // Replace with your OpenWeatherMap API key

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Real-Time Humidity - Chennai");

        NumberAxis xAxis = new NumberAxis(0, 60, 5);
        xAxis.setLabel("Time (seconds)");

        NumberAxis yAxis = new NumberAxis(40, 100, 5);
        yAxis.setLabel("Humidity (%)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Live Humidity Data");
        lineChart.setAnimated(false);
        lineChart.setCreateSymbols(true);

        series = new XYChart.Series<>();
        series.setName("Humidity");
        lineChart.getData().add(series);

        Button startBtn = new Button("Start");
        startBtn.setOnAction(e -> startUpdates());

        Button stopBtn = new Button("Stop");
        stopBtn.setOnAction(e -> stopUpdates());

        VBox root = new VBox(10, lineChart, new HBox(10, startBtn, stopBtn));
        Scene scene = new Scene(root, 900, 550);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startUpdates() {
        stopUpdates();

        timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            double humidity = fetchHumidity();
            series.getData().add(new XYChart.Data<>(timeCounter++, humidity));
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

    private double fetchHumidity() {
        try {
            String urlStr = "https://api.openweathermap.org/data/2.5/weather?q=Chennai&appid=" + API_KEY + "&units=metric";
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            reader.close();

            JSONObject obj = new JSONObject(json.toString());
            return obj.getJSONObject("main").getDouble("humidity");

        } catch (Exception e) {
            System.out.println("Error fetching humidity: " + e.getMessage());
            return 0;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}