package battle;

import droid.Droid;
import java.util.List;
import java.util.Scanner;
import util.ColorConsole;

public class OneVsOneBattle extends Battle {
    private Scanner scanner;

    public OneVsOneBattle(Droid droid1, Droid droid2) {
        super(List.of(droid1), List.of(droid2));
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void startBattle() {
        System.out.println(ColorConsole.PURPLE + "\n⚔️ === ПОЧАТОК БОЮ 1 НА 1 ===" + ColorConsole.RESET);
        logAction("Бій починається між " + team1.get(0).getName() + " та " + team2.get(0).getName());

        int round = 1;
        while (!isBattleOver()) {
            System.out.println(ColorConsole.YELLOW + "\n--- Раунд " + round + " ---" + ColorConsole.RESET);

            // Хід першого дроїда
            if (team1.get(0).isAlive()) {
                playerTurn(team1.get(0), team2.get(0));
            }

            // Хід другого дроїда
            if (team2.get(0).isAlive() && !isBattleOver()) {
                playerTurn(team2.get(0), team1.get(0));
            }

            regenerateEnergy();
            round++;
        }

        declareWinner();
    }

    private void playerTurn(Droid attacker, Droid defender) {
        System.out.println("\n" + ColorConsole.GREEN + "Хід " + attacker.getName() + ColorConsole.RESET);
        System.out.println("1. Атака");
        System.out.println("2. Спеціальна здатність");
        System.out.println("3. Пропустити хід");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                attacker.attack(defender);
                break;
            case 2:
                attacker.useSpecialAbility(defender);
                break;
            case 3:
                logAction(attacker.getName() + " пропускає хід");
                break;
        }
    }

    @Override
    public boolean isBattleOver() {
        return !team1.get(0).isAlive() || !team2.get(0).isAlive();
    }

    private void declareWinner() {
        Droid winner = team1.get(0).isAlive() ? team1.get(0) : team2.get(0);
        System.out.println(ColorConsole.GREEN + "\n🎉 ПЕРЕМОЖЕЦЬ: " + winner.getName() + "!" + ColorConsole.RESET);
        logAction("Переможець: " + winner.getName());
    }
}