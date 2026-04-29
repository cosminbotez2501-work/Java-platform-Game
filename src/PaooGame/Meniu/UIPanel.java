package PaooGame.Meniu;

import PaooGame.GameWindow.GameWindow;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JPanel {
    private int score;
    public UIPanel() {
        score=0;
    }
    public void draw(Graphics g)
    {
        g.setFont(new Font("Arial",Font.PLAIN,40));
        g.setColor(Color.WHITE);
        g.drawString("Score: "+score,50,50);
    }
}
