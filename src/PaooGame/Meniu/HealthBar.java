package PaooGame.Meniu;

import PaooGame.Items.Character;

import java.awt.*;

public class HealthBar {
    private Character person;
    private int width;
    private int height;

    public HealthBar(Character person, int width, int height) {
        this.person = person ;
        this.width = width;
        this.height = height;
    }

    public void Draw(Graphics g, int x, int y) {
        int currentLife = person.GetLife();
        int maxLife = 100;

        int healthWidth = (int) ((currentLife / (float) maxLife) * width);

        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        g.setColor(Color.RED);
        g.fillRect(x, y, healthWidth, height);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}
