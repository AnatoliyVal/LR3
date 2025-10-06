package weapon;

public class HealingGun extends Weapon {
    public HealingGun() {
        super("Лікувальний пістолет", -15, 20); // Негативна шкода = лікування
    }

    @Override
    public int useWeapon() {
        if (isBroken()) return 0;
        durability--;
        return damage; // Повертає негативне значення для лікування
    }

    @Override
    public String getDescription() {
        return "Зброя підтримки, лікує союзників";
    }
}