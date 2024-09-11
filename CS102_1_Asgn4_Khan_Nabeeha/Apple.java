import java.awt.*;

class Apple implements InteractableDrawing 
{
    private Rectangle rectangle;

    public Apple(int x, int y, int size) 
    {
        rectangle = new Rectangle(x, y, size, size);
    }

    @Override
    public boolean intersects(Ship s) 
    {
        return rectangle.intersects(s.getRectangle());
    }

    @Override
    public void interact(Ship s) 
    {
        s.increaseScore();
    }

    @Override
    public boolean moveLeft(int speed) 
    {
        rectangle.setLocation(rectangle.x - speed, rectangle.y);
        return rectangle.getX() < 0;
    }

    @Override
    public void draw(Graphics g) 
    {
        g.setColor(Color.GREEN);
        g.fillOval(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}