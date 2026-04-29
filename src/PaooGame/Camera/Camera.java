package PaooGame.Camera;

import PaooGame.Collision.Collisions;
import PaooGame.RefLinks;

public class Camera {
    RefLinks refLink;
    private int x,y,xMove,yMove,xv,yv;
    private boolean CameraMoving;
    private boolean CameraMovingLeft,CameraMovingRight;
    private Collisions colls;

    public Camera(RefLinks reflink){
        this.refLink = reflink;
        reflink.SetCamera(this);
        colls = new Collisions(reflink);
        x = 0;
        y = 0;
        xMove = 0;
        yMove = 0;
        xv=0;
        yv=0;
        CameraMoving=false;
        CameraMovingLeft=false;
        CameraMovingRight=false;
    }

    private void Move()
    {
//        if(x>=5 && x<=(refLink.GetWidth()-5))
//            x=x+xMove;
        if (xMove < 0 && x >= -refLink.GetWidth() + refLink.GetHero().GetSpeed() + 1920) {
            if (!colls.checkTileCollision(refLink.GetHero(), -xMove, 0)) {
                x = x + xMove;
            }}
        if (xMove > 0 && x <= -refLink.GetHero().GetSpeed()) {
            if (!colls.checkTileCollision(refLink.GetHero(), -xMove, 0)) {
                x = x + xMove;
            }
        }
        if (!colls.checkTileCollision(refLink.GetHero(), 0, yMove)) {
            if (yMove < 0 && y >= -refLink.GetHeight() + 5 + 1440) {
                y = y + yMove;
            }
            if (yMove > 0 && y <= -5) {
                y = y + yMove;
            }
        }
        if(x!=xv||y!=yv)
            CameraMoving=true;
        else
            CameraMoving=false;
        xv=x;
        yv=y;
    }

    private void GetInput()
    {
        ///Implicit eroul nu trebuie sa se deplaseze daca nu este apasata o tasta
        xMove = 0;
        yMove = 0;
//        ///Verificare apasare tasta "left"
//        if(refLink.GetKeyManager().left)
//        {
//            xMove = 5;
//        }
//        ///Verificare apasare tasta "dreapta"
//        if(refLink.GetKeyManager().right)
//        {
//            xMove = -5;
//        }
            if (refLink.GetHero().GetX() == 80 && refLink.GetKeyManager().left) {
                xMove = (int) (refLink.GetHero().GetSpeed());
                CameraMovingLeft = true;
                CameraMovingRight = false;
            }

            if (refLink.GetHero().GetX() == 1770 && refLink.GetKeyManager().right) {
                xMove = -(int) (refLink.GetHero().GetSpeed());
                CameraMovingLeft = false;
                CameraMovingRight = true;
            }

        if(refLink.GetHero().GetY() == 64 && refLink.GetKeyManager().up)
        {
            yMove = (int) (refLink.GetHero().GetSpeed());
        }

        if(refLink.GetHero().GetY() == 952 && refLink.GetKeyManager().down)
        {
            yMove = -(int) (refLink.GetHero().GetSpeed());
        }

    }
    public  void Update()
    {
        GetInput();
        Move();
    }
    public boolean getCameraMoving()
    {
        return CameraMoving;
    }
    public void resetPoz()
    {
        x=0;
        y=0;
    }
    public boolean getCmrLeft(){return CameraMovingLeft; }
    public boolean getCmrRight(){return CameraMovingRight; }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
