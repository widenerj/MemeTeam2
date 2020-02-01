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
    private int drawCount = 3;
    private ArrayList<Card> hand;
    private ArrayList<Card> discard;

    public ArrayList<Card> getHand()
    {
        return hand;
    }
    public ArrayList<Card> getDiscard()
    {
        return discard;
    }

    public Combat(Player _player, Monster _monster, ArrayList<Card> _deck)
    {
        player = _player;
        monster = _monster;
        deck = _deck;
    }

    public boolean GetDrawCount()
    {
        return turnActive;
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

        while (turnCount == 100) //
        {
            System.out.println("USER HEALTH: " + player.GetHealth() + "          MONSTER HEALTH: " + monster.GetHealth());

            if (hand.size() < 5 && drawCount > 0) {
                if (deck.size() == 0) {
                    if (hand.size() == 0 && deck.size() == 0) {
                        while (discard.size() > 0) {
                            int temp;

                            if (discard.size() > 1)
                                temp = rand.nextInt(discard.size() - 1);
                            else
                                temp = 0;
                            deck.add(discard.get(temp));
                            discard.remove(temp);
                        }
                    }
                }

                draw = 0;

                for (int i = 0; i < drawCount; i++) {
                    if (deck.size() == 1)
                        draw = 0;
                    else
                        draw = rand.nextInt(deck.size() - 1);

                    hand.add(deck.get(draw));
                    deck.remove(draw);
                    if (hand.size() == 5)
                        break;
                }

                turnActive = true;

                drawCount = 0;
            }

            // Fetch cards, print to user
            if (turnActive) {
                System.out.println("Cards: (5 to end turn)     | " + deck.size() + " cards left in deck     | " + discard.size() + " cards in discard");
                for (int i = 0; i < hand.size(); i++) {
                    System.out.print(i + ") " + hand.get(i).GetName());
                    if (i < hand.size() - 1)
                        System.out.print("   |   ");
                }
                System.out.println("");
            }

            // try to catch any false inputs
            try {

                userInt = 0; //scan.nextInt();

                // if hand is empty or user ends turn (5), end turn
                if (hand.size() == 0 || userInt == 5) {
                    turnCount++;
                    System.out.println("########## TURN " + turnCount + " ########################################");
                    monster.MonsterAttack(player, this);

                    turnActive = false;
                }

                // if user enters valid card, use card, discard
                else if (userInt <= hand.size()) {
                    hand.get(userInt).CardAction(player, monster, this);  // use card
                    discard.add(hand.get(userInt));                 // discard
                    hand.remove(userInt);                           // remove from hand

                    // check is hand was emptied, if so end turn
                    if (hand.size() == 0 && drawCount == 0) {
                        turnCount++;
                        System.out.println("########## TURN " + turnCount + " ########################################");
                        monster.MonsterAttack(player, this);

                        turnActive = false;
                    }
                }

                // invalid card MIGHT BE IRRELEVANT
                else
                    System.out.println("No such card exists, try a different input");
            }
            // invalid input
            catch(Exception e) {
                System.out.println("##### NO SUCH CARD EXISTS! Please select one of the given values above\n");
            }

            // if somebody dies, end game
            if (player.GetHealth() < 1 || monster.GetHealth() < 1) {
                System.out.println("GAME TERMINATED");
                break;
            }
        }
    }
}
