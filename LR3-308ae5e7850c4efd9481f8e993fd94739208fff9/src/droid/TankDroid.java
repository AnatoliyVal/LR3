package droid;

import util.ColorConsole;

public class TankDroid extends Droid {
    private int armor;

    public TankDroid(String name) {
        super(name, 200, 15, 70, 80);
        this.armor = 10;
    }

    @Override
    public void takeDamage(int damage) {
        int reducedDamage = Math.max(1, damage - armor);
        super.takeDamage(reducedDamage);
    }

    @Override
    public void useSpecialAbility(Droid target) {
        if (hasEnergy(25)) {
            consumeEnergy(25);
            armor += 5;
            System.out.println(ColorConsole.BLUE + "🛡️ " + name + " посилює броню! Тепер броня: " + armor + ColorConsole.RESET);
        }
    }

    @Override
    public String getType() {
        return "Танк";
    }
}