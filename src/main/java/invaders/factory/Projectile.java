package invaders.factory;

import invaders.gameobject.GameObject;
import invaders.physics.Collider;
import invaders.physics.Vector2D;
import invaders.rendering.Renderable;
import invaders.strategy.ProjectileStrategy;
import javafx.scene.image.Image;

public abstract class Projectile implements Renderable, GameObject {
    private int lives = 1;
    private Vector2D position;
    private final Image image;

    private ProjectileStrategy strategy;

    public Projectile(Vector2D position, Image image) {
        this.position = position;
        this.image = image;
    }

    // Method to get the strategy of the projectile
    public ProjectileStrategy getStrategy() {
        return this.strategy;
    }
    
    // Method to set the strategy of the projectile
    public void setStrategy(ProjectileStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public Layer getLayer() {
        return Layer.FOREGROUND;
    }

    @Override
    public void start() {}

    @Override
    public double getWidth() {
        return 10;
    }

    @Override
    public double getHeight() {
        return 10;
    }

    @Override
    public void takeDamage(double amount) {
        this.lives-=1;
    }

    @Override
    public double getHealth() {
        return this.lives;
    }

    @Override
    public boolean isAlive() {
        return this.lives>0;
    }

}
