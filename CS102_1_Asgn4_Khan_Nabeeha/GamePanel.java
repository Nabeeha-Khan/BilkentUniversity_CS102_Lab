import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

class GamePanel extends JPanel implements ActionListener, MouseMotionListener 
{
    private Ship ship;
    private GameFrame frame;
    private ArrayList<InteractableDrawing> objects;
    private Timer timer;
    private int speed;
    private Random random;

    public GamePanel(Ship ship, int speed, GameFrame frame) 
    {
        this.ship = ship;
        this.speed = speed;
        this.frame = frame;
        objects = new ArrayList<>();
        timer = new Timer(50, this); 
        timer.start();
        random = new Random();
        addMouseMotionListener(this);
        initializeObjects();
    }

    private void initializeObjects() 
    { 
        {
            // Generate initial objects (apples and bombs)
            for (int i = 0; i < 5; i++) 
            {
                objects.add(new Apple(getWidth(),  (getHeight() + random.nextInt(600)) % 600, 30));
                objects.add(new Bomb(getWidth(), (getHeight() + random.nextInt(600)) % 600, 30));
            }
        }
    }
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for (int i = 0; i < objects.size(); i++) 
        {
            InteractableDrawing object = objects.get(i);
            if (object.moveLeft(speed)) 
            {
                objects.remove(i);
                i--;
            }
            if (object.intersects(ship)) 
            {
                object.interact(ship);
                objects.remove(i);
                i--;
            }
        }
        // Add new objects if needed
        if (random.nextInt(50) < 5) 
        {
            objects.add(new Apple(getWidth() ,  (getHeight() + random.nextInt(600)) % 600, 30));
        }
        if (random.nextInt(75) < 5) 
        { 
            objects.add(new Bomb(getWidth(), (getHeight() + random.nextInt(600)) % 600, 30));
        }
        repaint();

        // Check if life is zero
        if (ship.getLife() <= 0) 
        {
            timer.stop();
            int choice = JOptionPane.showConfirmDialog(this, "Game over! Play again?", "Game Over", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) 
            {
                ship = new Ship(ship.getPlayerName());
                objects.clear();
                initializeObjects();
                timer.start();
            } 
            else 
            {
                System.exit(0); // Exit the application
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getWidth(), getHeight());
        ship.draw(g);
        for (InteractableDrawing object : objects) 
        {
            object.draw(g);
        }
        // Update title bar with score and life
        frame.updateTitle(ship.getPlayerName(), ship.getScore(), ship.getLife());
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        ship.setX(e.getX());
        ship.setY(e.getY());
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
    }

    public void addInteractableDrawing(InteractableDrawing object) 
    {
        objects.add(object);
    }
}
