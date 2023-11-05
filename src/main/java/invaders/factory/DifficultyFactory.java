package invaders.factory;

public class DifficultyFactory {

    public static DifficultyLevel createDifficulty(String level) {
        switch (level.toLowerCase()) {
            case "easy":
                return new EasyDifficulty();
            case "medium":
                return new MediumDifficulty();
            case "hard":
                return new HardDifficulty();
            default:
                throw new IllegalArgumentException("Unknown difficulty level: " + level);
        }
    }
}
