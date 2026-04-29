package PaooGame.Meniu;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Items.Hero;
import PaooGame.Items.ItemManager;
import PaooGame.Items.ItemType;
import PaooGame.RefLinks;

public class StatusPanel extends JPanel {
    private int health;
    private int maxHealth=200;
    private int score;
    private RefLinks refLinks;
    private BufferedImage UIimg;
    public ItemManager iteme;

    public StatusPanel(int initialHealth, int initialScore,RefLinks ref) {
        this.health = initialHealth;
        this.score = initialScore;
        refLinks=ref;
        iteme = new ItemManager(ref);
        setPreferredSize(new Dimension(800, 60));
        UIimg= ImageLoader.LoadImage("/textures/BaraGUI1920x160.png");
    }
    public void HasTakenItem()
    {
        repaint();
    }
    public void setHealth(int health) {
        this.health = health;
        repaint();
    }
    public void setScore(int score) {
        this.score =this.score+score;
        repaint();
    }
    public void UpdateHealth(int newHealth) {
            this.setHealth(newHealth);
    }
    public void UpdateItems(ItemType a)
    {
        if(a==ItemType.BOMBA||a==ItemType.BEREA_SFANTA)
            iteme.collectItemUsable(a);
        else
            iteme.collectItem(a);
        repaint();
    }
    public void Repaint()
    {
        repaint();
    }
    public void UpdateScore(int newScore)
    {
        this.setScore(newScore);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (UIimg != null) {
            g.drawImage(UIimg, 0, 0, getWidth(), getHeight(), this);
        }

       //viata
        g.setColor(Color.GRAY);
        g.fillRect(10, 10, 200, 15); // background
        g.setColor(Color.RED);
        int barWidth = (int)((health / (float)maxHealth) * 200); // 200 e lățimea totală a barei
        g.fillRect(10, 10, barWidth, 15);

        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 200, 15); // border

        //scor
        g.setColor(new Color(184, 145, 40));
        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.drawString("Lei Leuți: " + score, 230, 23);

        //sloturi in inventar
        int slotX = 400;
        for (int i = 0; i < ItemType.values().length; i++) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(slotX + i * 50, 5, 40, 40);
            g.setColor(Color.BLACK);
            g.drawRect(slotX + i * 50, 5, 40, 40);

                if(iteme.hasItem(ItemType.values()[i])!=0&&!Assets.iteme.isEmpty()) {
                    g.drawImage(Assets.iteme.get(i), slotX + i * 50 + 2, 7, 36, 36, this);
                }

    //            if (inventoryItems != null && i < inventoryItems.size() && inventoryItems.get(i) != null) {
    //                g.drawImage(inventoryItems.get(i), slotX + i * 50 + 2, 7, 36, 36, this);
    //            }
        }
    }
    public int getScore(){
        return score;
    }
    public void resetScore(){
        score=0;
    }
}
