package PaooGame.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

/*! \class public class KeyManager implements KeyListener
    \brief Gestioneaza intrarea (input-ul) de tastatura.

    Clasa citeste daca au fost apasata o tasta, stabiliteste ce tasta a fost actionata si seteaza corespunzator un flag.
    In program trebuie sa se tina cont de flagul aferent tastei de interes. Daca flagul respectiv este true inseamna
    ca tasta respectiva a fost apasata si false nu a fost apasata.
 */
public class KeyManager implements KeyListener
{
    private boolean[] keys;
    private boolean[] justpressed;
    private boolean[] lastKeys;

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean jump;
    public boolean interact;
    public boolean triggerConvo;
    public boolean attack;
    public boolean attackcopy;
    public boolean item1;
    public boolean item2;
    public boolean item3;
    public boolean item4;
    public boolean pause;

    public KeyManager()
    {
        keys = new boolean[256];
        justpressed = new boolean[256];
        lastKeys = new boolean[256];
        attackcopy=false;
    }

    public void Update()
    {
        // Determine keys that were just pressed this frame
        for(int i = 0; i < keys.length; i++)
        {
            justpressed[i] = !lastKeys[i] && keys[i]; // true only on press this frame
        }

        // Update action flags
        up = keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        jump = keys[KeyEvent.VK_SPACE];
        interact = keys[KeyEvent.VK_E];
        triggerConvo = justpressed[KeyEvent.VK_E];
        attack = keys[KeyEvent.VK_J];
        item1 = justpressed[KeyEvent.VK_1];
        item2 = justpressed[KeyEvent.VK_1];
        item3 = justpressed[KeyEvent.VK_1];
        item4 = justpressed[KeyEvent.VK_1];
        pause = justpressed[KeyEvent.VK_ESCAPE];


        // Save current keys for next frame
        System.arraycopy(keys, 0, lastKeys, 0, keys.length);
    }

    public boolean interactJustPressed() {
        return triggerConvo;
    }

    public boolean leftJustPressed() {
        return justpressed[KeyEvent.VK_A];
    }

    public boolean rightJustPressed() {
        return justpressed[KeyEvent.VK_D];
    }

    public boolean enterJustPressed() {
        return justpressed[KeyEvent.VK_ENTER];
    }

    public boolean isPause(){
        return pause;
    }

    public boolean itemSelect(int id){
        return switch (id) {
            case 1 -> item1;
            case 2 -> item2;
            case 3 -> item3;
            case 4 -> item4;
            default -> false;
        };
    }

    public boolean AttackJustPressed(){ return attack;}

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (e.getKeyCode() < keys.length)
            keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        if (e.getKeyCode() < keys.length)
            keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
