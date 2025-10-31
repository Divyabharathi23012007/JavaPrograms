import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.JSlider;
import javax.swing.SwingUtilities;

public class SwingSliderInJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a SwingNode to hold the Swing JSlider
        SwingNode swingNode = new SwingNode();
        Label valueLabel = new Label("Slider Value: 50");

        // Create and configure the Swing JSlider
        SwingUtilities.invokeLater(() -> {
            JSlider slider = new JSlider(0, 100, 50);
            slider.setMajorTickSpacing(10);
            slider.setPaintTicks(true);
            slider.setPaintLabels(true);

            // Add a change listener to update the JavaFX label
            slider.addChangeListener(e -> {
                int value = slider.getValue();
                valueLabel.setText("Slider Value: " + value);
            });

            swingNode.setContent(slider);
        });

        // Layout in JavaFX
        VBox root = new VBox(20);
        root.getChildren().addAll(valueLabel, swingNode);

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("JavaFX with SwingSlider");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}