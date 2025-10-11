 import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class RestaurantMenuSelector extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Restaurant Menu Selector");

        // Create Labels
        Label lblMenu = new Label("Menu");
        Label lblQuantity = new Label("Quantity");
        Label lblTotal = new Label("Total: $0.00");

        // Create Menu items
        CheckBox cbPizza = new CheckBox("Pizza ($8.50)");
        CheckBox cbBurger = new CheckBox("Burger ($5.50)");
        CheckBox cbPasta = new CheckBox("Pasta ($7.00)");
        CheckBox cbSalad = new CheckBox("Salad ($4.50)");

        // Quantity input
        Spinner<Integer> spPizza = new Spinner<>(0, 10, 0);
        Spinner<Integer> spBurger = new Spinner<>(0, 10, 0);
        Spinner<Integer> spPasta = new Spinner<>(0, 10, 0);
        Spinner<Integer> spSalad = new Spinner<>(0, 10, 0);

        // Button to calculate total
        Button btnCalculate = new Button("Calculate Total");

        // Layout for menu items and quantity
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        grid.add(cbPizza, 0, 0);
        grid.add(spPizza, 1, 0);

        grid.add(cbBurger, 0, 1);
        grid.add(spBurger, 1, 1);

        grid.add(cbPasta, 0, 2);
        grid.add(spPasta, 1, 2);

        grid.add(cbSalad, 0, 3);
        grid.add(spSalad, 1, 3);

        grid.add(btnCalculate, 0, 4);
        grid.add(lblTotal, 1, 4);

        // Button action
        btnCalculate.setOnAction(e -> {
            double total = 0;

            if (cbPizza.isSelected()) total += 8.50 * spPizza.getValue();
            if (cbBurger.isSelected()) total += 5.50 * spBurger.getValue();
            if (cbPasta.isSelected()) total += 7.00 * spPasta.getValue();
            if (cbSalad.isSelected()) total += 4.50 * spSalad.getValue();

            lblTotal.setText(String.format("Total: $%.2f", total));
        });

        // Scene and Stage
        Scene scene = new Scene(grid, 350, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
