package PaooGame.Enemies;

import PaooGame.Graphics.Assets;
import PaooGame.Items.Enemy;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Demon extends Enemy {
    private ArrayList<BufferedImage> idle=new ArrayList<>();

    public Demon(RefLinks refLink, float x, float y, Hero a)
    {
        super(refLink,x,y,a);
        this.SetSpeed(1.5f);

        normalBounds.x=10;
        normalBounds.y=16;
        normalBounds.width=64;
        normalBounds.height=60;

        attackBounds.x = 0;
        attackBounds.y = 32;
        attackBounds.width = 38;
        attackBounds.height = 38;
        this.setattackcooldown(40);

        idle.addAll(Assets.demon);
        this.setAnimatie(idle);
    }
}
