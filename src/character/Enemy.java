package character;

public class Enemy extends Character {
    public Enemy(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    @Override
    public void performAttack(Character target) {
        System.out.println(name + " menyerang " + target.getName());
        target.takeDamage(attackPower);
    }
}
