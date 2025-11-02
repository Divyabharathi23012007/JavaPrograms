import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import java.io.File;

public class MediaPlayerJavaFx extends Application {

    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {

        Button loadBtn = new Button("Load Media");
        Button playBtn = new Button("Play");
        Button pauseBtn = new Button("Pause");
        Button stopBtn = new Button("Stop");

        statusLabel = new Label("Status: Waiting for file...");
        mediaView = new MediaView();

        loadBtn.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Media File");
            fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Media Files", "*.mp4", "*.mp3", "*.wav")
            );

            File file = fileChooser.showOpenDialog(primaryStage);

            if (file != null) {
                try {
                    Media media = new Media(file.toURI().toString());
                    if (mediaPlayer != null) mediaPlayer.dispose(); // clear old player
                    mediaPlayer = new MediaPlayer(media);
                    mediaView.setMediaPlayer(mediaPlayer);
                    mediaView.setFitWidth(600);
                    mediaView.setPreserveRatio(true);
                    statusLabel.setText("Status: File Loaded. Ready to play.");
                } catch (Exception ex) {
                    statusLabel.setText("Error: Unsupported or invalid media file!");
                }
            } else {
                statusLabel.setText("Error: No file selected!");
            }
        });

        playBtn.setOnAction(e -> {
            if (mediaPlayer == null) {
                statusLabel.setText("Error: No media loaded!");
            } else {
                mediaPlayer.play();
                statusLabel.setText("Status: Playing");
            }
        });

        pauseBtn.setOnAction(e -> {
            if (mediaPlayer == null) {
                statusLabel.setText("Error: No media loaded!");
            } else {
                mediaPlayer.pause();
                statusLabel.setText("Status: Paused");
            }
        });

        stopBtn.setOnAction(e -> {
            if (mediaPlayer == null) {
                statusLabel.setText("Error: No media loaded!");
            } else {
                mediaPlayer.stop();
                statusLabel.setText("Status: Stopped & Reset");
            }
        });

        HBox controls = new HBox(10, loadBtn, playBtn, pauseBtn, stopBtn);
        VBox root = new VBox(15, mediaView, controls, statusLabel);
        root.setStyle("-fx-padding: 15; -fx-font-size: 14;");

        Scene scene = new Scene(root, 650, 450);
        primaryStage.setTitle("JavaFX Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
