//Name: Divya Bharathi  I 
//Reg no: 2117240020096


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class RealTimeChart extends Application {

    private XYChart.Series<Number, Number> series = new XYChart.Series<>();
    private int timeCounter = 0;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Real-Time Weather Data - Kanchipuram");

        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Temperature (°C)");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Live Temperature Updates");
        series.setName("Temperature");
        lineChart.getData().add(series);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), e -> {
            double temp = fetchTemperature();
            series.getData().add(new XYChart.Data<>(timeCounter, temp));
            timeCounter += 10;
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private double fetchTemperature() {
        try {
            String apiKey = "7dab5febadff449781d82955252906"; // Replace with your actual key
            String urlString = "https://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q=Kanchipuram";
            URL url = new URL(urlString);
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
            return obj.getJSONObject("current").getDouble("temp_c");

        } catch (Exception e) {
            System.out.println("Error fetching temperature: " + e.getMessage());
            return 0.0; // fallback value
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}