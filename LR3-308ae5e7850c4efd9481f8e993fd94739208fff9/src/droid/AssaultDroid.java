package droid;

import util.ColorConsole;

public class AssaultDroid extends Droid {
    private int criticalChance;

    public AssaultDroid(String name) {
        super(name, 120, 25, 80, 100);
        this.criticalChance = 30;
    }

    @Override
    public void useSpecialAbility(Droid target) {
        if (hasEnergy(30)) {
            consumeEnergy(30);
            // Критичний удар
            if (Math.random() * 100 < criticalChance) {
                int criticalDamage = damage * 2;
                target.takeDamage(criticalDamage);
                System.out.println(ColorConsole.PURPLE + "🔥 " + name + " використовує КРИТИЧНИЙ УДАР! Завдає " + criticalDamage + " шкоди!" + ColorConsole.RESET);
            } else {
                System.out.println(ColorConsole.YELLOW + "⚡ " + name + " намагається завдати критичного удару, але промахується!" + ColorConsole.RESET);
            }
        }
    }

    @Override
    public String getType() {
        return "Штурмовик";
    }
}