import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.*;

public class SwingSliderDemo extends Application {
    public void start(Stage s) {
        SwingNode sn=new SwingNode();
        sn.setContent(new JSlider());
        s.setScene(new Scene(new StackPane(sn),200,100));
        s.show();
    }
    public static void main(String[] args){
    	launch(args);  
    }
} 