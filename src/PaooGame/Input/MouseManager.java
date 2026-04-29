//package PaooGame.Input;
//
//import PaooGame.RefLinks;
//
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class MouseManager extends MouseAdapter {
//    private int mouseX, mouseY;
//    private boolean leftPressed;
//    public RefLinks refLinks;
//
//    public MouseManager(RefLinks refLinks) {
//        mouseX = 0;
//        mouseY = 0;
//        leftPressed = false;
//        this.refLinks=refLinks;
//        refLinks.setMouseManager(this);
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1)
//            leftPressed = true;
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//        if(e.getButton() == MouseEvent.BUTTON1)
//            leftPressed = false;
//    }
//
//    @Override
//    public void mouseMoved(MouseEvent e) {
//        mouseX = e.getX();
//        mouseY = e.getY();
//    }
//
//    @Override
//    public void mouseDragged(MouseEvent e) {
//        mouseX = e.getX();
//        mouseY = e.getY();
//    }
//
//    public boolean isLeftPressed() {
//        return leftPressed;
//    }
//
//    public int getMouseX() {
//        return mouseX;
//    }
//
//    public int getMouseY() {
//        return mouseY;
//    }
//}