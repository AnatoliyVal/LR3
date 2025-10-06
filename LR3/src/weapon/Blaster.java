package weapon;

public class Blaster extends Weapon {
    public Blaster() {
        super("Бластер", 10, 50);
    }

    @Override
    public int useWeapon() {
        if (isBroken()) return 0;
        durability--;
        return damage;
    }

    @Override
    public String getDescription() {
        return "Стандартний бластер - надійна зброя";
    }
}