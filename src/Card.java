import java.awt.*;

public class Card {
    private int cardType;
    private String name;
    private String desc;
    private int monsterDamage;
    private int playerDamage;
    private int heal;
    private int state; // 0 = DECK, 1 = HAND, 2 = DISCARD
    private Color color;
    private int drawCount = 0;
    Board board;


    public Card() {
        monsterDamage = 10;
        playerDamage = 0;
        heal = 0;
    }

    public String GetName()
    {
        return name;
    }

    public Color getColor()
    {
        return color;
    }

    public String getDesc()
    {
        return desc;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public void setDrawCount(int _drawCount) {
        drawCount = _drawCount;
    }

    public int getCardType() {
        return cardType;
    }

    public Card (int _cardType, Board _board) {
        cardType = _cardType;
        board = _board;
        switch (cardType) {
            case 0:
                name = "Attack";
                desc = "8 dmg";
                color = Color.red;
                break;
            case 1:
                name = "Spell";
                desc = "20 dmg, -10 hp, draw";
                color = Color.YELLOW;
                break;
            case 2:
                name = "Heal";
                desc = "Heal 6";
                color = Color.PINK;
                break;
            case 3:
                name = "Weaken";
                desc = "-4 hp, 1/2 enemy dmg";
                color = Color.gray;
                break;
        }
    }

    public void CardAction (Player _player, Monster _monster, Combat _fight) {
        switch(cardType) {
            case 0:     // Attack demo
                _monster.AdditiveChangeHealth(-8);
                System.out.println("EFFECT 0");
                _fight.setDisplayAttack(true);
                break;
            case 1:     // Spell demo
                _player.AdditiveChangeHealth(-10);
                _monster.AdditiveChangeHealth(-20);
                drawCount += 1;
                System.out.println("EFFECT 1");
                break;
            case 2:     // Heal demo
                _player.AdditiveChangeHealth(8);
                System.out.println("EFFECT 2");
                break;
            case 3:     // Weaken demo
                _monster.SetPower(_monster.GetPower() / 2);
                _player.AdditiveChangeHealth(-4);
                drawCount += 1;
                System.out.println("EFFECT 3");
                break;
            default:
                System.out.println("ERROR, INVALID CARD OBJECT");
                break;
        }
        state = 2;  // discard
    }
}
