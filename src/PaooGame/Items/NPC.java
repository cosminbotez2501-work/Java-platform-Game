package PaooGame.Items;

import PaooGame.Camera.Camera;
import PaooGame.Factories.NPCFactory;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.Mesaje;
import PaooGame.RefLinks;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Ref;
import java.util.ArrayList;

public abstract class NPC extends Character{
    private ArrayList<BufferedImage> idle=new ArrayList<>();
    private Animator animatie;
    protected boolean triggered;
    protected boolean efectAplicat = false;

    private Hero erou;
    private ArrayList<String> mesaj=new ArrayList<>();
    private int vorbesc;
    protected String name;
    protected boolean showDialog = false;
    private String intrebare;
    private String[] optiuni ; //= {"Normal ca vinul\nfiert are alcool\ngandesti cumva\ncu nasul?", "Vinul fiert nu \n are alcool wtf"};
    protected int selectedOption = 0;
    protected boolean answerGiven = false;
    protected boolean answerCorrect = false;


    public NPC(RefLinks refLink, float x, float y,Hero a,String name)
    {
        super(refLink, x,y, Character.DEFAULT_CREATURE_WIDTH, Character.DEFAULT_CREATURE_HEIGHT);
        refLink.SetNPC(this);
        this.name=name;
        switch(name)
        {
            case "Danut":
                idle.add(Assets.danut);
                mesaj.addAll(Mesaje.danut);
                break;
            case "Zana":
                idle.add(Assets.zana);
                mesaj.addAll(Mesaje.zana);
                this.SetWidth(100);
                this.SetHeight(100);
                break;
            case "Vrajitoarea":
                idle.add(Assets.vrajitoarea);
                mesaj.addAll(Mesaje.vrajitoarea);
                break;
            case "SkeletulMurat":
                idle.addAll(Assets.skeletulMurat);
                break;
        }

        //this.SetSpeed();
        normalBounds.x=16;
        normalBounds.y=16;
        normalBounds.width=64;
        normalBounds.height=80;
        erou=a;
        triggered=false;

        vorbesc=0;

        attackBounds.x = 10;
        attackBounds.y = 10;
        attackBounds.width = 38;
        attackBounds.height = 38;
        animatie=new Animator(idle);
        animatie.setSpeed(200);
        animatie.start();
    }
    public void GetInput()
    {
        if(refLink.GetKeyManager().interactJustPressed())
        {
            if(erou.GetX() >= this.GetX() - 50 && erou.GetX() <= this.GetX() + 50 && erou.GetY() >= this.GetY() - 60 && erou.GetY() <= this.GetY() + 60)
            {
                if (!triggered) {
                    triggered = true;
                } else {
                    vorbesc = (vorbesc + 1) % mesaj.size();
                    switch (name)
                    {
                        case "Zana":
                            if(!efectAplicat)
                            {
                                refLink.GetItemManager().collectItem(ItemType.CRUCEA_DE_AUR);
                                refLink.GetItemManager().collectItem(ItemType.PAPUCI_TABLA);
                                efectAplicat=true;
                            }
                            break;
                        case "Danut":
                                refLink.GetItemManager().collectItem(ItemType.SHAORMA);
                                refLink.GetItemManager().collectItemUsable(ItemType.BEREA_SFANTA);
                                efectAplicat=true;
                    }
                    if (vorbesc == 0||erou.GetX() >= this.GetX() - 50 && erou.GetX() <= this.GetX() + 50 && erou.GetY() >= this.GetY() - 60 && erou.GetY() <= this.GetY() + 60)
                    {
                        triggered = false;
                    }
                }
            }
        }
    }

    private void staiAcolo()
    {
        xMove=0;
        yMove=0;
            if(refLink.GetCamera().getCameraMoving())
            {
                if(refLink.GetCamera().getCmrRight())
                {
                    xMove=-(int)refLink.GetHero().speed;
                } else if (refLink.GetCamera().getCmrLeft())
                {
                    xMove=+(int)refLink.GetHero().speed;
                }
            }


    }
    void setOptiuni(String [] optiuni)
    {
        this.optiuni=optiuni;
    }
    void setIntrebare(String intrebare)
    {
        this.intrebare=intrebare;
    }
    @Override
    public void Update()
    {
        GetInput();
        staiAcolo();
        Move(true);
    }

    @Override
    public void Draw(Graphics g)
    {
        g.drawImage(animatie.imagine, (int) x, (int) y, width, height, null);

        if(triggered && !showDialog) {
            if(name!="Zana")
            {
                g.setFont(new Font("Arial", Font.PLAIN, 18));
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(mesaj.get(vorbesc));
                int textHeight = fm.getHeight();
                int boxX = (int)x + (width - textWidth) / 2;
                int boxY = (int)y - 10;

                g.setColor(new Color(0, 0, 0, 190));
                g.fillRect(boxX - 5, boxY - textHeight + 5, textWidth + 10, textHeight);

                g.setColor(Color.WHITE);
                g.drawString(mesaj.get(vorbesc), boxX, boxY);
            }
            else
                if(erou.canTalkToZana)
                {
                    g.setFont(new Font("Arial", Font.PLAIN, 18));
                    FontMetrics fm = g.getFontMetrics();
                    int textWidth = fm.stringWidth(mesaj.get(vorbesc));
                    int textHeight = fm.getHeight();
                    int boxX = (int)x + (width - textWidth) / 2;
                    int boxY = (int)y - 10;

                    g.setColor(new Color(0, 0, 0, 190));
                    g.fillRect(boxX - 5, boxY - textHeight + 5, textWidth + 10, textHeight);

                    g.setColor(Color.WHITE);
                    g.drawString(mesaj.get(vorbesc), boxX, boxY);
                }
        }

        if(showDialog) {
            int dialogX = (1920-1080)/2;
            int dialogY = 300;
            int dialogWidth = 500;
            int dialogHeight = 300;

            g.setColor(new Color(0, 0, 0, 210));
            g.fillRoundRect(dialogX, dialogY, dialogWidth, dialogHeight, 20, 20);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString(intrebare, dialogX + 30, dialogY + 40);

            for(int i = 0; i < optiuni.length; i++) {
                String[] linii = optiuni[i].split("\n");
                int lineHeight = g.getFontMetrics().getHeight();
                int xText = dialogX + 50 + i * 240;
                int yStart = dialogY + 90;

                for (int j = 0; j < linii.length; j++) {
                    if(i == selectedOption)
                        g.setColor(Color.YELLOW);
                    else
                        g.setColor(Color.LIGHT_GRAY);

                    g.drawString(linii[j].trim(), xText, yStart + j * lineHeight);
                }
            }


            if(answerGiven) {
                g.setColor(answerCorrect ? Color.GREEN : Color.RED);
                String msg;
                switch (name)
                {
                    case "Vrajitoarea":
                        msg = answerCorrect ? "Corect! Stiam ca esti o persoana culta" : "Nu!!!Nu pot accepta asa ceva";
                        break;
                    default:
                        msg = answerCorrect ? "Bun bun multumesc poti trai" : "Ha pentru asta vei muri,ia de aici \n niste apa sfanta pentru rani";

                }
                g.drawString(msg, dialogX + 50, dialogY + 200);
            }
        }
    }

}
