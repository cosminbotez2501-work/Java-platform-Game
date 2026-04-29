package PaooGame.Observator;

import java.util.ArrayList;
import PaooGame.RefLinks;

public class Observable {
    ArrayList<Observer> baubau=new ArrayList<>();
    RefLinks refLinks;

    public Observable(RefLinks reflinks)
    {
        this.refLinks=reflinks;
    }
    public void SendMessage()
    {


        for(Observer a: baubau)
        {
            a.GetMessage();
        }
    }
}
