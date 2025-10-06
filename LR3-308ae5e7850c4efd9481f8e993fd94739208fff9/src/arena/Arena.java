package arena;

import util.ColorConsole; // Додати імпорт

public class Arena {
    private String name;
    private String effect;

    public Arena(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public void applyArenaEffect() {
        System.out.println(ColorConsole.CYAN + "🏟️  Арена: " + name + " - " + effect + ColorConsole.RESET);
    }

    public String getName() { return name; }
    public String getEffect() { return effect; }
}