package weapon;

public abstract class Weapon {
    protected String name;
    protected int damage;
    protected int durability;

    public Weapon(String name, int damage, int durability) {
        this.name = name;
        this.damage = damage;
        this.durability = durability;
    }

    public abstract int useWeapon();
    public abstract String getDescription();

    public String getName() { return name; }
    public boolean isBroken() { return durability <= 0; }
}