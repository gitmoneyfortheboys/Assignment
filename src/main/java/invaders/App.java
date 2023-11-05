package invaders;

import javafx.application.Application;
import javafx.stage.Stage;
import invaders.engine.GameEngine;
import invaders.engine.GameWindow;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String selectedDifficulty = "easy"; // This should be determined by user input

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
