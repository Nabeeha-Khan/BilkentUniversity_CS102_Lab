import javax.swing.*;

class GameFrame extends JFrame 
{
    private Ship ship;

    public GameFrame(String playerName, int speed) 
    {
        super("Player: " + playerName + " | Score: 0 | Life: 3");
        ship = new Ship(playerName);
        GamePanel gamePanel = new GamePanel(ship, speed, this);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(gamePanel);
        setVisible(true);
    }

    public void updateTitle(String name, int score, int life)
    {
        this.setTitle("Player: " + name + " | Score: " + score + "| Life: " + life);
    }
}
