package droid;

import util.ColorConsole;

public class SniperDroid extends Droid {
    private int headshotChance;

    public SniperDroid(String name) {
        super(name, 80, 35, 95, 90);
        this.headshotChance = 20;
    }

    @Override
    public boolean attack(Droid target) {
        if (!isAlive || !target.isAlive) return false;

        // Снайпер завжди потрапляє
        int totalDamage = damage;

        // Перевірка на хедшот
        if (Math.random() * 100 < headshotChance) {
            totalDamage *= 3;
            System.out.println(ColorConsole.RED + "🎯 " + name + " робить ХЕДШОТ! Завдає " + totalDamage + " шкоди!" + ColorConsole.RESET);
        } else {
            System.out.println(ColorConsole.RED + "💥 " + name + " атакує " + target.name + " і завдає " + totalDamage + " шкоди!" + ColorConsole.RESET);
        }

        if (weapon != null) {
            totalDamage += weapon.useWeapon();
        }

        target.takeDamage(totalDamage);
        return true;
    }

    @Override
    public void useSpecialAbility(Droid target) {
        if (hasEnergy(40)) {
            consumeEnergy(40);
            headshotChance += 15;
            System.out.println(ColorConsole.CYAN + "👁️ " + name + " покращує приціл! Шанс хедшоту: " + headshotChance + "%" + ColorConsole.RESET);
        }
    }

    @Override
    public String getType() {
        return "Снайпер";
    }
}