package invaders.factory;

import org.json.simple.JSONObject;

public interface DifficultyLevel {
    // Method to get the game settings for this difficulty level
    JSONObject getGameSettings();

    // Method to get the player settings for this difficulty level
    JSONObject getPlayerSettings();

    // Method to get the bunkers settings for this difficulty level
    JSONObject getBunkersSettings();

    // Method to get the enemies settings for this difficulty level
    JSONObject getEnemiesSettings();
}

