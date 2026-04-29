package PaooGame.Enemies;

import PaooGame.Collision.Collisions;
import PaooGame.Graphics.Assets;
import PaooGame.Items.Enemy;
import PaooGame.Items.Hero;
import PaooGame.RefLinks;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SpiderMan extends Enemy {
    private ArrayList<BufferedImage> idle=new ArrayList<>();
    private float inity;
    Collisions colls;
    private boolean isJumping;
    private float speed;

    public SpiderMan(RefLinks refLink, float x, float y, Hero a)
    {
        super(refLink,x,y,a);
        this.SetSpeed(1.5f);
        colls = new Collisions(refLink);
        speed=1.25f;

        normalBounds.x=10;
        normalBounds.y=16;
        normalBounds.width=64;
        normalBounds.height=60;

        this.isJumping=false;

        attackBounds.x = 0;
        attackBounds.y = 32;
        attackBounds.width = 38;
        attackBounds.height = 38;
        this.setattackcooldown(30);

        inity=y;

        idle.add(Assets.paianjen);
        this.setAnimatie(idle);
    }

    @Override
    public void FindPlayer() {
        xMove=0;
    }
    @Override
    public void MoveY() {

        if (!colls.checkTileCollision(this, 0, yMove)&&!isJumping) {
            if (y >= 30 && y <= 1080) {
                y += speed;
            }
//            if (y >= 952) {
//                y = 952;
//            }
//            if (y <= 80) {
//                y = 80;
//            }
        } else {
            if(!isJumping)
            {
                isJumping=true;
            }
            if(y>inity)
            {
                y-=speed;
            }
            if(y<=inity)
            {
                isJumping=false;
            }

        }
    }
}
