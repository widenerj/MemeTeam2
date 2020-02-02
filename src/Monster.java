import java.util.Random;

public class Monster {
    private String name;
    private int health;
    private int healthMax;
    private int INIT_POWER;
    private int power;
    private int drawCount;
    private int monsterAttack;

    private int level = 0;

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

    public int GetLevel() {
        return level;
    }

    public void incrementLevel() {
        level++;
        healthMax += level * 25;
        //health = healthMax;
        INIT_POWER += 2;
        power = INIT_POWER;
        System.out.println(healthMax + " " + health + " " + power);
    }

    public void AdditiveChangeHealth(int effect) {
        health += effect;
        if (health > healthMax)
            health = healthMax;

        if (health < 0)
            health = 0;

    }

    public void setHealth(int _health) {
        health = _health;
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
        System.out.println("++++++++++" + power);
    }

    public void MonsterAttack(Player _player, Combat _fight) {
        if (health != 0)
        {
            _player.AdditiveChangeHealth(-monsterAttack);
            drawCount = 2;
            System.out.println("" + name + " attacked for " + monsterAttack + " damage!");
        }

        _fight.setGooAttacked(true);

        power = INIT_POWER;
    }
}

