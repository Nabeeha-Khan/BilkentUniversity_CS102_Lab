import java.awt.*;

interface InteractableDrawing 
{
    boolean intersects(Ship s);
    void interact(Ship s);
    boolean moveLeft(int speed);
    void draw(Graphics g);
}
