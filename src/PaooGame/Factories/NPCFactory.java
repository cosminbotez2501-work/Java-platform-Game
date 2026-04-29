package PaooGame.Factories;

import PaooGame.Items.*;
import PaooGame.RefLinks;

public class NPCFactory {
    public static NPC createNPC(String name, RefLinks refLinks, float x, float y, Hero hero) {
        switch (name) {
            case "Danut":
                return new Danut(refLinks, x, y, hero);
            case "Zana":
                return new Zana(refLinks, x, y, hero);
            case "Vrajitoarea":
                return new Vrajitoarea(refLinks, x, y, hero);
            case "SkeletulMurat":
                return new SkeletulMurat(refLinks,x,y,hero);
            default:
                throw new IllegalArgumentException("Unknown NPC type: " + name);
        }
    }

    public static NPC SpawnNPC(int id,RefLinks refLinks)
    {

        switch(id)
        {
            case 1:
                return NPCFactory.createNPC("Danut", refLinks, 2235, 484, refLinks.GetHero());
            case 2:
                return NPCFactory.createNPC("Zana",refLinks, 2010, 800, refLinks.GetHero());
            case 3:
                return NPCFactory.createNPC("Vrajitoarea",refLinks, 2410, 803, refLinks.GetHero());
            case 5:
                return NPCFactory.createNPC("SkeletulMurat",refLinks,1700,726,refLinks.GetHero());
        }
        return null;
    }
}
