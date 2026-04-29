package PaooGame.Items;

import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Animator {
    public ArrayList<BufferedImage> frames;
    public BufferedImage imagine;
    private volatile boolean running=false;
    private int curentframe;
    private long previousTime,speed;
    private int countdown=0;
    private boolean cd=false;

    public Animator(ArrayList<BufferedImage> frames)
    {
        this.frames=frames;
        if (frames != null && frames.isEmpty())
            imagine = frames.get(0);
    }
    public void SetArray(ArrayList<BufferedImage> frames)
    {
        this.frames=frames;
        if (frames != null && frames.isEmpty())
            imagine = frames.get(0);
    }
    public void update(long time)
    {
        cd=false;
        if(running && frames != null && !frames.isEmpty())
        {
            if(time-previousTime>=speed)
            {
                curentframe++;
                countdown++;
                try {
                    imagine = frames.get(curentframe);
                } catch (IndexOutOfBoundsException e) {
                    curentframe = 0;
                    imagine = frames.get(curentframe);
                    countdown=0;
                }
                if(countdown==frames.size()-2)
                {
                    cd=true;
                }
                previousTime=time;
            }
        }
    }
    public boolean GetCD(){return cd;}
    public void setSpeed(long speed)
    {
        this.speed=speed;
    }
    public boolean isRunning()
    {
        return running;
    }
    public void start()
    {
        curentframe=0;
        previousTime=0;
        running=true;
        if (frames != null && !frames.isEmpty()) {
            imagine = frames.get(0);
        }
    }
    public int getCurentframe(){return curentframe;}
    public void stop()
    {
        running=false;
        curentframe=0;
        previousTime=0;
    }
}