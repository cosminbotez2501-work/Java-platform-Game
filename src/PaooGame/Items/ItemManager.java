package PaooGame.Items;


//Pentru iteme in inventar;

import PaooGame.Meniu.StatusPanel;
import PaooGame.RefLinks;

import static PaooGame.Graphics.Assets.iteme;

public class ItemManager {
    private int[] ownedItems;
    private RefLinks ref;

    public ItemManager(RefLinks refLinks) {
        this.ref=refLinks;
        ownedItems = new int[ItemType.values().length];
    }

    public void collectItem(ItemType type) {
        ownedItems[type.ordinal()] = 2;
    }
    public void collectItemUsable(ItemType type){ownedItems[type.ordinal()] = 5;}

    public int hasItem(ItemType type) {
        return ownedItems[type.ordinal()];
    }

    public void applyPassiveEffects(Hero hero) {
        if (hasItem(ItemType.SAPALIGA) == 2) {
            hero.SetDamage(hero.GetDamage() + 5);
            ownedItems[ItemType.SAPALIGA.ordinal()] = 1;
            hero.itemtaken=true;
        }

        if (hasItem(ItemType.SHAORMA) == 2) {
            ownedItems[ItemType.SHAORMA.ordinal()] = 1;
            hero.itemtaken=true;
        }
        if(hasItem(ItemType.CRUCEA_DE_AUR)==2)
        {
            hero.SetDamage(hero.GetDamage()+10);
            ownedItems[ItemType.CRUCEA_DE_AUR.ordinal()]=1;
            hero.itemtaken=true;
        }
        if(hasItem(ItemType.PAPUCI_TABLA)==2)
        {
            hero.LavaDmages=false;
            ownedItems[ItemType.PAPUCI_TABLA.ordinal()]=1;
            hero.itemtaken=true;
        }
        if(hasItem(ItemType.SPRAY_GANDACI)==2)
        {
            hero.DamageAll(20);
            ownedItems[ItemType.SPRAY_GANDACI.ordinal()]=1;
            hero.itemtaken=true;
        }
        if(hasItem(ItemType.CIOCANUL)==2)
        {
            hero.DamageAll(20);
            ownedItems[ItemType.CIOCANUL.ordinal()]=1;
            hero.itemtaken=true;
        }
        if(hasItem(ItemType.APA_SFANTA)==2)
        {
            hero.SetLife(hero.GetMaxLife());
            ownedItems[ItemType.APA_SFANTA.ordinal()]=1;
            hero.itemtaken=true;
        }
        if(hasItem(ItemType.LICHID_VIATA)==2)
        {
            hero.finalJ=true;
            ownedItems[ItemType.LICHID_VIATA.ordinal()]=1;
            hero.itemtaken=true;
        }
    }
    public void SetItem(ItemType a)
    {
        ownedItems[a.ordinal()]=1;
    }

    public String getItem(){
        StringBuilder v = new StringBuilder();

        for (int i = 0; i < ItemType.values().length; i++) {
            int o;
            if((o=hasItem(ItemType.values()[i]))!=0) {
                v.append(o+1);
            }else{
                v.append(o);
            }
        }
        return v.toString();
    }
    public void activateItem(ItemType type, Hero hero) {
        if (hasItem(type) == 0) return;

        switch (type) {
            case BEREA_SFANTA:
                if(hero.GetLife()==hero.GetMaxLife())
                    return;
                hero.AddLife(hero.GetLife() + 20);
                ref.getStatusPanel().UpdateHealth(hero.life);
                ownedItems[type.ordinal()]--;
                hero.itemtaken=true;
                break;

            case BOMBA:
                hero.DamageAll(50);
                ownedItems[type.ordinal()]--;
                hero.itemtaken=true;
                break;
        }
    }

    public void reset(){
        for (int i = 0; i < ItemType.values().length; i++) {
            ownedItems[ItemType.values()[i].ordinal()] = 0;
        }

    }
}
