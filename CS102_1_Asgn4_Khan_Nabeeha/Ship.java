import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Ship 
{
    private String playerName;
    private int score;
    private int life;
    private Rectangle rectangle; // Rectangle representing the ship's position and size

    public Ship(String playerName) 
    {
        this.playerName = playerName;
        this.score = 0;
        this.life = 3;
        // Initialize ship's rectangle (for collision detection)
        this.rectangle = new Rectangle(0, 0, 50, 30); 
    }

    public String getPlayerName() 
    {
        return playerName;
    }

    public int getScore() 
    {
        return score;
    }

    public int getLife() 
    {
        return life;
    }

    public Rectangle getRectangle() 
    {
        return rectangle;
    }

    public void setX(int x) 
    {
        rectangle.x = x;
    }

    public void setY(int y) 
    {
        rectangle.y = y;
    }

    public void increaseScore() 
    {
        score++;
    }

    public void decreaseLife() 
    {
        life--;
    }

    public void draw(Graphics g) 
    {
        g.setColor(Color.WHITE);
        g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(Color.BLACK);
        g.drawString(playerName, rectangle.x + 10, rectangle.y + 20);
    }
}
