package battle;

import droid.Droid;
import java.util.List;
import java.util.Random;
import util.ColorConsole;

public class TeamBattle extends Battle {
    private Random random;

    public TeamBattle(List<Droid> team1, List<Droid> team2) {
        super(team1, team2);
        this.random = new Random();
    }

    @Override
    public void startBattle() {
        System.out.println(ColorConsole.PURPLE + "\n⚔️ === ПОЧАТОК КОМАНДНОГО БОЮ ===" + ColorConsole.RESET);
        logAction("Командний бій починається! Команда 1 (" + team1.size() + " дроїдів) vs Команда 2 (" + team2.size() + " дроїдів)");

        int round = 1;
        while (!isBattleOver()) {
            System.out.println(ColorConsole.YELLOW + "\n--- Раунд " + round + " ---" + ColorConsole.RESET);

            // Хід команди 1
            if (isTeamAlive(team1)) {
                teamTurn(team1, team2, "Команда 1");
            }

            // Хід команди 2
            if (isTeamAlive(team2) && !isBattleOver()) {
                teamTurn(team2, team1, "Команда 2");
            }

            printTeamStatus();
            regenerateEnergy();
            round++;
        }

        declareWinner();
    }

    private void teamTurn(List<Droid> attackingTeam, List<Droid> defendingTeam, String teamName) {
        logAction("\nХід " + teamName);

        for (Droid attacker : attackingTeam) {
            if (!attacker.isAlive() || !isTeamAlive(defendingTeam)) continue;

            // Вибір цілі - атакуємо найслабшого живого дроїда
            Droid target = findWeakestDroid(defendingTeam);
            if (target != null) {
                // Випадковий вибір дії
                if (random.nextDouble() < 0.7) {
                    attacker.attack(target);
                } else {
                    attacker.useSpecialAbility(target);
                }
            }
        }
    }

    private Droid findWeakestDroid(List<Droid> team) {
        return team.stream()
                .filter(Droid::isAlive)
                .min((d1, d2) -> Integer.compare(d1.getHealth(), d2.getHealth()))
                .orElse(null);
    }

    @Override
    public boolean isBattleOver() {
        return !isTeamAlive(team1) || !isTeamAlive(team2);
    }

    private void declareWinner() {
        List<Droid> winners = isTeamAlive(team1) ? team1 : team2;
        String teamName = isTeamAlive(team1) ? "Команда 1" : "Команда 2";

        System.out.println(ColorConsole.GREEN + "\n🎉 ПЕРЕМОЖЕЦЬ: " + teamName + "!" + ColorConsole.RESET);
        logAction("Переможець: " + teamName);

        System.out.println("Дроїди, що вижили:");
        winners.stream()
                .filter(Droid::isAlive)
                .forEach(droid -> System.out.println("  " + droid.getName()));
    }
}