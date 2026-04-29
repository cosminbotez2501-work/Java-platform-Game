package PaooGame.Factories;

import PaooGame.Enemies.Demon;
import PaooGame.Enemies.Regina;
import PaooGame.Enemies.Skeletont;
import PaooGame.Enemies.SpiderMan;
import PaooGame.Items.*;
import PaooGame.RefLinks;

import java.util.ArrayList;

public class EnemyFactory {
    public static Enemy createEnemy(String name, RefLinks refLinks, float x, float y, Hero hero) {
        switch (name) {
            case "Demon":
                return new Demon(refLinks, x, y, hero);
            case "Spider":
                return new SpiderMan(refLinks, x, y, hero);
            case "Schelet":
                return new Skeletont(refLinks, x, y, hero);
            case "Regina":
                return new Regina(refLinks, x, y, hero);
            default:
                throw new IllegalArgumentException("Unknown NPC type: " + name);
        }
    }
    public static ArrayList<Enemy> SpawnEnemy(int id,RefLinks refLinks)
    {
        ArrayList<Enemy> enemies=new ArrayList<>();
        Enemy a;
        switch (id)
        {
            case 1:
                a=EnemyFactory.createEnemy("Spider",refLinks,760,600,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Spider",refLinks,2000,600,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Spider",refLinks,790,160,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Spider",refLinks,1400,69,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Spider",refLinks,2250,69,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Spider",refLinks,220,66,refLinks.GetHero());
                enemies.add(a);
                break;
            case 2:
                a=EnemyFactory.createEnemy("Schelet",refLinks,760,800,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Schelet",refLinks,400,100,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Schelet",refLinks,1000,200,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Schelet",refLinks,1500,100,refLinks.GetHero());
                enemies.add(a);
                break;
            case 3:
                a=EnemyFactory.createEnemy("Demon",refLinks,760,550,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Demon",refLinks,400,800,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Demon",refLinks,600,100,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Demon",refLinks,1600,200,refLinks.GetHero());
                enemies.add(a);
                a=EnemyFactory.createEnemy("Regina",refLinks,2300,803,refLinks.GetHero());
                enemies.add(a);

        }
        return enemies;
    }
    private static int swapX(int i)
    {
        switch (i)
        {
            case 1:
                return 760;
            case 2:
                return 920;
            case 3:
                return 1400;
            case 4:
                return 1980;
            case 5:
                return 2200;
            case 6:
                return 400;
            default:
                return 600;
        }
    }
    private static int swapY(int i)
    {
        switch (i%3)
        {
            case 1:
                return 600;
            case 2:
                return 484;
            default:
                return 20;
        }
    }
}
