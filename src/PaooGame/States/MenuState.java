package PaooGame.States;

import PaooGame.Baza.Baza;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;
import PaooGame.RefLinks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/*! \class public class MenuState extends State
    \brief Implementeaza notiunea de menu pentru joc.
 */
public class MenuState extends State //implements ActionListener
{
//    private JButton resumeButton;
//    private JButton exitButton;
//    public static BufferedImage buttonResume;
//    public static BufferedImage buttonLoad;
//    public static BufferedImage buttonExit;

    /*! \fn public MenuState(RefLinks refLink)
        \brief Constructorul de initializare al clasei.

        \param refLink O referinta catre un obiect "shortcut", obiect ce contine o serie de referinte utile in program.
     */
    public MenuState(RefLinks refLink)
    {
        super(refLink);



       // SpriteSheet OurButtons=new SpriteSheet(ImageLoader.LoadImage("/textures/ButtonsFullsprite.png"));
     //   int buttonW=178,buttonH=44;
        //croparea butoanelor din imaginea lor
//        buttonResume=OurButtons.crop(0,0,buttonW,buttonH);
//        buttonLoad=OurButtons.crop(0,1,buttonW,buttonH);
//        buttonExit=OurButtons.crop(0,2,buttonW,buttonH);
    }

    /*! \fn public void Update()
        \brief Actualizeaza starea curenta a meniului.
     */
    @Override
    public void Update()
    {
//
//        int x = refLink.getMouseManager().getMouseX();
//        int y = refLink.getMouseManager().getMouseY();
//
//        checkMouseClick(x,y);
        if(!refLink.GetHero().finalJ)
            if(refLink.GetKeyManager().isPause() || refLink.GetGame().died>=60){
     //           refLink.GetGame().StartGame();

                refLink.GetGame().setPauseState(false);
                refLink.GetGame().setPlayState(true);
                State.SetState(refLink.GetGame().getPlayState());
                refLink.getStatusPanel().resetScore();
                String s = refLink.GetGame().LoadSave();
                String []x=s.split(" ");
                refLink.GetHero().SetEnemyLife(x[0]);
                refLink.GetInteraction().setOpenChest(Integer.parseInt(x[1]) == 1);
                if(refLink.GetGame().died>=60) {
                    refLink.GetHero().resetPoz();
                    refLink.GetCamera().resetPoz();
                    refLink.getPlayState().resetNPC();
                    refLink.getPlayState().resetEnemies();
                }
                refLink.GetGame().died = 0;
                if(refLink.GetKeyManager().isPause()){
                    Baza.DeleteSave(1);
                }
            }
    }

    /*! \fn public void Draw(Graphics g)
        \brief Deseneaza (randeaza) pe ecran starea curenta a meniului.

        \param g Contextul grafic in care trebuie sa deseneze starea jocului pe ecran.
     */
    @Override
    public void Draw(Graphics g) {
        if(!refLink.GetHero().finalJ)
            if (refLink.GetHero().GetLife() <= 0) {
                g.drawImage(refLink.GetGame().dieScreen, 0, 0, 1920, 1080, null);
                refLink.GetGame().died++;
            } else {
                g.drawImage(refLink.GetGame().pauseScreen, 0, 0, 1920, 1080, null);

    //            g.drawImage(buttonResume,1500/2, 350, 400, 90,null);
    //            g.drawImage(buttonLoad,1500/2, 460, 400, 90,null);
    //            g.drawImage(buttonExit,1500/2, 570, 400, 90,null);
            }
        else{
            g.drawImage(refLink.GetGame().endScreen,0,0,1920,1080,null);
        }

    }


//    @Override
//    public void actionPerformed(ActionEvent e) {
//        String cmd = e.getActionCommand();
//
//        switch (cmd) {
//            case "resume":
//                refLink.GetKeyManager().pause=true;
//                this.Update();
//                break;
//            case "exit":
//                Baza.SaveState(refLink,0);
//                System.exit(0);
//                break;
//        }
//    }
//    public void checkMouseClick(int mouseX, int mouseY) {
//        Rectangle resumeRect = new Rectangle(1500/2, 350, 400, 90);
//        Rectangle loadRect = new Rectangle(1500/2, 460, 400, 90);
//        Rectangle exitRect = new Rectangle(1500/2, 570, 400, 90);
//
//        if (resumeRect.contains(mouseX, mouseY)) {
//            refLink.GetKeyManager().pause = true;
//           // this.Update();
//        } else if (exitRect.contains(mouseX, mouseY)) {
//            Baza.SaveState(refLink, 0);
//            System.exit(0);
//        } else if(loadRect.contains(mouseX,mouseY)){
//            refLink.GetGame().died=100;
//            //this.Update();
//        }
//    }
}
