package invaders;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import invaders.engine.GameEngine;
import invaders.engine.GameWindow;

public class App extends Application {

    private String selectedDifficulty = "easy"; // default difficulty

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a VBox to hold the instructions
        VBox root = new VBox();
        Text instructions = new Text("Press 'E' for Easy, 'M' for Medium, or 'H' for Hard difficulty, then press 'Enter' to start the game.");
        root.getChildren().add(instructions);

        // Create a scene with the VBox as the root node
        Scene scene = new Scene(root, 600, 400);

        // Set an event filter for key pressed events
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case E:
                    selectedDifficulty = "easy";
                    instructions.setText("Easy difficulty selected. Press 'Enter' to start the game.");
                    break;
                case M:
                    selectedDifficulty = "medium";
                    instructions.setText("Medium difficulty selected. Press 'Enter' to start the game.");
                    break;
                case H:
                    selectedDifficulty = "hard";
                    instructions.setText("Hard difficulty selected. Press 'Enter' to start the game.");
                    break;
                case ENTER:
                    startGame(primaryStage);
                    break;
                default:
                    break;
            }
        });

        primaryStage.setTitle("Space Invaders - Select Difficulty");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame(Stage primaryStage) {
        // Create the game engine with the selected difficulty
        GameEngine model = new GameEngine(selectedDifficulty);
        GameWindow window = new GameWindow(model);
        window.run();

        primaryStage.setTitle("Space Invaders");
        primaryStage.setScene(window.getScene());
        primaryStage.show();

        window.run();
    }
}
