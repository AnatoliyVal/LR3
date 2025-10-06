package droid;

import weapon.Weapon;
import util.ColorConsole;

public abstract class Droid {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int damage;
    protected int accuracy;
    protected int energy;
    protected int maxEnergy;
    protected Weapon weapon;
    protected boolean isAlive;

    public Droid(String name, int health, int damage, int accuracy, int energy) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.accuracy = accuracy;
        this.energy = energy;
        this.maxEnergy = energy;
        this.isAlive = true;
    }

    public abstract void useSpecialAbility(Droid target);
    public abstract String getType();

    public boolean attack(Droid target) {
        if (!isAlive || !target.isAlive) return false;

        // Перевірка точності
        if (Math.random() * 100 > accuracy) {
            System.out.println(ColorConsole.YELLOW + "❌ " + name + " промахнувся!" + ColorConsole.RESET);
            return false;
        }

        int totalDamage = damage;
        if (weapon != null) {
            totalDamage += weapon.useWeapon();
        }

        target.takeDamage(totalDamage);
        System.out.println(ColorConsole.RED + "💥 " + name + " атакує " + target.name + " і завдає " + totalDamage + " шкоди!" + ColorConsole.RESET);

        return true;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            isAlive = false;
            System.out.println(ColorConsole.RED + "💀 " + name + " знищено!" + ColorConsole.RESET);
        }
    }

    public void regenerate() {
        if (isAlive) {
            int healAmount = maxHealth / 10;
            health = Math.min(health + healAmount, maxHealth);
            energy = Math.min(energy + 20, maxEnergy);
            System.out.println(ColorConsole.GREEN + "🔋 " + name + " відновив " + healAmount + " здоров'я" + ColorConsole.RESET);
        }
    }

    public boolean hasEnergy(int cost) {
        return energy >= cost;
    }

    public void consumeEnergy(int amount) {
        energy -= amount;
    }

    // Гетери та сетери
    public String getName() { return name; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getDamage() { return damage; }
    public int getAccuracy() { return accuracy; }
    public int getEnergy() { return energy; }
    public boolean isAlive() { return isAlive; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    @Override
    public String toString() {
        return String.format("%s [%s] ❤%d/%d ⚡%d/%d 💥%d 🎯%d%% %s",
                name, getType(), health, maxHealth, energy, maxEnergy, damage, accuracy,
                isAlive ? "✅" : "💀");
    }
}