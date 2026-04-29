
package PaooGame.Meniu;

import PaooGame.Baza.Baza;
import PaooGame.Game;
import PaooGame.Graphics.Assets;
import PaooGame.Graphics.ImageLoader;
import PaooGame.Graphics.SpriteSheet;

import javax.swing.*;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import static PaooGame.Graphics.Assets.*;

public class MyFrame extends JFrame implements ActionListener {
    private int gwndw;
    private int gwndh;
    public static BufferedImage buttonSave;
    public static BufferedImage buttonLoad;
    public static BufferedImage buttonExit;
    private JLabel scoreLabel;
    private int score = 0;
    private int score2=0;
    private int score3=0;

    private int FrameW=1024,FramwH=768,buttonW=178,buttonH=44;


    public void StartMenu(int w,int h)
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(FrameW, FramwH);
        this.setTitle("Lichidul Vietii");

        JMenuBar menuBar = new JMenuBar();
        gwndw = w;
        gwndh = h;
        //ImageIcon backgroundIcon = new ImageIcon("/textures/imagine meniu.png");
        //Image backgroundImage = backgroundIcon.getImage();

        //Se initializeaza obiecte cu imaginile corespunaztoare background-ului si al butoanelor
        BufferedImage backgroundImage = ImageLoader.LoadImage("/textures/imagine meniu.png");
        SpriteSheet OurButtons=new SpriteSheet(ImageLoader.LoadImage("/textures/ButtonsFullsprite.png"));

        //croparea butoanelor din imaginea lor
        buttonSave=OurButtons.crop(0,0,buttonW,buttonH);
        buttonLoad=OurButtons.crop(0,1,buttonW,buttonH);
        buttonExit=OurButtons.crop(0,2,buttonW,buttonH);
        //Icon a=new ImageIcon(loadIcon);
        Icon a = new ImageIcon(buttonSave);
        Icon b=new ImageIcon(buttonLoad);
        Icon c=new ImageIcon(buttonExit);

        //Pentru backgroundul meniului
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);

        this.setIconImage(backgroundImage);

        //crearea butoanelor
        JButton loadButton = new JButton(b);
        JButton exitButton = new JButton(c);
        JButton newGame = new JButton(a);

        //atribuirea comenzilor pt butoane
        newGame.setActionCommand("NewGame");
        loadButton.setActionCommand("LoadGame");
        exitButton.setActionCommand("Exit");

        //adaugarea unui action listener pentru a putea detecta clickurile
        newGame.addActionListener(this);
        loadButton.addActionListener(this);
        exitButton.addActionListener(this);

        exitButton.setBounds((FrameW-buttonW-10)/2, 400, buttonW, buttonH);
        loadButton.setBounds((FrameW-buttonW-10)/2, 300, buttonW, buttonH);
        newGame.setBounds((FrameW-buttonW-10)/2, 200, buttonW, buttonH);
        backgroundPanel.add(BorderLayout.CENTER, loadButton);
        backgroundPanel.add(BorderLayout.NORTH, exitButton);
        backgroundPanel.add(newGame);

        Baza.LoadScore(this);

        scoreLabel = new JLabel("Scor1: " + score);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));  // font Comic Sans, mai mare
        scoreLabel.setForeground(Color.BLACK);                          // text negru, contrast cu gri
        scoreLabel.setOpaque(true);                                     // să poată avea fundal colorat
        scoreLabel.setBackground(Color.LIGHT_GRAY);                    // fundal gri deschis
        scoreLabel.setBounds((FrameW - 240) / 2, 480, 220, 50);        // pătrat mai mare, centrat
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)); // bordură gri închis
        backgroundPanel.add(scoreLabel);

        scoreLabel = new JLabel("Scor2: " + score2);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));  // font Comic Sans, mai mare
        scoreLabel.setForeground(Color.BLACK);                          // text negru, contrast cu gri
        scoreLabel.setOpaque(true);                                     // să poată avea fundal colorat
        scoreLabel.setBackground(Color.LIGHT_GRAY);                    // fundal gri deschis
        scoreLabel.setBounds((FrameW - 240) / 2, 530, 220, 50);        // pătrat mai mare, centrat
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)); // bordură gri închis
        backgroundPanel.add(scoreLabel);

        scoreLabel = new JLabel("Scor3: " + score3);
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 28));  // font Comic Sans, mai mare
        scoreLabel.setForeground(Color.BLACK);                          // text negru, contrast cu gri
        scoreLabel.setOpaque(true);                                     // să poată avea fundal colorat
        scoreLabel.setBackground(Color.LIGHT_GRAY);                    // fundal gri deschis
        scoreLabel.setBounds((FrameW - 240) / 2, 580, 220, 50);        // pătrat mai mare, centrat
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scoreLabel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2)); // bordură gri închis
        backgroundPanel.add(scoreLabel);



       /* MouseAdapter maus = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        };
        newGeym.addMouseListener(maus);
        newGeym.
*/

        this.setContentPane(backgroundPanel);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
    }

    public void setScore(int newScore,int id) {
        switch (id){
            case 1:this.score = newScore;break;
//                if (scoreLabel != null) {
//                    scoreLabel.setText("Scor: " + score);
//                }
            case 2:this.score2 = newScore;break;
//                if (scoreLabel != null) {
//                    scoreLabel.setText("Scor: " + score);
//                }
            case 3:this.score3 = newScore;break;
//                if (scoreLabel != null) {
//                    scoreLabel.setText("Scor: " + score);
//                }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "NewGame":
                Game paooGame = new Game("PaooGame", gwndw, gwndh,0);
                paooGame.StartGame();
                this.setVisible(false);
                break;
            case "LoadGame":
                paooGame = new Game("PaooGame", gwndw, gwndh,1);
                paooGame.StartGame();
                this.setVisible(false);
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }
}
