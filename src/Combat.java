import java.util.ArrayList;
import java.util.Random;
import java.util.Date;
import java.util.Calendar;

public class Combat
{
    private Player player;
    private Monster monster;
    ArrayList<Card> deck;
    private boolean turnActive = false;
    private int drawCount = 0;
    private ArrayList<Card> hand;
    private ArrayList<Card> discard;
    private int monsterIntent;
    Board board;

    ///

    private boolean displayAttack = false;
    public void setDisplayAttack(boolean _displayAttack) {
        displayAttack = _displayAttack;
    }
    public boolean getDisplayAttack() {
        return displayAttack;
    }

    private boolean displaySpell = false;
    public void setDisplaySpell(boolean _displaySpell) {
        displaySpell = _displaySpell;
    }
    public boolean getDisplaySpell() {
        return displaySpell;
    }

    private boolean displayHeal = false;
    public void setDisplayHeal(boolean _displayHeal) {
        displayHeal = _displayHeal;
    }
    public boolean getDisplayHeal() {
        return displayHeal;
    }

    private boolean displayWeaken = false;
    public void setDisplayWeaken(boolean _displayWeaken) {
        displayWeaken = _displayWeaken;
    }
    public boolean getDisplayWeaken() {
        return displayWeaken;
    }


    private boolean displayGooAttacked = false;
    public void setGooAttacked(boolean _displayGooAttacked) {
        displayGooAttacked = _displayGooAttacked;
    }
    public boolean getDisplayGooAttacked() {
        return displayGooAttacked;
    }

    ///

    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public ArrayList<Card> getDiscard()
    {
        return discard;
    }

    public ArrayList<Card> getDeckDraw()
    {
        return deck;
    }

    public int getMonsterIntent() {
        return monsterIntent;
    }

    public Combat(Player _player, Monster _monster, ArrayList<Card> _deck, Board _board)
    {
        player = _player;
        monster = _monster;
        deck = _deck;
    }

    public int GetDrawCount()
    {
        return drawCount;
    }

    public void SetDrawCount(int _drawCount)
    {
        drawCount = _drawCount;
    }

    public void Start()
    {
        Random rand = new Random();

        int userInt;
        int turnCount;
        int draw;

        hand = new ArrayList<Card>();
        discard = new ArrayList<Card>();

        drawCount = 3;
        for (int i = 0; i < drawCount; i++) {
            if (deck.size() == 1)
                draw = 0;
            else {
                draw = rand.nextInt(deck.size() - 1);
            }

            hand.add(deck.get(draw));
            deck.remove(draw);
            if (hand.size() == 5)
                break;
        }

        // Initialize all values
        // Paste turn 1 start
        turnCount = 1;
        System.out.println("#################### TURN " + turnCount + " ########################################");

        monsterIntent = monster.getMonsterAttack();
        System.out.println("-----------" + monsterIntent);
    }
}
