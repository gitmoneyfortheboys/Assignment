package invaders.factory;

import invaders.engine.GameEngine;
import invaders.physics.Collider;
import invaders.physics.Vector2D;
import invaders.strategy.FastProjectileStrategy;
import invaders.strategy.ProjectileStrategy;
import invaders.strategy.SlowProjectileStrategy;
import javafx.scene.image.Image;

public class EnemyProjectile extends Projectile{
    private ProjectileStrategy strategy;

    public EnemyProjectile(Vector2D position, ProjectileStrategy strategy, Image image) {
        super(position,image);
        this.strategy = strategy;
    }

    @Override
    public void update(GameEngine model) {
        strategy.update(this);

        if(this.getPosition().getY()>= model.getGameHeight() - this.getImage().getHeight()){
            this.takeDamage(1);
        }

    }
    @Override
    public String getRenderableObjectName() {
        return "EnemyProjectile";
    }

    @Override
    public int getScoreValue() {
        // The score could be based on the strategy of the projectile.
        // For example, faster projectiles might be worth more points.
        if (this.strategy instanceof FastProjectileStrategy) {
            return 2; // Example score value for fast projectiles
        } else if (this.strategy instanceof SlowProjectileStrategy) {
            return 1; // Example score value for slow projectiles
        } else {
            return 0; // Default score if no strategy is set or it's a different type
        }
    }
}
