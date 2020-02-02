import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Board extends JPanel
{
    //public static int[] hand = {1,1,1,1,1}; //TODO: Card objects NOT ints
    private Image goo;
    private Image scientist;
    private Image lab;
    private Image drawPile;
    private Image discardPile;
    private Image endTurn;
    private Image BloodAttack2;
    private Image BloodAttack;
    private Image Goo1;
    private GooGame game = new GooGame(this);
    private ArrayList<Card> hand;

    public Board()
    {
        initBoard();
    }

    private void initBoard()
    {
        loadImage();
        addMouseListener(new Goo.CardClick());
    }

    private void loadImage()
    {
        //Loads all the images
        ImageIcon i = new ImageIcon("src/gameImages/goo.png");
        goo = i.getImage();
        ImageIcon ii = new ImageIcon("src/gameImages/scientist.png");
        scientist = ii.getImage();
        ImageIcon iii = new ImageIcon("src/gameImages/lab.png");
        lab = iii.getImage();
        ImageIcon iv = new ImageIcon("src/gameImages/drawPile.png");
        drawPile = iv.getImage();
        ImageIcon v = new ImageIcon("src/gameImages/discardPile.png");
        discardPile = v.getImage();
        ImageIcon vi = new ImageIcon("src/gameImages/endme.png");
        endTurn = vi.getImage();
        ImageIcon vii = new ImageIcon("src/gameImages/BloodAttack2.png");
        BloodAttack2 = vii.getImage();
        ImageIcon viii = new ImageIcon("src/gameImages/BloodAttack.png");
        BloodAttack = viii.getImage();
        ImageIcon ix = new ImageIcon("src/gameImages/Goo1.png");
        Goo1 = ix.getImage();

    }

    @Override
    public void paintComponent(Graphics g)
    {
        drawBackground(g); //Background elements
        drawNumbers(g); //Number elements
        drawCards(g); //Draw the cards
    }

    public void drawBackground(Graphics g)
    {
        //Static background objects
        g.drawImage(lab,0,-100,null);
        g.drawImage(goo,200,270,null);
        if (game.getMonster().GetHealth() != 0)
        {
            g.drawImage(scientist,550,250,null);
        }
        else
        {
            g.drawImage(BloodAttack,550,250,null);
        }
        g.drawImage(drawPile,50,450,null);
        g.drawImage(discardPile,900,450,null);
        g.drawImage(endTurn,870,550,null);
        if (game.getCombat().getDisplayAttack()) {
            g.drawImage(BloodAttack2, 550, 250, null);
        }
        if (game.getCombat().getDisplaySpell()) {
            g.drawImage(Goo1, 635, 300, null);
        }

        //All of this does the green box
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D bigCardBox = new Rectangle2D.Double(170,440,700,200);
        g.setColor(Color.green);
        g2d.fill(bigCardBox);
        g2d.draw(bigCardBox);
        g.setColor(Color.black);

        //Card slots
        for (int i=0;i<5;i++)
        {
            Rectangle2D littleCardBox = new Rectangle2D.Double(190 + (136 * i),460,116,160);
            g2d.fill(littleCardBox);
            g2d.draw(littleCardBox);
        }

        //End turn
    }

    public void drawNumbers(Graphics g)
    {
        //TODO: Make values dynamic
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(new Font("default",Font.BOLD,16));

        g2d.drawString("Goo Health: " + game.getPlayer().GetHealth() + "/" + game.getPlayer().GetHealthMax() ,250,250);
        if (game.getMonster().GetHealth() != 0)
        {
            g2d.drawString("Scientist Health: " + game.getMonster().GetHealth() + "/" + game.getMonster().GetHealthMax(),600,250);
            g2d.drawString("Dealing " + game.getMonster().getMonsterAttack() + " damage", 600, 150);
        }

        g2d.drawString(Integer.toString(game.getCombat().getDeckDraw().size()),92,505);
        g2d.drawString(Integer.toString(game.getCombat().getDiscard().size()),942,505);
    }

    public void drawCards(Graphics g)
    {
        //TODO: Fill handCardArray with cards in hand

        Graphics2D g2d = (Graphics2D) g;
        hand = game.getCombat().getHand();
        for (int i=0;i<hand.size();i++)
        {
            if (hand.get(i) != null) //TODO: null not 0 when its card objects
            {
                g.setColor(hand.get(i).getColor());
                Rectangle2D littleCardBox = new Rectangle2D.Double(190 + (136 * i),460,116,160);
                g2d.fill(littleCardBox);
                g2d.draw(littleCardBox);
                g.setColor(Color.BLACK);

                g.setFont(new Font("default",Font.BOLD,16));
                g2d.drawString(hand.get(i).GetName(),200 + (136 * i), 500);
                g.setFont(new Font("default2",Font.PLAIN,10));
                g2d.drawString(hand.get(i).getDesc(),200 + (136 * i),550);

                int x = 190 + (136 * i);
                int y = 460;
                int w = 116;
                int h = 160;
                Goo.CardClick.receiveCardAreas(x,y,w,h,game); //Send valid coordinates
            }
        }
    }
}
