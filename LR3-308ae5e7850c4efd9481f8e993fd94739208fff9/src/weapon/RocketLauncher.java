package weapon;

public class RocketLauncher extends Weapon {
    public RocketLauncher() {
        super("Ракетниця", 25, 10);
    }

    @Override
    public int useWeapon() {
        if (isBroken()) return 0;
        durability--;
        // Шанс подвійної шкоди
        if (Math.random() < 0.3) {
            return damage * 2;
        }
        return damage;
    }

    @Override
    public String getDescription() {
        return "Потужна ракетниця з шансом подвійної шкоди";
    }
}