import java.util.ArrayList;
import java.util.Random;

public class GooGame {

    private Player player;
    private Monster monster;
    private ArrayList<Card> deck;
    private Combat fight1;

    // TODO CARD DRAW ON DAMAGE, IMPLEMENT BOTH CODES

    public GooGame() {

        System.out.println("Beginning of game");

        player = new Player(100);

        monster = new Monster("Monster", 90, 6);

        deck = new ArrayList<Card>();
        deck.add(new Card(0));
        deck.add(new Card(0));
        deck.add(new Card(0));
        deck.add(new Card(0));
        deck.add(new Card(3));
        deck.add(new Card(1));
        deck.add(new Card(1));
        deck.add(new Card(1));
        deck.add(new Card(2));
        deck.add(new Card(2));
        fight1 = new Combat(player, monster,deck);
        fight1.Start();
    }

    public Player getPlayer()
    {
        return player;
    }
    public Monster getMonster()
    {
        return monster;
    }
    public ArrayList<Card> getDeck()
    {
        return deck;
    }
    public Combat getCombat()
    {
        return fight1;
    }
}
