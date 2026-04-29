package PaooGame.GameWindow;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel{
    public void paint(Graphics g){
        ImageIcon img = new ImageIcon("actual/PozePtPdf/nivel1.jpeg");
        g.drawImage(img.getImage(),0,0,1920,1024,null);
        JRootPane r = getRootPane();
    }
}
