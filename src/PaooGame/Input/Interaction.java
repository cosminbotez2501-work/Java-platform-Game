package PaooGame.Input;

import PaooGame.Baza.Baza;

import PaooGame.Items.Hero;
import PaooGame.Items.ItemType;
import PaooGame.RefLinks;

public class Interaction {
    public RefLinks refLinks;
    private boolean openChest = false;
    public Interaction(RefLinks ref){
        refLinks = ref;
        refLinks.SetInteraction(this);
    }
    public void Base(){
        if(refLinks.GetKeyManager().interactJustPressed()){
            Hero h = refLinks.GetHero();

            if(h.GetX()>=(64 +refLinks.GetCamera().getX()) && h.GetX()<=(130 + refLinks.GetCamera().getX()) && h.GetY()>=(860 + refLinks.GetCamera().getY()) && h.GetY()<=(900 +refLinks.GetCamera().getY())) {
                Baza.SaveState(refLinks,0);
                System.exit(0);
            }
            if(h.GetX()>=1700  && h.GetX()<=1880 && h.GetY()>=(860 + refLinks.GetCamera().getY()) && h.GetY()<=(900 +refLinks.GetCamera().getY())) {
                refLinks.GetMap().setId(1);
                openChest=false;
            }


        }

    }

    public void Level1(){
        if(refLinks.GetKeyManager().interactJustPressed()){
            Hero h = refLinks.GetHero();

            if(h.GetX()>=(64 +refLinks.GetCamera().getX()) && h.GetX()<=(130 + refLinks.GetCamera().getX()) && h.GetY()>=(790 + refLinks.GetCamera().getY()) && h.GetY()<=(880 +refLinks.GetCamera().getY())) {
                Baza.SaveState(refLinks,0);
                System.exit(0);
            }
            if(h.GetX()>=(1540 + refLinks.GetCamera().getX()) && h.GetX()<=(1623 + refLinks.GetCamera().getX()) && h.GetY()>=(150 + refLinks.GetCamera().getY()) && h.GetY()<=(240 +refLinks.GetCamera().getY())) {
                refLinks.GetMap().setId(2);
                openChest=false;
            }
            if(h.GetX()>=(64 +refLinks.GetCamera().getX()) && h.GetX()<=(130 + refLinks.GetCamera().getX()) && h.GetY()>=(150 + refLinks.GetCamera().getY()) && h.GetY()<=(240 +refLinks.GetCamera().getY())) {
                System.out.println("chest");
                //aici se face chestu lvl 1 (ca sa vad ca is orb)
                refLinks.GetMap().setNewTile((int)h.GetX()/80,(int)h.GetY()/80,17);
                if(openChest==false)
                {
                    refLinks.getStatusPanel().UpdateScore(50);
                    refLinks.getStatusPanel().UpdateItems(ItemType.SPRAY_GANDACI);
                    refLinks.getStatusPanel().UpdateItems(ItemType.SAPALIGA);
                }
                openChest=true;
            }

        }

    }
    public void Level2(){
        if(refLinks.GetKeyManager().interactJustPressed()){
            Hero h = refLinks.GetHero();

            if(h.GetX()>=(64 +refLinks.GetCamera().getX()) && h.GetX()<=(130 + refLinks.GetCamera().getX()) && h.GetY()>=(790 + refLinks.GetCamera().getY()) && h.GetY()<=(880 +refLinks.GetCamera().getY())) {
                Baza.SaveState(refLinks,0);
                System.exit(0);
            }
            if(h.GetX()>=1700  && h.GetX()<=1800 && refLinks.GetCamera().getX()<-500 && h.GetY()>=(790 + refLinks.GetCamera().getY()) && h.GetY()<=(880 +refLinks.GetCamera().getY())) {
                refLinks.GetMap().setId(3);
                openChest=false;
            }
            if(h.GetX()>=(184 +refLinks.GetCamera().getX()) && h.GetX()<=(290 +refLinks.GetCamera().getX()) && h.GetY()>=(150 + refLinks.GetCamera().getY()) && h.GetY()<=(240 +refLinks.GetCamera().getY())) {
                System.out.println("chest");
                //aici e chest pt lvl 2 (ai crede ca se vede usor da am stat 2 minute ca nu gaseam)
                if(h.GetX()<240+refLinks.GetCamera().getX())
                    refLinks.GetMap().setNewTile((int)h.GetX()/80 +1,(int)h.GetY()/80,17);
                else
                    refLinks.GetMap().setNewTile((int)h.GetX()/80,(int)h.GetY()/80,17);
                if(openChest==false)
                {
                    refLinks.getStatusPanel().UpdateItems(ItemType.CIOCANUL);
                    refLinks.getStatusPanel().UpdateItems(ItemType.BOMBA);
                    refLinks.getStatusPanel().UpdateScore(69);
                }
                openChest=true;
            }

        }

    }
    public void Level3(){
        if(refLinks.GetKeyManager().interactJustPressed()){
            Hero h = refLinks.GetHero();

            if(h.GetX()>=(64 +refLinks.GetCamera().getX()) && h.GetX()<=(130 + refLinks.GetCamera().getX()) && h.GetY()>=(790 + refLinks.GetCamera().getY()) && h.GetY()<=(880 +refLinks.GetCamera().getY())) {
                Baza.SaveState(refLinks,0);
                System.exit(0);
            }

            if(h.GetX()>=1600  && h.GetX()<=1800 && h.GetY()>=(790 + refLinks.GetCamera().getY()) && h.GetY()<=(880 +refLinks.GetCamera().getY())) {
                System.out.println("Cazan");
                Baza.SaveScore(refLinks);
            }

        }

    }

    public void Update(int id){
        switch(id){
            case 1: Level1();break;
            case 2: Level2();break;
            case 3: Level3();break;
            default: Base();
        }
    }

    public void setOpenChest(boolean openChest) {
        this.openChest = openChest;
    }
    public void openTheChest(){
        if(openChest){
            switch (refLinks.GetMap().getId()){
                case 1: refLinks.GetMap().setNewTile(1,2,17); break;
                case 2:refLinks.GetMap().setNewTile(3,2,17);
            }
        }
        else{
            switch (refLinks.GetMap().getId()){
                case 1: refLinks.GetMap().setNewTile(1,2,7); break;
                case 2:refLinks.GetMap().setNewTile(3,2,7);
            }
        }
    }

    public int isOpenChest() {
        if(openChest){
            return 1;
        }
        return 0;
    }
}
