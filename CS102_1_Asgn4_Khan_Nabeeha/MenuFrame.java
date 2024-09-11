import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuFrame extends JFrame 
{
    private JTextField playerNameField;
    private JTextField speedField;

    public MenuFrame() 
    {
        setTitle("Space Adventure Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        // Create panels
        JPanel panel1 = new JPanel(new GridLayout(1, 2));
        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        JPanel panel3 = new JPanel();

        // Add labels and text fields
        JLabel nameLabel = new JLabel("Player's Name:");
        JLabel speedLabel = new JLabel("Speed:");

        playerNameField = new JTextField();
        speedField = new JTextField();

        panel1.add(nameLabel);
        panel1.add(playerNameField);
        panel2.add(speedLabel);
        panel2.add(speedField);

        // Add start button
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                String playerName = playerNameField.getText();
                String speedInput = speedField.getText();

                // Validate input (check if not empty and speed is numeric)
                if (playerName.isEmpty() || speedInput.isEmpty()) 
                {
                    JOptionPane.showMessageDialog(MenuFrame.this, "Please fill in all fields.");
                }
                else 
                {
                    
                    boolean condition = true;
                    for(int i = 0; i < speedInput.length(); i++)
                    {
                        if(!Character.isDigit(speedInput.charAt(i)))
                            condition = false;
                    }
                    if(condition)
                    {
                        int speed = Integer.parseInt(speedInput);
                        // Hide this frame and open the game frame
                        if(speed > 0)
                        {
                            setVisible(false);
                            new GameFrame(playerName, speed);
                        }
                        else
                            JOptionPane.showMessageDialog(MenuFrame.this, "Speed must be a positive integer.");
                    }
                    else
                        JOptionPane.showMessageDialog(MenuFrame.this, "Speed must be a positive integer.");
                }
            }
        });

        panel3.add(startButton);

        // Add panels to the frame
        add(panel1);
        add(panel2);
        add(panel3);

        setSize(300, 150);
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        new MenuFrame();
    }
}