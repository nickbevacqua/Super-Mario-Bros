// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

// Panel which will contain the main menu
public class MarioTitle extends JPanel implements ActionListener, KeyListener
{
    private int speed = 5; // Speed of timer
    private Timer time = new Timer (speed,this); // Timer that controls movement
    private static int highScore = 0;
    private boolean showInstructions = false;
    private MarioFrame window;
    
    // Images
    private Image back;
    private Image mario;
    private Image c1;
    
    
    public MarioTitle (MarioFrame x) // Starts the timer and listeners
    {
        back = new ImageIcon (".." + File.separator + "Pics" + File.separator + "Level" + File.separator + "main.gif").getImage();
        mario =  new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallstillright.gif").getImage();
        c1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Coin" + File.separator + "coin1.png").getImage();
        
        window = x;
        time.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
    }
    
    public int getHighScore()
    {
        return highScore;
    }
    
    public void setHighScore(int x)
    {
        if(x > highScore)
            highScore = x;
    }
    
    public void actionPerformed (ActionEvent e)
    {
        repaint();
    }
    
    public void keyPressed(KeyEvent e)
    {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_Q) // Moving left
        {
            setInstructions();
        }
        if (c == KeyEvent.VK_ENTER) // Moving right
        {
            start();
        }
    }
    
    public void keyReleased(KeyEvent e)
    {
        
    }
    
    public void keyTyped(KeyEvent e)
    {
        
    }
    
    public void paintComponent (Graphics g) // Fills in objects
    {
        super.paintComponent(g);
        g.drawImage(back, 0, 0, 1000, 720, null);
        g.drawImage(mario, 50, 596, 36, 50, null);
        g.setColor(new Color(255,255,255));
        Font top = new Font("Monospaced", Font.PLAIN, 20);
        g.setFont(top);
        // Displays score
        g.drawString("MARIO", 75, 25);
        g.drawString("0000000" , 75, 45);
        // Displays coins collected
        g.drawImage(c1, 185, 8, 10, 20, null);
        g.drawString(" * 00" , 200, 25);
        // Displays level
        g.drawString("WORLD", 700, 25);
        g.drawString("1-1", 700, 45);
        // Displays time left
        g.drawString("TIME", 900, 25);
        // Displays options
        if(showInstructions)
        {
            g.setColor(Color.BLACK);
            g.fillRect(200, 430, 600, 100);
            g.setColor(new Color(255,255,255));
            g.drawString("Press LEFT and RIGHT to move sideways.", 220, 460);
            g.drawString("Press UP to jump and DOWN to crouch.", 220, 480);
            g.drawString("Press P to pause.", 220, 500);
            g.drawString("Press SPACE to throw fireballs with the powerup.", 220, 520);
            g.setColor(Color.YELLOW);
            g.drawString("Made by Nick Bevacqua", 0,715);
        }
        else
        {
            g.setColor(Color.BLACK);
            g.fillRect(200, 430, 600, 100);
            g.setColor(new Color(255,255,255));
            g.drawString("Press ENTER to Play.", 220, 460);
            g.drawString("Press Q for Instructions.", 220, 480);
            g.drawString("High Score: " + highScore, 220, 500);
        }
    }
    
    public void setInstructions()
    {
        if(showInstructions)
            showInstructions = false;
        else
            showInstructions = true;
    }
    
    public void start()
    {
        removeKeyListener(this);
        window.toLevelScreen(3, 0, 0, "1-1", 1);
    }
    
}