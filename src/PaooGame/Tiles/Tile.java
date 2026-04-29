package PaooGame.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile
{
    private static final int NO_TILES   = 32;
    public static Tile[] tiles          = new Tile[NO_TILES];       /*!< Vector de referinte de tipuri de dale.*/

        /// De remarcat ca urmatoarele dale sunt statice si publice. Acest lucru imi permite sa le am incarcate
        /// o singura data in memorie
    //public static Tile dirtTile        = new DirtTile(0);     /*!< Dala de tip pamant*/
    //public static Tile mountainTile     = new MountainTile(1);  /*!< Dala de tip munte/piatra*/
    public static Tile soilTile         = new SoilTile(4);      /*!< Dala de tip sol/pamant*/
    public static Tile waterTile        = new WaterTile(1);     /*!< Dala de tip apa*/
    public static Tile vineTile         = new VineTile(2);      /*!< Dala de tip liana*/
    public static Tile lavaTile         = new LavaTile(3);      /*!< Dala de tip lava*/
    public static Tile transparentTile  = new TransparentTile(0); /*!<Dala transparenta*/
    public static Tile danut            = new danut(13);           /*!<Alchimistul berii*/
    public static Tile webTile          = new WebTile(6); /*!<Dala spiderWeb*/
    public static Tile chest            = new chest(7);           /*!<Alchimistul berii*/
    public static Tile gate             = new Gate(8);           /*!<Alchimistul berii*/

    public static Tile rocksoil         = new RockSoilTile(9);
    public static Tile spike            = new SpikeTile(10);
    public static Tile magmaTile        = new MagmaTile(5);      /*!< Dala de tip lava*/

    public static Tile transWall        = new TransparentWithC(20);
    public static Tile whiteTent        = new WhiteTent(21);
    public static Tile redTent          = new RedTent(22);
    public static Tile openChest            = new OpenChest(17);

    public static Tile Cazan            = new Cazan(30);

    public static final int TILE_WIDTH  = 80;                       /*!< Latimea unei dale.*/
    public static final int TILE_HEIGHT = 80;                       /*!< Inaltimea unei dale.*/

    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

    /*! \fn public Tile(BufferedImage texture, int id)
        \brief Constructorul aferent clasei.

        \param image Imaginea corespunzatoare dalei.
        \param id Id-ul dalei.
     */
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }

    /*! \fn public void Update()
        \brief Actualizeaza proprietatile dalei.
     */
    public void Update()
    {

    }

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y)
    {
            /// Desenare dala
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid()
    {
        return false;
    }
    public boolean IsWater()
    {
        return false;
    }
    public boolean IsVine()
    {
        return false;
    }
    public boolean IsSpike()
    {
        return false;
    }
    public boolean IsLava()
    {
        return false;
    }
    public boolean IsWeb()
    {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId()
    {
        return id;
    }
}
