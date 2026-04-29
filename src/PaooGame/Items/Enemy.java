package PaooGame.Items;

import PaooGame.Collision.Collisions;
import PaooGame.Graphics.Assets;
import PaooGame.Meniu.HealthBar;
import PaooGame.Observator.Observable;
import PaooGame.RefLinks;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Enemy extends Character {
    //private ArrayList<BufferedImage> idle=new ArrayList<>();
    private Animator animatie;
    private Hero erou;
    private boolean isthere;
    protected float yVelocity = 0;
    private final float GRAVITY = 0.5f;
    private final float MAX_FALL_SPEED = 10f;
    protected final float JUMP_FORCE = -11f;
    public boolean isJumping = false;
    private Collisions colls;
    private Rectangle enemyRect;
    private int attackcooldown=80;
    private HealthBar healthBar;
    private int frameKt = 0;


    public Enemy(RefLinks refLink, float x, float y,Hero a)
    {

        super(refLink,x,y,Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        erou=a;

        colls = new Collisions(refLink);

        enemyRect = new Rectangle((int)(a.GetX() + a.normalBounds.x), (int)(a.GetY() + a.normalBounds.y), a.normalBounds.width, a.normalBounds.height);

        this.SetLife(100);
        this.SetDamage(10);
        this.healthBar = new HealthBar(this, 100, 10);

        //animatie=new Animator(idle);
        //animatie.setSpeed(500);
        //animatie.start();
    }
    public void setAnimatie(ArrayList<BufferedImage> a)
    {
        animatie= new Animator(a);
        animatie.setSpeed(500);
        animatie.start();
    }
    public Rectangle GetenemyReact()
    {
        return enemyRect;
    }
    public void FindPlayer()
    {
        xMove=0;

        float heroX = erou.GetX();
        float heroY = erou.GetY();

        float distanceX = heroX - this.x;
        float distanceY = heroY - this.y;

        float stopDistance = 50;
        float findDistance = 240;
        if (!colls.checkTileCollision(this, xMove, 0))
        {
            if(Math.abs(distanceX) > stopDistance && Math.abs(distanceX) < findDistance)
            {
                if(Math.abs(distanceY)<150)
                {
                    if(!refLink.GetCamera().getCameraMoving()) {
                        xMove = (distanceX > 0) ? speed : -speed;
                    } else {
                        xMove = (distanceX > 0) ? -speed : speed;
                    }
                }


            }
            //if(Math.abs(distanceY) > stopDistance) {
              //  yMove = (distanceY > 0) ? speed : -speed;}

        }

        //isthere= erou.normalBounds.x<this.attackBounds.x&&erou.normalBounds.x+erou.normalBounds.width>this.attackBounds.x+this.attackBounds.width;
        /*if(erou.GetX()+erou.GetWidth()!=this.GetX()&&erou.GetX()!=this.GetX()+this.GetWidth())
        {
            if(!refLink.GetCamera().getCameraMoving())
            {
                if(this.GetX()<erou.GetX())
                {
                    xMove=speed;
                } else if (erou.GetX()<this.GetX())
                {
                    xMove=-speed;
                }
            }
            else
            {
                if(this.GetX()<erou.GetX())
                {
                    xMove=-speed;
                } else if (erou.GetX()<this.GetX())
                {
                    xMove=speed;
                }
            }
        }
        if(erou.GetY()!=this.GetY()+this.GetHeight()&&erou.GetY()+erou.GetHeight()!=this.GetY())
        {
            if(this.GetY()<erou.GetY())
            {
                yMove=speed;
            } else if (erou.GetY()<this.GetY())
            {
                yMove=-speed;
            }
        }*/
    }
    public void IsGettingAttacked()
    {
        this.life-=erou.damage;
        //System.out.println(this.life);
    }
    public void attackHero()
    {
        int attackWidth = 40;
        int attackHeight = height;

        Rectangle attackRectLeft = new Rectangle((int)(x - attackWidth), (int)y, attackWidth, attackHeight);
        Rectangle attackRectRight = new Rectangle((int)(x + width), (int)y, attackWidth, attackHeight);
        Rectangle erouRect = new Rectangle((int)(erou.GetX() + erou.normalBounds.x), (int)(erou.GetY() + erou.normalBounds.y), erou.normalBounds.width, erou.normalBounds.height);
        if (attackRectLeft.intersects(erouRect) || attackRectRight.intersects(erouRect))
        {
            erou.IsGettingAttacked(this);
        }
    }
    public void setattackcooldown(int i)
    {
        attackcooldown=i;
    }
    @Override
    public void Update() {
        frameKt++;
        yVelocity += GRAVITY; /// yVelocity este o simualre a energiei potentiale, practic o variabila care acumuleaza graviatie si accelereaza characterul spre tile-ul cu coliziune
        if (yVelocity > MAX_FALL_SPEED) {
            yVelocity = MAX_FALL_SPEED;
        }
        yMove = yVelocity;
        FindPlayer();
        if(frameKt==attackcooldown)
        {
            attackHero();
            frameKt=0;
        }

        staiAcolo();
        Move(true);
        animatie.update(System.currentTimeMillis());
    }
    public void staiAcolo()
    {
        if(refLink.GetCamera().getCameraMoving())
        {
            if(refLink.GetCamera().getCmrRight())
            {
                //xMove=-2*speed;
                xMove=-(int)refLink.GetHero().speed;
            } else if (refLink.GetCamera().getCmrLeft())
            {
                //xMove=+2*speed;
                xMove=+(int)refLink.GetHero().speed;
            }
        }


    }

    @Override
    public void Draw(Graphics g) {
        if(animatie!=null)
        {
            g.drawImage(animatie.imagine, (int) x, (int) y, width, height, null);
        }
        if(this.GetLife()!=this.GetMaxLife())
        {
            healthBar.Draw(g, (int)x, (int)y - 15);
        }

    }

    @Override
    public void MoveY() {
        if (!colls.checkTileCollision(this, 0, yMove)) {
            if (y >= 30 && y <= 1080) {
                y += yMove;
            }
//            if (y >= 952) {
//                y = 952;
//            }
//            if (y <= 80) {
//                y = 80;
//            }
        } else {
            if (yVelocity > 0) { ///  caderea s-a incheiat, characterul se afla pe o platforma cu coliziune
                isJumping = false; /// a ajuns pe pamant
                yVelocity = 0;
            }

        }
    }
}
