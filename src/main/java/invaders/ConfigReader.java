package invaders;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigReader {
    private JSONObject gameInfo;
    private JSONObject playerInfo;
    private JSONArray bunkersInfo;
    private JSONArray enemiesInfo;

    // The Singleton instance
    private static ConfigReader instance;

    // Private constructor to prevent instantiation
    private ConfigReader() {}

    // Public method to get the instance
    public static ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    // Instance method to parse the configuration
    public void parse(String configPath){
        JSONParser parser = new JSONParser();
        try {
            JSONObject configObject = (JSONObject) parser.parse(new FileReader(configPath));

            // Reading game section
            gameInfo = (JSONObject) configObject.get("Game");

            // Reading player section
            playerInfo = (JSONObject) configObject.get("Player");

            // Reading bunker section
            bunkersInfo = (JSONArray) configObject.get("Bunkers");

            // Reading enemies section
            enemiesInfo = (JSONArray) configObject.get("Enemies");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Instance getters for the configuration data
    public JSONObject getGameInfo() {
        return gameInfo;
    }

    public JSONObject getPlayerInfo() {
        return playerInfo;
    }

    public JSONArray getBunkersInfo() {
        return bunkersInfo;
    }

    public JSONArray getEnemiesInfo() {
        return enemiesInfo;
    }
}
