// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Panel which will contain the main menu
public class MarioLevelScreen extends JPanel implements ActionListener
{
    private int speed = 5; // Speed of timer
    private Timer time = new Timer (speed,this); // Timer that controls movement
    private int timeCount = 0;
    private int lives;
    private int coins;
    private int score;
    private String level;
    private int status;
    private MarioFrame window;
    
    
    
    // Images
    private Image mario;
    private Image bigmario;
    private Image firemario;
    private Image c1;
    
    
    public MarioLevelScreen(int lifes, int c, int sc, String lev, int sts, MarioFrame frame) // Starts the timer and listeners
    {
        mario =  new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallstillright.png").getImage();
        bigmario =  new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigstillright.png").getImage();
        firemario =  new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firestillright.png").getImage();
        c1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Coin" + File.separator + "coin1.png").getImage();
        
        time.start();
        lives = lifes;
        coins = c;
        score = sc;
        level = lev;
        status = sts;
        window = frame;
    }
    
    public void paintComponent (Graphics g) // Fills in objects
    {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1000, 720);
        g.setColor(new Color(255,255,255));
        Font top = new Font("Monospaced", Font.PLAIN, 20);
        g.setFont(top);
        // Displays score
        g.drawString("MARIO", 75, 25);
        g.drawString("" + score , 75, 45);
        // Displays lives
        if(status == 1)
            g.drawImage(mario, 400, 300, 36, 50, null);
        else if(status == 2)
            g.drawImage(bigmario, 400, 280, 36, 90, null);
        else
            g.drawImage(firemario, 400, 280, 36, 90, null);
        g.drawString("X", 490, 330);
        g.drawString("" + lives, 550, 330);
        // Displays level
        g.drawString("WORLD", 420, 250);
        g.drawString(level, 520, 250);
        // Displays level
        g.drawString("WORLD", 700, 25);
        g.drawString(level, 700, 45);
        // Displays time left
        g.drawString("TIME", 900, 25);
        // Displays coins collected
        g.drawImage(c1, 185, 8, 10, 20, null);
        g.drawString(" * " + coins, 200, 25);
    }
    
    public void actionPerformed (ActionEvent e) // Takes one step by calling all methods
    {
        timeCount++;
        if(timeCount == 200)
            window.toGame(score);
        else
            repaint();
    }
    
}