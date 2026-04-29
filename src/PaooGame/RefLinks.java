package PaooGame;

import PaooGame.Camera.Camera;
import PaooGame.Input.Interaction;
//import PaooGame.Input.MouseManager;
import PaooGame.Items.Hero;
import PaooGame.Items.ItemManager;
import PaooGame.Items.NPC;
import PaooGame.Maps.Map;

import PaooGame.Input.KeyManager;
import PaooGame.Meniu.StatusPanel;
import PaooGame.States.PlayState;

/*! \class public class RefLinks
    \brief Clasa ce retine o serie de referinte ale unor elemente pentru a fi usor accesibile.

    Altfel ar trebui ca functiile respective sa aiba o serie intreaga de parametri si ar ingreuna programarea.
 */
public class RefLinks
{
    private Game game;          /*!< Referinta catre obiectul Game.*/
    private Map map;            /*!< Referinta catre harta curenta.*/
    private Hero hero;
    private Camera camera;
    private Interaction inter;
    private NPC danut;
    StatusPanel statusPanel;
    ItemManager itemManager;
    PlayState playState;
//    MouseManager mouseManager;

    /*! \fn public RefLinks(Game game)
        \brief Constructorul de initializare al clasei.

        \param game Referinta catre obiectul game.
     */
    public RefLinks(Game game)
    {
        this.game = game;
    }

    /*! \fn public KeyManager GetKeyManager()
        \brief Returneaza referinta catre managerul evenimentelor de tastatura.
     */
    public KeyManager GetKeyManager()
    {
        return game.GetKeyManager();
    }

    /*! \fn public int GetWidth()
        \brief Returneaza latimea ferestrei jocului.
     */
    public int GetWidth()
    {
        return game.GetWidth();
    }

    /*! \fn public int GetHeight()
        \brief Returneaza inaltimea ferestrei jocului.
     */
    public int GetHeight()
    {
        return game.GetHeight();
    }

    /*! \fn public Game GetGame()
        \brief Intoarce referinta catre obiectul Game.
     */
    public Game GetGame()
    {
        return game;
    }

    /*! \fn public void SetGame(Game game)
        \brief Seteaza referinta catre un obiect Game.

        \param game Referinta obiectului Game.
     */
    public void SetGame(Game game)
    {
        this.game = game;
    }

    /*! \fn public Map GetMap()
        \brief Intoarce referinta catre harta curenta.
     */
    public Map GetMap()
    {
        return map;
    }

    /*! \fn public void SetMap(Map map)
        \brief Seteaza referinta catre harta curenta.

        \param map Referinta catre harta curenta.
     */
    public void SetMap(Map map)
    {
        this.map = map;
    }
    public void SetHero(Hero h){hero=h;}
    public Hero GetHero(){return hero;}
    public void SetCamera(Camera c){camera=c;}
    public Camera GetCamera(){return camera;}
    public void SetInteraction(Interaction i){inter=i;}
    public Interaction GetInteraction(){return inter;}
    public void SetNPC(NPC persoana){this.danut=persoana;}
    public NPC GetNPC(){return this.danut;}
    public void SetStatus(StatusPanel st){
        this.statusPanel=st;
        this.SetItemManager(statusPanel.iteme);
    }
    public StatusPanel getStatusPanel(){return this.statusPanel;}
    public void SetItemManager(ItemManager a){this.itemManager=a;}
    public ItemManager GetItemManager(){return this.itemManager;}
    public void setPlayState(PlayState playState) {
        this.playState = playState;
    }
    public PlayState getPlayState(){
        return playState;
    }

//    public MouseManager getMouseManager() {
//        return mouseManager;
//    }
//
//    public void setMouseManager(MouseManager mouseManager) {
//        this.mouseManager = mouseManager;
//    }
}
