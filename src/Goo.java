import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Goo extends JFrame
{
    private static int drawCount = 0;
    private boolean onTitleScreen = true;
    private static JPanel board;
    private static JPanel titleScreen;

    public Goo()
    {
        initUI();
    }

    private void initUI()
    {
        board = new Board();
        add(board);
        setResizable(false);
        setSize(1024,690);
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
            Random rand = new Random();

            //Cycle through all valid card coordinates
            for (int i=0;i<cardZones.size()-3;i+=4) //TODO: Array List out of bounds
            {
                if (((x >= cardZones.get(i)) && (x <= (cardZones.get(i) + cardZones.get(i+2)))) && ((y >= cardZones.get(i+1)) && (y <= (cardZones.get(i+1) + cardZones.get(i+3)))))
                {
                    //TODO: Activate card effect
                    System.out.println(cardZones.size());
                    game.getCombat().getHand().get(i / 4).CardAction(game.getPlayer(),game.getMonster(),game.getCombat()); //Card action
                    game.getCombat().getDiscard().add(game.getCombat().getHand().get(i / 4)); //Add to discard pile
                    game.getCombat().getHand().remove(i / 4); //Remove from hand



                    System.out.println("Valid card clicked");

                    cardZones.clear();
                    board.repaint();
                    //TODO: Remove card from hand list, clear coordinates, and sort hand
                    break;
                }
            }

            if (((x >= 870) && (x <= (870 + 161))) && ((y >= 550) && (y <= (550 + 107))))
            {
                System.out.println("You ended your turn!");

                boolean turnActive;
                int draw;

                if (game.getCombat().getHand().size() < 5 && drawCount > 0) {
                    if (game.getCombat().getDeckDraw().size() == 0) {
                        if (game.getCombat().getHand().size() == 0 && game.getCombat().getDeckDraw().size() == 0) {
                            while (game.getCombat().getDiscard().size() > 0) {
                                int temp;

                                if (game.getCombat().getDiscard().size() > 1)
                                    temp = rand.nextInt(game.getCombat().getDiscard().size() - 1);
                                else
                                    temp = 0;
                                game.getCombat().getDeckDraw().add(game.getCombat().getDiscard().get(temp));
                                game.getCombat().getDiscard().remove(temp);
                            }
                        }
                    }

                    for (int j = 0; j < drawCount; j++) {
                        if (game.getCombat().getDeckDraw().size() == 1)
                            draw = 0;
                        else
                            draw = rand.nextInt(game.getCombat().getDeckDraw().size() - 1);

                        hand.add(game.getCombat().getDeckDraw().get(draw));
                        game.getCombat().getDeckDraw().remove(draw);
                        if (hand.size() == 5)
                            break;
                    }

                    turnActive = true;

                    drawCount = 0;
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


