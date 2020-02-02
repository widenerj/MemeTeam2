import java.util.Random;

public class Monster {
    private String name;
    private int health;
    private int healthMax;
    private int INIT_POWER;
    private int power;
    private int drawCount;
    private int monsterAttack;

    public Monster(String _name, int _healthMax, int _power) {
        name = _name;
        health = _healthMax;
        healthMax = _healthMax;
        INIT_POWER = _power;
        power = INIT_POWER;
    }

    public int GetHealth() {
        return health;
    }

    public int GetHealthMax() {
        return healthMax;
    }

    public int GetPower() {
        return power;
    }

    public void SetPower(int _powerChange) {
        power = _powerChange;
    }

    public void AdditiveChangeHealth(int effect) {
        health += effect;
        if (health > healthMax)
            health = healthMax;

        if (health < 0)
            health = 0;

    }

    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int _drawCount) {
        drawCount = _drawCount;
    }

    public int getMonsterAttack() {
        return monsterAttack;
    }

    public void MonsterIntent(Player _player, Combat _fight) {
        Random rand = new Random();
        monsterAttack = power + (rand.nextInt(power) / 2);
    }

    public void MonsterAttack(Player _player, Combat _fight) {
        _player.AdditiveChangeHealth(-monsterAttack);
        drawCount = 2;
        System.out.println("" + name + " attacked for " + monsterAttack + " damage!");

        power = INIT_POWER;
    }
}

