package PaooGame.Items;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import PaooGame.Meniu.*;
import PaooGame.RefLinks;
import PaooGame.Graphics.Assets;
import PaooGame.Collision.*;


/*! \class public class Hero extends Character
    \brief Implementeaza notiunea de erou/player (caracterul controlat de jucator).

    Elementele suplimentare pe care le aduce fata de clasa de baza sunt:
        imaginea (acest atribut poate fi ridicat si in clasa de baza)
        deplasarea
        atacul (nu este implementat momentan)
        dreptunghiul de coliziune
 */

public class Hero extends Character {

    private ArrayList<BufferedImage> imageleft = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> imageright = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> imagerunR = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> imagerunL = new ArrayList<BufferedImage>();
    private ArrayList<BufferedImage> attackright =new ArrayList<>();
    private ArrayList<BufferedImage> attackleft=new ArrayList<>();
    private Animator animatie;
    private Animator animLeft, animRight, animrunR, animrunL,animAtR,animAtL;
    public boolean nokeypressed;
    private boolean attacking;

    private Collisions colls;
    private float yVelocity = 0;
    private final float GRAVITY = 0.5f;
    private final float MAX_FALL_SPEED = 10f;
    private final float JUMP_FORCE = -11f;
    private boolean isJumping = false;
    private boolean isSwimming = false;
    private boolean isClimbing = false;
    private boolean inWeb = false;
    private int frameKt = 0;
    private int attackcooldown=25;
    public boolean canMove=true;
    public boolean canTalkToZana=false;
    public boolean LavaDmages=true;
    public boolean itemtaken=false;

    public boolean finalJ=false;


    private ArrayList<Enemy> enemy = new ArrayList<>();

    //private BufferedImage [] image=new BufferedImage[3];/*!< Referinta catre imaginea curenta a eroului.*/

    public static boolean running = false;
    public static final int N = 669;
    private boolean facingRight = true;


    /*! \fn public Hero(RefLinks refLink, float x, float y)
        \brief Constructorul de initializare al clasei Hero.

        \param refLink Referinta catre obiectul shortcut (obiect ce retine o serie de referinte din program).
        \param x Pozitia initiala pe axa X a eroului.
        \param y Pozitia initiala pe axa Y a eroului.
     */
    public Hero(RefLinks refLink, float x, float y) {
        ///Apel al constructorului clasei de baza
        super(refLink, x, y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        refLink.SetHero(this);
        colls = new Collisions(refLink);
        ///Seteaza imaginea de start a eroului
        imageleft.addAll(Assets.heroLeft);
        imageright.addAll(Assets.heroRight);
        imagerunL.addAll(Assets.herorunLeft);
        imagerunR.addAll(Assets.herorunRight);
        attackright.addAll(Assets.heroattackright);
        attackleft.addAll(Assets.heroattackleft);
        //image[0] = Assets.heroLeft[0];
        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea implicita(normala)
        normalBounds.x = 12;
        normalBounds.y = 0;
        normalBounds.width = 62;
        normalBounds.height = 72;
        attacking=false;

        this.SetDamage(20);
        this.SetLife(200);
        //this.healthBar = new HealthBar(this, 100, 10);

        ///Stabilieste pozitia relativa si dimensiunea dreptunghiului de coliziune, starea de atac
        attackBounds.x = 30;
        attackBounds.y = 16;
        attackBounds.width = 38;
        attackBounds.height = 38;
        animLeft = new Animator(imageleft);
        animRight = new Animator(imageright);
        animrunL = new Animator(imagerunL);
        animrunR = new Animator(imagerunR);
        animAtR=new Animator(attackright);
        animAtL=new Animator(attackleft);
        //animAtL.SetArray(Assets.heroattackleft);

        animLeft.setSpeed(N);
        animRight.setSpeed(N);
        animAtR.setSpeed(100);
        animAtL.setSpeed(100);
        animrunR.setSpeed(269);
        animrunL.setSpeed(269);

        animatie = animRight;
        animatie.start();
    }
    public int RetNormalBoundsX(){return normalBounds.x;}
    public int RetNormalBoundsY(){return normalBounds.y;}
    public int RetNormalBoundsW(){return normalBounds.width;}
    public int RetNormalBoundsH(){return normalBounds.height;}
    public void addObserver(Enemy a) {
        this.enemy.add(a);
    }
    public void addAllObservers(ArrayList<Enemy> a)
    {
        if(!this.enemy.isEmpty())
        {
            this.removeAllObservers();
        }
        this.enemy=a;
    }
    public void removeObserver(Enemy a) {
        this.enemy.remove(a);
    }
    public void removeAllObservers()
    {
        this.enemy.removeAll(enemy);
    }
    public boolean getAttacking()
    {
        return attacking;
    }

    public void Attack()
    {
        if(animatie != animAtR && animatie != animAtL)
        {
//            int attackWidth = 40;
//            int attackHeight = height;
//
//            Rectangle attackRectLeft = new Rectangle((int)(x - attackWidth), (int)y, attackWidth, attackHeight);
//            Rectangle attackRectRight = new Rectangle((int)(x + width), (int)y, attackWidth, attackHeight);
//
//            for (Enemy a : this.enemy)
//            {
//                Rectangle enemyRect = new Rectangle((int)(a.GetX() + a.normalBounds.x), (int)(a.GetY() + a.normalBounds.y), a.normalBounds.width, a.normalBounds.height);
//
//                if (attackRectLeft.intersects(enemyRect) || attackRectRight.intersects(enemyRect))
//                {
//                    a.IsGettingAttacked();
//                    //a.setattackcooldown();
//                }
//            }
            if(facingRight)
                animatie = animAtR;
            else
                animatie=animAtL;
            animatie.start();
        }
        if(frameKt>=attackcooldown)
        {
            int attackWidth = 40;
            int attackHeight = height;

            Rectangle attackRectLeft = new Rectangle((int)(x - attackWidth), (int)y, attackWidth, attackHeight);
            Rectangle attackRectRight = new Rectangle((int)(x + width), (int)y, attackWidth, attackHeight);

            for (Enemy a : this.enemy)
            {
                Rectangle enemyRect = new Rectangle((int)(a.GetX() + a.normalBounds.x), (int)(a.GetY() + a.normalBounds.y), a.normalBounds.width, a.normalBounds.height);

                if (attackRectLeft.intersects(enemyRect) || attackRectRight.intersects(enemyRect))
                {
                    a.IsGettingAttacked();
                    //a.setattackcooldown();
                }
            }
            frameKt=0;
        }
    }
    public void IsGettingAttacked(Enemy a)
    {
        this.life-=a.damage;
        refLink.getStatusPanel().UpdateHealth(this.life);
        System.out.println(this.life);
    }
    public void DamageAll(int damage)
    {
        for(Enemy a:this.enemy)
        {
            a.SetLife(a.GetLife()-damage);
        }
    }
    public void TakeDamage(int damage)
    {
        this.life-=damage;
        refLink.getStatusPanel().UpdateHealth(this.life);
        System.out.println(this.life);
    }
    /*! \fn public void Update()
        \brief Actualizeaza pozitia si imaginea eroului.
     */
    public void UpdateAll()
    {
        for(Enemy a : enemy)
        {
            if(a.GetLife()>0)
            {
                a.Update();
            }
            else
            {
                if(a.GetLife()==0)
                {
                    refLink.getStatusPanel().UpdateScore(13);
                    a.SetLife(-1);
                }

                //enemy.remove(a);
            }
        }
    }
    public void DrawAll(Graphics g)
    {
        for(Enemy a : enemy)
        {
            if(a.GetLife()>0)
            {
                a.Draw(g);
            }
        }
    }
    @Override
    public void Update() {
        ///Verifica daca a fost apasata o tasta
        GetInput();
        frameKt++;
        ///Actualizeaza pozitia
        refLink.GetItemManager().applyPassiveEffects(this);
        if(itemtaken)
            refLink.getStatusPanel().HasTakenItem();
        if(refLink.GetMap().getId()==2 && !enemy.isEmpty()){
            speed=3f;
        }
        else
            speed=Character.DEFAULT_SPEED;


        yVelocity += GRAVITY; /// yVelocity este o simualre a energiei potentiale, practic o variabila care acumuleaza graviatie si accelereaza characterul spre tile-ul cu coliziune
        if (yVelocity > MAX_FALL_SPEED) {
            yVelocity = MAX_FALL_SPEED;
        }
        yMove = yVelocity; /// toata energia potentiala se trimite prin parametrul yMove in MoveY()
        Move(canMove);
        ///Actualizeaza imaginea
        nokeypressed = !refLink.GetKeyManager().up && !refLink.GetKeyManager().down && !refLink.GetKeyManager().left && !refLink.GetKeyManager().right;
        if(refLink.GetKeyManager().attack)
        {
            canMove=false;
            Attack();

            if(animatie.getCurentframe() >= animatie.frames.size())
            {
                refLink.GetKeyManager().attack = false;
                if(facingRight) animatie = animRight;
                else animatie = animLeft;
                animatie.start();
            }

        }
        else if (refLink.GetKeyManager().right) {
            canMove=true;
            if (animatie != animrunR) {
                animatie = animrunR;
                running = true;
                animatie.start();
            }
            facingRight = true;
        } else if (refLink.GetKeyManager().left) {
            canMove=true;
            if (animatie != animrunL) {
                animatie = animrunL;
                running = true;
                animatie.start();
            }
            facingRight = false;
        } else if (nokeypressed) {
            if (facingRight) {
                if (animatie != animRight) {
                    animatie = animRight;
                    animatie.start();
                }
            } else {
                if (animatie != animLeft) {
                    animatie = animLeft;
                    animatie.start();
                }
            }
        }
        animatie.update(System.currentTimeMillis());

        if(colls.checkSpikeCollision(this,0,yMove)){
            frameKt++;
            if(frameKt>=200){
                life-=3;
                System.out.println(this.life);
                frameKt=0;
                refLink.getStatusPanel().UpdateHealth(this.life);

            }
        }
        if(colls.checkLavaCollision(this,0,yMove)&&LavaDmages){
            frameKt++;
            if(frameKt>=150){
                life-=5;
                System.out.println(this.life);
                frameKt=0;
                refLink.getStatusPanel().UpdateHealth(this.life);

            }
        }


    }

    /*! \fn private void GetInput()
        \brief Verifica daca a fost apasata o tasta din cele stabilite pentru controlul eroului.
     */
    private void GetInput() {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        //colls.CheckPoz(this);


        if(!refLink.GetKeyManager().attack )
        {

            if (refLink.GetKeyManager().jump && !isJumping && !inWeb) {
                yVelocity = JUMP_FORCE;
                isJumping = true;
            }
            ///Verificare apasare tasta "left"
            if (refLink.GetKeyManager().left) {
                xMove = -speed;
            }
            ///Verificare apasare tasta "dreapta"
            if (refLink.GetKeyManager().right) {
                xMove = speed;
            }
            if (refLink.GetKeyManager().up && colls.checkVineCollision(this,0,yMove)) {
                yVelocity = -3f;
                isClimbing = true;
            }
            else {
                isClimbing = false;
            }
        }
        if(refLink.GetKeyManager().item1)
        {
            refLink.GetItemManager().activateItem(ItemType.BEREA_SFANTA,this);
        }
        if(refLink.GetKeyManager().item2)
        {
            refLink.GetItemManager().activateItem(ItemType.BOMBA,this);
        }

    }

    public void resetPoz()
    {
        this.x=128;
        this.y=806;
    }

    /*! \fn public void Draw(Graphics g)
        \brief Randeaza/deseneaza eroul in noua pozitie.

        \brief g Contextul grafi in care trebuie efectuata desenarea eroului.
     */
    @Override
    public void Draw(Graphics g)
    {
        if(animatie!=null)
        {
            g.drawImage(animatie.imagine, (int) x, (int) y, width, height, null);
        }
        //healthBar.Draw(g, (int)x, (int)y - 15);

        ///doar pentru debug daca se doreste vizualizarea dreptunghiului de coliziune altfel se vor comenta urmatoarele doua linii
        //g.setColor(Color.blue);
        //g.fillRect((int)(x + bounds.x), (int)(y + bounds.y), bounds.width, bounds.height);
//        g.setColor(Color.RED);
//        g.fillRect((int)(x+attackBounds.x),(int)(y + attackBounds.y), attackBounds.width, attackBounds.height);
    }
    @Override
    public void MoveX(){

            if (!colls.checkTileCollision(this, xMove, 0)) {
                if (x >= 80 && x <= 1770) {
                    x += xMove;
                }
                if (x >= 1770) {
                    x = 1770;
                }
                if (x <= 80) {
                    x = 80;
                }
                if(colls.checkWaterCollision(this,xMove,0)){
                    isSwimming = true;
                    this.speed = 2f;
                }
                else {
                    this.speed=Character.DEFAULT_SPEED;
                    isSwimming = false;
                    if(refLink.GetMap().getId()==2 && !enemy.isEmpty()){
                        speed=3f;
                    }

                }
                if(colls.checkWebCollision(this,xMove,0)){
                    inWeb = true;
                    this.speed = 1.5f;
                }
                else
                {
                    if(!isSwimming){
                        this.speed= Character.DEFAULT_SPEED;
                        if(refLink.GetMap().getId()==2 && !enemy.isEmpty()){
                            speed=3f;
                        }

                    }
                    inWeb = false;
                }
            }
        }


    @Override
    public void MoveY(){
        //colls.CheckPoz(this);
        //if(!colision) {
//        if(colls.checkWaterCollision(this,0,yMove)){
//            y+=0;
//        }else {
        int q =0;
        if(isSwimming){
            q=20;
        }
            if (!colls.checkTileCollision(this, 0, yMove+q) ) {


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
                isSwimming=false;

            }
//        }

    }

    public String GetEnemy(){
        StringBuilder v = new StringBuilder();
        for (Enemy value : enemy) {
            if (value.GetLife() > 0) {
                v.append("1");
            } else {
                v.append('0');
            }
        }
        return v.toString();
    }
    public void SetEnemyLife(String a){
        for(int i=0;i<a.length();i++){
            if(a.charAt(i)=='0'){
                enemy.get(i).SetLife(-1);
            }
            else {
                enemy.get(i).SetLife(100);
            }
        }
    }

}