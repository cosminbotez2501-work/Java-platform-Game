
package PaooGame.States;

import PaooGame.Baza.Baza;
import PaooGame.Enemies.Demon;
import PaooGame.Enemies.Skeletont;
import PaooGame.Enemies.SpiderMan;
import PaooGame.Factories.EnemyFactory;
import PaooGame.Factories.NPCFactory;
import PaooGame.Items.*;
import PaooGame.RefLinks;
import PaooGame.Maps.Map;

import java.awt.*;
import java.util.ArrayList;

/*! \class public class PlayState extends State
    \brief Implementeaza/controleaza jocul.
 */
public class PlayState extends State
{
    private Hero hero;  /*!< Referinta catre obiectul animat erou (controlat de utilizator).*/
    private Map map;    /*!< Referinta catre harta curenta.*/
    private Enemy enemy,spider,schelet;
//    private NPC danut;
//    private NPC zana;
//    private NPC vrajitoarea;
    private NPC baiatul;
    private NPC specialBaiatul;
    private ArrayList<Enemy> enemies=new ArrayList<>();

    /*! \fn public PlayState(RefLinks refLink)
        \brief Constructorul de initializare al clasei

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public PlayState(RefLinks refLink)
    {
        ///Apel al constructorului clasei de baza
        super(refLink);
        refLink.setPlayState(this);
        ///Construieste harta jocului
        map = new Map(refLink);
        ///Referinta catre harta construita este setata si in obiectul shortcut pentru a fi accesibila si in alte clase ale programului.
        refLink.SetMap(map);
        ///Construieste eroul
        hero = new Hero(refLink,128, 806);
        enemy= EnemyFactory.createEnemy("Demon",refLink, 900, 600, hero);
        spider=EnemyFactory.createEnemy("Spider",refLink,760,600,hero);
        schelet=EnemyFactory.createEnemy("Schelet",refLink,1100,600,hero);
//        hero.addObserver(enemy);
//        hero.addObserver(spider);
//        hero.addObserver(schelet);
        baiatul=NPCFactory.SpawnNPC(map.getId(),refLink);
        specialBaiatul=NPCFactory.SpawnNPC(map.getId()+3,refLink);
        enemies=EnemyFactory.SpawnEnemy(map.getId(),refLink);
        hero.addAllObservers(enemies);
//        danut = NPCFactory.createNPC("Danut", refLink, 1440, 808, hero);
//        zana=NPCFactory.createNPC("Zana",refLink, 1540, 808, hero);
//        vrajitoarea=NPCFactory.createNPC("Vrajitoarea",refLink, 1300, 808, hero);
        //danut=new NPC(refLink,1440,808,hero);

    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a jocului.
     */
    @Override
    public void Update()
    {
        map.Update();
        if(map.isSwapMap()){
            baiatul=NPCFactory.SpawnNPC(map.getId(),refLink);
            specialBaiatul=NPCFactory.SpawnNPC(map.getId()+3,refLink);
            enemies=EnemyFactory.SpawnEnemy(map.getId(),refLink);
            hero.addAllObservers(enemies);
            Baza.SaveState(refLink,0);
        }
        hero.Update();
        hero.UpdateAll();
        baiatul.Update();
        if(specialBaiatul!=null)
            specialBaiatul.Update();

        //enemy.Update();
        //spider.Update();
        //schelet.Update();
//        danut.Update();
//        zana.Update();
//        vrajitoarea.Update();
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a jocului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g)
    {
        map.Draw(g);
       // enemy.Draw(g);
        //spider.Draw(g);
        //schelet.Draw(g);
        hero.DrawAll(g);
        hero.Draw(g);
        baiatul.Draw(g);
        if(specialBaiatul!=null)
            specialBaiatul.Draw(g);
//       danut.Draw(g);
//       zana.Draw(g);
//       vrajitoarea.Draw(g);
    }
    public void resetNPC(){
        baiatul.resetPoz();
    }
    public void resetEnemies(){
        for(Enemy a: enemies){
            a.resetPoz();
        }
    }
}
