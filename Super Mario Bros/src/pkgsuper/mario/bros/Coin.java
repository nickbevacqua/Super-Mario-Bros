// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Coin extends JComponent// Currency which is counted and gives score
{
    private int x;
    private int y;
    private int timeCount;
    
    // PC FILES
    private Image c1;
    private Image c2;
    private Image c3;
    private Image c4;
    
    
    public Coin(int b, int h) // Sets up location in constructor
    {
        c1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Coin" + File.separator + "coin1.png").getImage();
        c2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Coin" + File.separator + "coin2.png").getImage();
        c3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Coin" + File.separator + "coin3.png").getImage();
        c4 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Coin" + File.separator + "coin4.png").getImage();
        
        x = b;
        y = h;
    }
    
    public void paintComponent(Graphics g, MarioCanvas panel) // Places on canvas
    {
        int count = panel.getBlockCount();
        if (count < 16)
        {
            g.drawImage(c1, x, y, 30, 50, null);
        }
        else if (count < 31)
        {
            g.drawImage(c2, x, y, 30, 50, null);
        }
        else if (count < 46)
        {
            g.drawImage(c3, x, y, 30, 50, null);
        }
        else
        {
            g.drawImage(c4, x, y, 30, 50, null);
        }
        
        if(x == 180 || x == 390)
        {
            timeCount++;
            if(timeCount == 10)
            {
                hit(panel.getMario());
            }
        }
    }
    
    public int getX() // Returns current x-coordinate
    {
        return x;
    }
    
    public int getY() // Returns current y-coordinate
    {
        return y;
    }
    
    public boolean touched (Mario player) // Checks if player has touched a coin
    {
        if(player.getX() <= x+20 && player.getX() >= x-20)
        {
            if(player.getY() <= y+40 && player.getY() >= y-40)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    public void hit(Mario player) // Will give points and a coin to player
    {
        player.collectCoin();
    }
    
}