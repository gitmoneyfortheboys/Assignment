package invaders.factory;

import invaders.factory.DifficultyLevel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

// Concrete class for the "Hard" difficulty level
public class HardDifficulty implements DifficultyLevel {
    private JSONObject gameSettings;
    private JSONObject playerSettings;
    private JSONArray bunkersSettings;
    private JSONArray enemiesSettings;

    public HardDifficulty() {
        // Load the configuration from the JSON file
        JSONParser parser = new JSONParser();
        try {
            JSONObject config = (JSONObject) parser.parse(new FileReader("src/main/resources/config_hard.json"));
            this.gameSettings = (JSONObject) config.get("Game");
            this.playerSettings = (JSONObject) config.get("Player");
            this.bunkersSettings = (JSONArray) config.get("Bunkers");
            this.enemiesSettings = (JSONArray) config.get("Enemies");
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
    public JSONArray getBunkersSettings() {
        return bunkersSettings;
    }

    @Override
    public JSONArray getEnemiesSettings() {
        return enemiesSettings;
    }
}
