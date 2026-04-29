package PaooGame.Graphics;

import PaooGame.Enemies.Regina;
import PaooGame.Items.ItemType;
import PaooGame.Items.SkeletulMurat;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets
{
        /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static ArrayList<BufferedImage> heroLeft=new ArrayList<BufferedImage>();
    public static ArrayList<BufferedImage> heroRight=new ArrayList<BufferedImage>();
    public static ArrayList<BufferedImage> herorunRight=new ArrayList<BufferedImage>();
    public static ArrayList<BufferedImage> herorunLeft=new ArrayList<BufferedImage>();
    public static ArrayList<BufferedImage> heroattackright=new ArrayList<>();
    public static ArrayList<BufferedImage> heroattackleft=new ArrayList<>();
    public static ArrayList<BufferedImage> heroJump=new ArrayList<>();
    public static ArrayList<BufferedImage> herofallJump=new ArrayList<>();

    public static ArrayList<BufferedImage> skeletont=new ArrayList<>();
    public static BufferedImage paianjen;
    public static ArrayList<BufferedImage> demon=new ArrayList<>();
    public static BufferedImage regina;

    public static ArrayList<BufferedImage> skeletulMurat=new ArrayList<>();
    public static BufferedImage danut;
    public static BufferedImage zana;
    public static BufferedImage vrajitoarea;


    public static BufferedImage soil;
    public static BufferedImage lava;
    public static BufferedImage magma;
    public static BufferedImage web;
    //public static BufferedImage townGrassDestroyed;
    public static BufferedImage gate;
    public static BufferedImage water;
    public static BufferedImage transparent;
    public static BufferedImage transparentWall;
//    public static BufferedImage rockUp;
//    public static BufferedImage rockDown;
//    public static BufferedImage rockLeft;
//    public static BufferedImage rockRight;
    public static BufferedImage vine;
    public static BufferedImage chest;
    public static BufferedImage openchest;
    public static BufferedImage rocksoil;
    public static BufferedImage spike;

    public static BufferedImage cazan;
    public static BufferedImage whiteTent;
    public static BufferedImage redTent;
    public static BufferedImage fireball;

    public static ArrayList<BufferedImage>iteme=new ArrayList<BufferedImage>();


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init()
    {
            /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/fullspritesheet5.png"));

            /// Se obtin subimaginile corespunzatoare elementelor necesare.
        lava = sheet.crop(4, 17);
        magma = sheet.crop(0, 1);
        soil = sheet.crop(2, 17);
        water = sheet.crop(6, 17);
        web = sheet.crop(1, 16);
        gate = sheet.crop(0, 20);
        chest = sheet.crop(2, 19);
        openchest = sheet.crop(3, 19);
        cazan = sheet.crop(4,19);
        vine = sheet.crop(3, 16);
        transparent = sheet.crop(6,15);
        transparentWall = sheet.crop(7,15);
        rocksoil = sheet.crop(0, 17);
        spike = sheet.crop(2, 16);
        whiteTent = sheet.crop(1, 19);
        redTent = sheet.crop(0, 19);

        danut = sheet.crop(0, 10);
        zana=sheet.crop(0, 16);
        vrajitoarea=sheet.crop(0, 15);


        heroLeft.add(sheet.crop(0, 6));
        heroLeft.add(sheet.crop(1,6)) ;
        heroLeft.add(sheet.crop(2,6)) ;
        heroRight.add(sheet.crop(0, 2));
        heroRight.add(sheet.crop(1,2));
        heroRight.add(sheet.crop(2,2));

        for(int i=0;i<=1;i++)
            herorunRight.add(sheet.crop(i,4));
        for(int i=0;i<=4;i++)
            heroattackright.add(sheet.crop(i,5));
        for(int i=0;i<=4;i++)
            heroattackleft.add(sheet.crop(i,9));
        for(int i=0;i<=1;i++)
            herorunLeft.add(sheet.crop(i,8));
            heroJump.add(sheet.crop(1,7));
            herofallJump.add(sheet.crop(1,7));


        for(int i=0;i<=3;i++)
            demon.add(sheet.crop(i,18));
        for(int i=0;i<=3;i++)
            skeletont.add(sheet.crop(i,11));
        paianjen=sheet.crop(4,14);
        regina=sheet.crop(0,12);

        iteme.add(sheet.crop(0,0));
        for(int i=3;i<=7;i++)
            iteme.add(sheet.crop(i,0));
        iteme.add(sheet.crop(0,1));
        iteme.add(sheet.crop(1,1));
        iteme.add(sheet.crop(1,0));
        iteme.add(sheet.crop(2,0));

        for(int i=0;i<4;i++)
            skeletulMurat.add(sheet.crop(i,14));

        fireball=ImageLoader.LoadImage("/textures/Fireball.png");
//        rockUp = sheet.crop(2, 2);
//        rockDown = sheet.crop(3, 2);
//        rockLeft = sheet.crop(0, 3);
//        rockRight = sheet.crop(1, 3);
    }
}
