package character;

public abstract class Character implements Cloneable {
    protected String name;
    protected int health;
    protected int attackPower;
    protected int defense;

    public Character(String name, int health, int attackPower, int defense) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public abstract void performAttack(Character target) throws InterruptedException;

    public void takeDamage(int damage) {
        int damageTaken = damage - defense / 4;
        if (damageTaken < 0) damageTaken = 0;
        health -= damageTaken;
        if (health < 0) health = 0;
        System.out.println(name + " menerima " + damageTaken + " damage!");
    }

    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public String toString() {
        return name + " - Health: " + health + ", Attack: " + attackPower + ", Defense: " + defense;
    }

    public String getName() {
        return name;
    }

    @Override
    public Character clone() {
        try {
            return (Character) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
