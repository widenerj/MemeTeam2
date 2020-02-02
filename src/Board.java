import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Board extends JPanel
{
    private Image goo;
    private Image scientist;
    private Image lab;
    private Image drawPile;
    private Image discardPile;
    private Image endTurn;
    private Image BloodAttack2;
    private Image BloodAttack;
    private Image Goo1;
    private Image Heal1;
    private Image GooProjectile;
    private Image gooAttacked;
    private GooGame game = new GooGame(this);
    private ArrayList<Card> hand;

    private Image Sci1;
    private Image Sci2;
    private Image Sci3;
    private Image Sci4;

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
        ImageIcon x = new ImageIcon("src/gameImages/Heal1.png");
        Heal1 = x.getImage();
        ImageIcon xi = new ImageIcon("src/gameImages/GooProjectile.png");
        GooProjectile = xi.getImage();
        ImageIcon xii = new ImageIcon("src/gameImages/gooAttacked.png");
        gooAttacked = xii.getImage();

        ImageIcon sci1 = new ImageIcon("src/gameImages/scientist2.png");
        Sci1 = sci1.getImage();
        ImageIcon sci2 = new ImageIcon("src/gameImages/scientist3.png");
        Sci2 = sci2.getImage();
        ImageIcon sci3 = new ImageIcon("src/gameImages/scientist4.png");
        Sci3 = sci3.getImage();
        ImageIcon sci4 = new ImageIcon("src/gameImages/scientist5.png");
        Sci4 = sci4.getImage();





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
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(lab,0,-100,null);
        if (game.getPlayer().GetHealth() != 0)
        {
            if (!game.getCombat().getDisplayGooAttacked()) {
                g.drawImage(goo, 200, 270, null);
            }
            else {
                g.drawImage(gooAttacked, 200, 270, null);
            }
        }
        else
        {
            g.setFont(new Font("default",Font.BOLD,100));
            g2d.drawString("GAME OVER",200,200);
        }

        if (game.getMonster().GetHealth() != 0)
        {
            switch (game.getMonster().GetLevel()) {
                case 0:
                    g.drawImage(scientist, 550, 250, null);
                    break;
                case 1:
                    g.drawImage(Sci1, 550, 250, null);
                    break;
                case 2:
                    g.drawImage(Sci2, 550, 250, null);
                    break;
                case 3:
                    g.drawImage(Sci3, 550, 250, null);
                    break;
                case 4:
                    g.drawImage(Sci4, 550, 250, null);
                    break;
                default:
            }
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
            g.drawImage(GooProjectile, 490, 250, null);
        }
        if (game.getCombat().getDisplayHeal()) {
            g.drawImage(Heal1, 200, 270, null);
        }
        if (game.getCombat().getDisplayWeaken()) {
            g.drawImage(Goo1, 635, 300, null);
    }

        //All of this does the green box
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

        if (game.getPlayer().GetHealth() != 0)
        {
            g2d.drawString("Goo Health: " + game.getPlayer().GetHealth() + "/" + game.getPlayer().GetHealthMax() ,250,250);
        }
        if (game.getMonster().GetHealth() != 0)
        {
            g2d.drawString("Scientist Health: " + game.getMonster().GetHealth() + "/" + game.getMonster().GetHealthMax(),600,250);
            g2d.drawString("Dealing " + game.getMonster().getMonsterAttack() + " damage", 600, 230);
        }

        g2d.drawString(Integer.toString(game.getCombat().getDeckDraw().size()),92,505);
        g2d.drawString(Integer.toString(game.getCombat().getDiscard().size()),942,505);
    }

    public void drawCards(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        if (game.getPlayer().GetHealth() != 0)
        {
            hand = game.getCombat().getHand();
            for (int i=0;i<hand.size();i++)
            {
                if (hand.get(i) != null)
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
        else
        {

        }
    }
}
