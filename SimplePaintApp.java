import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SimplePaintApp extends Application {

    private boolean eraserMode = false;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        ColorPicker colorPicker = new ColorPicker(Color.BLACK);
        Slider brushSize = new Slider(1, 50, 5);
        Button clearBtn = new Button("Clear");
        ToggleButton eraserBtn = new ToggleButton("Eraser");

        // Drawing logic
        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
            gc.setLineWidth(brushSize.getValue());
            gc.setStroke(eraserMode ? Color.WHITE : colorPicker.getValue());
            gc.beginPath();
            gc.moveTo(e.getX(), e.getY());
            gc.stroke();
        });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, e -> {
            gc.setLineWidth(brushSize.getValue());
            gc.setStroke(eraserMode ? Color.WHITE : colorPicker.getValue());
            gc.lineTo(e.getX(), e.getY());
            gc.stroke();
        });

        // Toggle eraser mode
        eraserBtn.setOnAction(e -> eraserMode = eraserBtn.isSelected());

        // Clear canvas
        clearBtn.setOnAction(e -> gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));

        // Layout
        HBox controls = new HBox(10, colorPicker, brushSize, eraserBtn, clearBtn);
        BorderPane root = new BorderPane();
        root.setTop(controls);
        root.setCenter(canvas);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("JavaFX Paint App with Eraser");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}