package invaders.commands;

import invaders.engine.GameEngine;

public class RemoveEnemyCommand implements CheatCommand {
    private GameEngine gameEngine;
    private String strategy;

    public RemoveEnemyCommand(GameEngine gameEngine, String strategy) {
        this.gameEngine = gameEngine;
        this.strategy = strategy;
    }

    @Override
    public void execute() {
        gameEngine.removeAllEnemiesWithStrategy(strategy);
    }
}