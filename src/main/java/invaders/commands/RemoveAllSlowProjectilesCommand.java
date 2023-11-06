package invaders.commands;

import invaders.engine.GameEngine;

public class RemoveAllSlowProjectilesCommand implements CheatCommand {
    private GameEngine gameEngine;

    public RemoveAllSlowProjectilesCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void execute() {
        gameEngine.removeAllProjectilesOfType("SlowProjectile");
    }
}