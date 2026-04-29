
package PaooGame.Maps;

import PaooGame.Baza.Baza;
import PaooGame.Camera.Camera;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Input.Interaction;
import PaooGame.RefLinks;
import PaooGame.Tiles.Tile;
import PaooGame.*;
import java.io.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import static java.lang.System.exit;

/*! \class public class Map
    \brief Implementeaza notiunea de harta a jocului.
 */
public class Map
{
    private RefLinks refLink;   /*!< O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.*/
    private int width;          /*!< Latimea hartii in numar de dale.*/
    private int height;         /*!< Inaltimea hartii in numar de dale.*/
    private int [][] tiles;     /*!< Referinta catre o matrice cu codurile dalelor ce vor construi harta.*/

    private int x,y,id=1,ido=1;
    private Camera camera;
    private Interaction inter;
    private BufferedImage bgStart = ImageLoader.LoadImage("/textures/bgStart.png");
    private BufferedImage bgLVL1 = ImageLoader.LoadImage("/textures/lvl1p.png");
    private BufferedImage bgLVL2 = ImageLoader.LoadImage("/textures/lvl2p.jpeg");
    private BufferedImage bgLVL3 = ImageLoader.LoadImage("/textures/lvl3p.jpeg");
    private boolean swapMap = false;

    /*! \fn public Map(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinte catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public Map(RefLinks refLink)
    {
        /// Retine referinta "shortcut".
        this.refLink = refLink;
        ///incarca harta de start. Functia poate primi ca argument id-ul hartii ce poate fi incarcat.
        LoadWorld();
        x=0;
        y=0;
        camera = new Camera(refLink);
        inter = new Interaction(refLink);
    }

    /*! \fn public  void Update()
        \brief Actualizarea hartii in functie de evenimente (un copac a fost taiat)
     */

    public void Update()
    {
        camera.Update();
        inter.Update(id);
        if(ido!=id){
            swapMap=true;
            ido=id;
            LoadWorld();

            refLink.GetHero().resetPoz();
            refLink.GetCamera().resetPoz();

        }else {
            swapMap=false;
        }
        refLink.GetInteraction().openTheChest();
    }

    BufferedImage bgLoader(int id){
        switch (id){
            case 1: return bgLVL1;
            case 2: return bgLVL2;
            case 3: return bgLVL3;
            default: return bgStart;
        }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Functia de desenare a hartii.

        \param g Contextl grafi in care se realizeaza desenarea.
     */
    public void Draw(Graphics g)
    {
        g.drawImage(bgLoader(id),0,0,1920, 1080, null);

        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for(int y = 0; y < refLink.GetGame().GetHeight()/Tile.TILE_HEIGHT; y++)
        {
            for(int x = 0; x < refLink.GetGame().GetWidth()/Tile.TILE_WIDTH; x++)
            {
                GetTile(x, y).Draw(g, (int)x * Tile.TILE_HEIGHT + camera.getX(), (int)y * Tile.TILE_WIDTH + camera.getY());
            }
        }
        //g.translate(GetInput(),0);
    }

    /*! \fn public Tile GetTile(int x, int y)
        \brief Intoarce o referinta catre dala aferenta codului din matrice de dale.

        In situatia in care dala nu este gasita datorita unei erori ce tine de cod dala, coordonate gresite etc se
        intoarce o dala predefinita (ex. grassTile, mountainTile)
     */
    public Tile GetTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
        {
            return Tile.transparentTile;
        }
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
        {
            return Tile.transparentTile;
        }
        return t;
    }

    /*! \fn private void LoadWorld()
        \brief Functie de incarcare a hartii jocului.
        Aici se poate genera sau incarca din fisier harta. Momentan este incarcata static.
     */
    private void LoadWorld()
    {
        //atentie latimea si inaltimea trebuiesc corelate cu dimensiunile ferestrei sau
        //se poate implementa notiunea de camera/cadru de vizualizare al hartii
        ///Se stabileste latimea hartii in numar de dale.
        width = 33;
        ///Se stabileste inaltimea hartii in numar de dale
        height = 14;
        ///Se construieste matricea de coduri de dale
        tiles = new int[width][height];
        //Se incarca matricea cu coduri
        for(int y = 0; y < height; y++)
        {
            for(int x = 0; x < width; x++)
            {
                try {
                    tiles[x][y] = LevelMaps(id,y, x);
                }catch (FileNotFoundException e){
                    System.err.println("Fisierul cu mapele nu este gasit!!");
                    exit(1);
                }
            }
        }

        //Image img = new Image(getClass())
    }

    /*! \fn private int MiddleEastMap(int x ,int y)
        \brief O harta incarcata static.

        \param x linia pe care se afla codul dalei de interes.
        \param y coloana pe care se afla codul dalei de interes.
     */

//    private int Level1Map(int x ,int y) throws FileNotFoundException {
//            /Definire statica a matricei de coduri de dale.
//        final int [][]map = {
//
//        {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
//        {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
//        {4, 7, 0, 6, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 4, 4, 4, 0, 0, 0, 6, 0, 0, 0, 0, 4},
//        {4, 4, 4, 4, 4, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 4, 4, 4, 4, 4, 0, 0, 4, 4, 0, 0, 0, 0, 4},
//        {4, 4, 4, 4, 4, 2, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0, 0, 0, 4, 4, 4, 4, 2, 0, 4},
//        {4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 2, 0, 0, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 6, 6, 2, 0, 4},
//        {4, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 2, 0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 5, 6, 2, 0, 4},
//        {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 4, 4, 4, 4, 4, 0, 4},
//        {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 4, 4, 4, 4, 4, 0, 4},
//        {4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 4},
//        {4, 8, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 6, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 4},
//        {4, 4, 4, 4, 4, 4, 1, 1, 1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 1, 4, 4, 4, 4, 4, 1, 1, 4, 4, 4, 4},
//        {4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}};
//
//        Scanner sc = new Scanner(new File("src\\PaooGame\\Maps\\mapsFile.in"));
//        final int [][]map = new int[height][width];
//        for(int i=0;i<height;i++)
//            for(int j=0;j<width;j++)
//                map[i][j] = sc.nextInt();
//        return map[x][y];
//    }



    private int LevelMaps(int id, int x ,int y) throws FileNotFoundException {


        Scanner sc = new Scanner(new File("src\\PaooGame\\Maps\\mapsFile.in"));
        final int [][]map1 = new int[height][width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                map1[i][j] = sc.nextInt();
        final int [][]map2 = new int[height][width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                map2[i][j] = sc.nextInt();
        final int [][]map3 = new int[height][width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                map3[i][j] = sc.nextInt();
        final int [][]mapStart = new int[height][width];
        for(int i=0;i<height;i++)
            for(int j=0;j<width;j++)
                mapStart[i][j] = sc.nextInt();
        switch (id){
            case 1: return map1[x][y];
            case 2: return map2[x][y];
            case 3: return map3[x][y];
            default: return mapStart[x][y];
        }
    }
    public void setId(int i){
        id=i;
    }

    public int getId() {
        return id;
    }

    public void setNewTile(int x, int y, int id){
        tiles[x][y]=id;
    }

    public boolean isSwapMap() {
        return swapMap;
    }
}


