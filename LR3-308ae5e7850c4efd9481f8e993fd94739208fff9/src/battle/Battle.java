package battle;

import droid.Droid;
import java.util.List;
import util.ColorConsole;

public abstract class Battle {
    protected List<Droid> team1;
    protected List<Droid> team2;
    protected StringBuilder battleLog;

    public Battle(List<Droid> team1, List<Droid> team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.battleLog = new StringBuilder();
    }

    public abstract void startBattle();
    public abstract boolean isBattleOver();

    protected boolean isTeamAlive(List<Droid> team) {
        return team.stream().anyMatch(Droid::isAlive);
    }

    protected void regenerateEnergy() {
        team1.forEach(Droid::regenerate);
        team2.forEach(Droid::regenerate);
    }

    protected void logAction(String action) {
        battleLog.append(action).append("\n");
        System.out.println(action);
    }

    public String getBattleLog() {
        return battleLog.toString();
    }

    protected void printTeamStatus() {
        System.out.println("\n" + ColorConsole.BLUE + "=== СТАТУС КОМАНД ===" + ColorConsole.RESET);
        System.out.println(ColorConsole.CYAN + "Команда 1:" + ColorConsole.RESET);
        team1.forEach(droid -> System.out.println("  " + droid));
        System.out.println(ColorConsole.CYAN + "Команда 2:" + ColorConsole.RESET);
        team2.forEach(droid -> System.out.println("  " + droid));
        System.out.println();
    }
}