package PaooGame.Enemies;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Enemy;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Set;

public class Regina extends Enemy {
    private ArrayList<BufferedImage> idle = new ArrayList<>();
    private int offset = 0;
    private boolean movingLeft = true;
    private BufferedImage attackim;
    int NuVremInstaKill=0;

    public Regina(RefLinks refLinks, float x, float y, Hero a) {
        super(refLinks, x, y, a);
        this.SetSpeed(0);
        this.SetLife(50);
        this.SetDamage(20);
        normalBounds.x = 10;
        normalBounds.y = 16;
        normalBounds.width = 64;
        normalBounds.height = 60;

        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 40;
        attackBounds.height = 40;

        this.setattackcooldown(5);
        idle.add(Assets.regina);
        this.setAnimatie(idle);
        attackim=Assets.fireball;
    }

    @Override
    public void FindPlayer() {
        if(!refLink.GetCamera().getCameraMoving())
        {
            xMove=0;
            yMove=0;
        }
    }
    @Override
    public void attackHero() {
        if (!movingLeft) {
            offset -= 10;
            if (offset <= -300) {
                movingLeft = true;
            }
        } else {
            offset = 0;
            movingLeft=false;
        }
        NuVremInstaKill++;
        int attackX = (int) (x + attackBounds.x + offset);
        int attackY = (int) (y + attackBounds.y);
        Rectangle attackRect = new Rectangle(attackX, attackY, attackBounds.width, attackBounds.height);

        Rectangle heroRect = new Rectangle((int) (refLink.GetHero().GetX() + refLink.GetHero().RetNormalBoundsX()),(int) (refLink.GetHero().GetY() + refLink.GetHero().RetNormalBoundsY()),
                refLink.GetHero().RetNormalBoundsW(),refLink.GetHero().RetNormalBoundsH());

        if (attackRect.intersects(heroRect)&&NuVremInstaKill>=40)
        {
            NuVremInstaKill=0;
            refLink.GetHero().IsGettingAttacked(this);
        }
    }
    @Override
    public void Draw(Graphics g)
    {
        super.Draw(g);

            g.drawImage(attackim,(int)(x + attackBounds.x+offset),(int)(y + attackBounds.y),attackBounds.width,attackBounds.height,null);
            //g.setColor(Color.blue);
            //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
            //g.setColor(Color.RED);
            //g.fillRect((int)(x+attackBounds.x),(int)(y + attackBounds.y), attackBounds.width, attackBounds.height);

    }
}
