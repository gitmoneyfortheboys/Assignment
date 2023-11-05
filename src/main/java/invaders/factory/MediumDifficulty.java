package invaders.factory;

import invaders.factory.DifficultyLevel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

// Concrete class for the "Medium" difficulty level
public class MediumDifficulty implements DifficultyLevel {
    private JSONObject gameSettings;
    private JSONObject playerSettings;
    private JSONObject bunkersSettings;
    private JSONObject enemiesSettings;

    public MediumDifficulty() {
        // Load the configuration from the JSON file
        JSONParser parser = new JSONParser();
        try {
            JSONObject config = (JSONObject) parser.parse(new FileReader("src/main/resources/config_medium.json"));
            this.gameSettings = (JSONObject) config.get("Game");
            this.playerSettings = (JSONObject) config.get("Player");
            this.bunkersSettings = (JSONObject) config.get("Bunkers");
            this.enemiesSettings = (JSONObject) config.get("Enemies");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public JSONObject getGameSettings() {
        return gameSettings;
    }

    @Override
    public JSONObject getPlayerSettings() {
        return playerSettings;
    }

    @Override
    public JSONObject getBunkersSettings() {
        return bunkersSettings;
    }

    @Override
    public JSONObject getEnemiesSettings() {
        return enemiesSettings;
    }
}
