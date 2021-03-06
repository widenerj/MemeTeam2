import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Goo extends JFrame
{
    private static int monsterIntent;

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
        private static int selfDrawCount = 0;
        int monsterIntent = 0;

        @Override
        public void mousePressed(MouseEvent e)
        {
            if (game.getMonster().GetHealth() != 0) {
                if (monsterIntent == 0) {
                    game.getMonster().MonsterIntent(game.getPlayer(), game.getCombat());
                    monsterIntent = game.getMonster().getMonsterAttack();
                }

                int x = e.getX();
                int y = e.getY();
                Random rand = new Random();

                //Cycle through all valid card coordinates
                for (int i = 0; i < cardZones.size() - 3; i += 4)
                {
                    if (((x >= cardZones.get(i)) && (x <= (cardZones.get(i) + cardZones.get(i + 2)))) && ((y >= cardZones.get(i + 1)) && (y <= (cardZones.get(i + 1) + cardZones.get(i + 3))))) {
                        game.getCombat().getHand().get(i / 4).CardAction(game.getPlayer(), game.getMonster(), game.getCombat()); //Card action
                        if (game.getCombat().getHand().get(i / 4).getCardType() == 1 || game.getCombat().getHand().get(i / 4).getCardType() == 3)
                            selfDrawCount++;

                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        game.getCombat().setDisplayAttack(false);
                                        game.getCombat().setDisplaySpell(false);
                                        game.getCombat().setDisplayHeal(false);
                                        game.getCombat().setDisplayWeaken(false);
                                        board.repaint();
                                    }
                                },
                                325
                        );

                        game.getCombat().getDiscard().add(game.getCombat().getHand().get(i / 4)); //Add to discard pile
                        game.getCombat().getHand().remove(i / 4); //Remove from hand

                        cardZones.clear();
                        board.repaint();

                        int drawCount = selfDrawCount;
                        int draw;

                        if (game.getCombat().getHand().size() < 5 && drawCount > 0) {
                            if (game.getCombat().getDeckDraw().size() == 0) {
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

                            if (drawCount > game.getDeck().size()) {
                                drawCount = game.getDeck().size();
                            }

                            for (int j = 0; j < drawCount; j++) {
                                if (game.getCombat().getDeckDraw().size() == 1)
                                    draw = 0;
                                else
                                    draw = rand.nextInt(game.getCombat().getDeckDraw().size() - 1);

                                game.getCombat().getHand().add(game.getCombat().getDeckDraw().get(draw));
                                game.getCombat().getDeckDraw().remove(draw);
                                if (game.getCombat().getHand().size() == 5)
                                    break;
                            }
                        }
                        selfDrawCount = 0;
                        break;
                    }
                }

                if (((x >= 870) && (x <= (870 + 161))) && ((y >= 550) && (y <= (550 + 107)))) {
                    boolean turnActive;
                    int draw;

                    game.getMonster().MonsterAttack(game.getPlayer(), game.getCombat());
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    game.getCombat().setGooAttacked(false);
                                    board.repaint();
                                }
                            },
                            325
                    );

                    int drawCount = 2;

                    if (game.getCombat().getHand().size() < 5 && drawCount > 0) {
                        if (game.getCombat().getDeckDraw().size() == 0) {
                            //if (game.getCombat().getHand().size() == 0 && game.getCombat().getDeckDraw().size() == 0) {
                            while (game.getCombat().getDiscard().size() > 0) {
                                int temp;

                                if (game.getCombat().getDiscard().size() > 1)
                                    temp = rand.nextInt(game.getCombat().getDiscard().size() - 1);
                                else
                                    temp = 0;
                                game.getCombat().getDeckDraw().add(game.getCombat().getDiscard().get(temp));
                                game.getCombat().getDiscard().remove(temp);
                                // }
                            }
                        }

                        //System.out.println(game.getCombat().getDeckDraw().size());
                        if (drawCount > game.getDeck().size()) {
                            drawCount = game.getDeck().size();
                        }

                        for (int j = 0; j < drawCount; j++) {
                            if (game.getCombat().getDeckDraw().size() == 1)
                                draw = 0;
                            else
                                draw = rand.nextInt(game.getCombat().getDeckDraw().size() - 1);

                            game.getCombat().getHand().add(game.getCombat().getDeckDraw().get(draw));
                            game.getCombat().getDeckDraw().remove(draw);
                            if (game.getCombat().getHand().size() == 5)
                                break;
                        }

                        turnActive = true;
                    }

                    game.getMonster().MonsterIntent(game.getPlayer(), game.getCombat());
                    monsterIntent = game.getMonster().getMonsterAttack();

                    game.getMonster().setDrawCount(0);
                    selfDrawCount = 0;
                    board.repaint();
                    cardZones.clear();

                }

                if (game.getMonster().GetHealth() < 1) {
                    game.getMonster().incrementLevel();
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    game.getMonster().setHealth(game.getMonster().GetHealthMax());
                                    board.repaint();
                                }
                            },
                            3000
                    );
                }

                if (game.getPlayer().GetHealth() == 0) {
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    System.exit(0);
                                }
                            },
                            5000
                    );
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


