package invaders.engine;

import java.util.ArrayList;
import java.util.List;

import invaders.ConfigReader;
import invaders.builder.BunkerBuilder;
import invaders.builder.Director;
import invaders.builder.EnemyBuilder;
import invaders.factory.DifficultyFactory;
import invaders.factory.DifficultyLevel;
import invaders.factory.Projectile;
import invaders.gameobject.Bunker;
import invaders.gameobject.Enemy;
import invaders.gameobject.GameObject;
import invaders.entities.Player;
import invaders.rendering.Renderable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.Instant;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.text.SimpleDateFormat;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * This class manages the main loop and logic of the game
 */
public class GameEngine {
	private List<GameObject> gameObjects = new ArrayList<>(); // A list of game objects that gets updated each frame
	private List<GameObject> pendingToAddGameObject = new ArrayList<>();
	private List<GameObject> pendingToRemoveGameObject = new ArrayList<>();

	private List<Renderable> pendingToAddRenderable = new ArrayList<>();
	private List<Renderable> pendingToRemoveRenderable = new ArrayList<>();

	private List<Renderable> renderables =  new ArrayList<>();

	private Player player;

	private boolean left;
	private boolean right;
	private int gameWidth;
	private int gameHeight;
	private int timer = 45;

	private DifficultyLevel difficultyLevel;

	private Instant gameStartTime;
	private Duration elapsedTime;

    private String formattedTime = "0:00";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("m:ss");

	public GameEngine(String difficulty){
	
		// Instantiate the difficulty level based on user selection
		this.difficultyLevel = DifficultyFactory.createDifficulty(difficulty);

		// Get game width and height from the difficulty settings
		gameWidth = ((Long)((JSONObject) difficultyLevel.getGameSettings().get("size")).get("x")).intValue();
		gameHeight = ((Long)((JSONObject) difficultyLevel.getGameSettings().get("size")).get("y")).intValue();

		// Get player info from the difficulty settings
		this.player = new Player(difficultyLevel.getPlayerSettings());

		this.gameStartTime = Instant.now();
        updateGameTime();


	
		Director director = new Director();
		BunkerBuilder bunkerBuilder = new BunkerBuilder();
		// Get Bunkers info from the difficulty level
		JSONArray bunkersInfo = difficultyLevel.getBunkersSettings();
		for(Object eachBunkerInfo : bunkersInfo){
			Bunker bunker = director.constructBunker(bunkerBuilder, (JSONObject) eachBunkerInfo);
			gameObjects.add(bunker);
			renderables.add(bunker);
		}
	
		EnemyBuilder enemyBuilder = new EnemyBuilder();
		// Get Enemy info from the difficulty level
		JSONArray enemiesInfo = difficultyLevel.getEnemiesSettings();
		for(Object eachEnemyInfo : enemiesInfo){
			Enemy enemy = director.constructEnemy(this, enemyBuilder, (JSONObject) eachEnemyInfo);
			gameObjects.add(enemy);
			renderables.add(enemy);
		}

		this.gameStartTime = Instant.now();

		renderables.add(player);
	}
	

	/**
	 * Updates the game/simulation
	 */
	public void update(){
		timer+=1;

		movePlayer();

		for(GameObject go: gameObjects){
			go.update(this);
		}

		for (int i = 0; i < renderables.size(); i++) {
			Renderable renderableA = renderables.get(i);
			for (int j = i+1; j < renderables.size(); j++) {
				Renderable renderableB = renderables.get(j);

				if((renderableA.getRenderableObjectName().equals("Enemy") && renderableB.getRenderableObjectName().equals("EnemyProjectile"))
						||(renderableA.getRenderableObjectName().equals("EnemyProjectile") && renderableB.getRenderableObjectName().equals("Enemy"))||
						(renderableA.getRenderableObjectName().equals("EnemyProjectile") && renderableB.getRenderableObjectName().equals("EnemyProjectile"))){
				}else{
					if(renderableA.isColliding(renderableB) && (renderableA.getHealth()>0 && renderableB.getHealth()>0)) {
						renderableA.takeDamage(1);
						renderableB.takeDamage(1);
					}
				}
			}
		}


		// ensure that renderable foreground objects don't go off-screen
		int offset = 1;
		for(Renderable ro: renderables){
			if(!ro.getLayer().equals(Renderable.Layer.FOREGROUND)){
				continue;
			}
			if(ro.getPosition().getX() + ro.getWidth() >= gameWidth) {
				ro.getPosition().setX((gameWidth - offset) -ro.getWidth());
			}

			if(ro.getPosition().getX() <= 0) {
				ro.getPosition().setX(offset);
			}

			if(ro.getPosition().getY() + ro.getHeight() >= gameHeight) {
				ro.getPosition().setY((gameHeight - offset) -ro.getHeight());
			}

			if(ro.getPosition().getY() <= 0) {
				ro.getPosition().setY(offset);
			}
		}

		updateGameTime();
	}

	public void updateGameTime() {
        if (!isGameOver()) {
            Duration duration = Duration.between(gameStartTime, Instant.now());
            LocalTime time = LocalTime.ofSecondOfDay(duration.getSeconds());
            formattedTime = time.format(TIME_FORMATTER);
        }
    }

    private boolean isGameOver() {
        // Check if the player is dead
        if (player != null && !player.isAlive()) {
            return true;
        }

        // Or if there are no more enemies alive
        for (GameObject obj : gameObjects) {
            if (obj instanceof Enemy && ((Enemy) obj).isAlive()) {
                return false; // There is at least one enemy alive
            }
        }

        // If we reach this point, all enemies are destroyed
        return true;
    }

    public String getFormattedTime() {
        return formattedTime;
    }

	public List<Renderable> getRenderables(){
		return renderables;
	}

	public List<GameObject> getGameObjects() {
		return gameObjects;
	}
	public List<GameObject> getPendingToAddGameObject() {
		return pendingToAddGameObject;
	}

	public List<GameObject> getPendingToRemoveGameObject() {
		return pendingToRemoveGameObject;
	}

	public List<Renderable> getPendingToAddRenderable() {
		return pendingToAddRenderable;
	}

	public List<Renderable> getPendingToRemoveRenderable() {
		return pendingToRemoveRenderable;
	}


	public void leftReleased() {
		this.left = false;
	}

	public void rightReleased(){
		this.right = false;
	}

	public void leftPressed() {
		this.left = true;
	}
	public void rightPressed(){
		this.right = true;
	}

	public boolean shootPressed(){
		if(timer>45 && player.isAlive()){
			Projectile projectile = player.shoot();
			gameObjects.add(projectile);
			renderables.add(projectile);
			timer=0;
			return true;
		}
		return false;
	}

	private void movePlayer(){
		if(left){
			player.left();
		}

		if(right){
			player.right();
		}
	}

	public int getGameWidth() {
		return gameWidth;
	}

	public int getGameHeight() {
		return gameHeight;
	}

	public Player getPlayer() {
		return player;
	}
}
