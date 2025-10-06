package droid;

import util.ColorConsole;

public class SupportDroid extends Droid {
    private int healPower;

    public SupportDroid(String name) {
        super(name, 100, 10, 90, 120);
        this.healPower = 30;
    }

    @Override
    public void useSpecialAbility(Droid target) {
        if (hasEnergy(20)) {
            consumeEnergy(20);
            if (target.isAlive) {
                int healAmount = Math.min(healPower, target.getMaxHealth() - target.getHealth());
                target.health += healAmount;
                System.out.println(ColorConsole.GREEN + "💚 " + name + " лікує " + target.name + " на " + healAmount + " здоров'я!" + ColorConsole.RESET);
            }
        }
    }

    @Override
    public String getType() {
        return "Підтримка";
    }
}