import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;

public class WeatherDashboard extends JFrame {
    // View components
    private JTextField cityField = new JTextField(15);
    private JButton fetchButton = new JButton("Get Weather");
    private JLabel tempLabel = new JLabel("Temperature: ");
    private JLabel descLabel = new JLabel("Description: ");
    private JLabel humidityLabel = new JLabel("Humidity: ");

    public WeatherDashboard() {
        setTitle("Weather Dashboard");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("City:"));
        inputPanel.add(cityField);
        inputPanel.add(fetchButton);

        add(inputPanel);
        add(tempLabel);
        add(descLabel);
        add(humidityLabel);

        // Controller logic
        fetchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String city = cityField.getText();
                try {
                    JSONObject data = fetchWeather(city);
                    String temp = String.valueOf(data.getJSONObject("main").getDouble("temp"));
                    String desc = data.getJSONArray("weather").getJSONObject(0).getString("description");
                    String humidity = String.valueOf(data.getJSONObject("main").getInt("humidity"));

                    updateWeather(temp, desc, humidity);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        setVisible(true);
    }

    // Model logic
    private JSONObject fetchWeather(String city) throws Exception {
        String apiKey = "YOUR API KEY"; // Replace with your OpenWeatherMap API key
        String endpoint = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder json = new StringBuilder();
        while (scanner.hasNext()) {
            json.append(scanner.nextLine());
        }
        scanner.close();

        return new JSONObject(json.toString());
    }

    // View update method
    private void updateWeather(String temp, String desc, String humidity) {
        tempLabel.setText("Temperature: " + temp + " °C");
        descLabel.setText("Description: " + desc);
        humidityLabel.setText("Humidity: " + humidity + "%");
    }

    public static void main(String[] args) {
        new WeatherDashboard();
    }
}