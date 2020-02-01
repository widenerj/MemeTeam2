import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Goo extends JFrame
{
    private static JPanel board;

    public Goo()
    {
        initUI();
    }

    private void initUI()
    {
        board = new Board();
        add(board);
        setResizable(false);
        setSize(1024,768);
        setTitle("Goo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            Goo ex = new Goo();
            ex.setVisible(true);
        });
    }

    public static class CardClick extends MouseAdapter
    {
        //ArrayList for valid card coordinates
        private static ArrayList<Integer> cardZones = new ArrayList<>(20);
        private static ArrayList<Card> hand = new ArrayList<>();
        private static GooGame game;

        @Override
        public void mousePressed(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();

            //Cycle through all valid card coordinates
            for (int i=0;i<cardZones.size()-3;i+=4) //TODO: Array List out of bounds
            {
                if (((x >= cardZones.get(i)) && (x <= (cardZones.get(i) + cardZones.get(i+2)))) && ((y >= cardZones.get(i+1)) && (y <= (cardZones.get(i+1) + cardZones.get(i+3)))))
                {
                    //TODO: Activate card effect
                    System.out.println(cardZones.size());
                    game.getCombat().getHand().get(i / 4).CardAction(game.getPlayer(),game.getMonster(),game.getCombat());



                    System.out.println("Valid card clicked");

                    cardZones.clear();
                    board.repaint();
                    //TODO: Remove card from hand list, clear coordinates, and sort hand
                    break;
                }
            }
        }

        public static void receiveCardAreas(int x, int y, int w, int h, GooGame _game)
        {
            //Receives coordinates of valid cards
            game = _game;
            cardZones.add(x);
            cardZones.add(y);
            cardZones.add(w);
            cardZones.add(h);
        }
    }
}


