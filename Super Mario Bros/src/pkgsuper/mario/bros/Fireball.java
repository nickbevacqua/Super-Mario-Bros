// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Fireball extends JComponent// Currency which is counted and gives score
{
    private int x;
    private int y;
    private int changeX;
    private int changeY = 1;
    private MarioCanvas screen;
    private boolean isGone = false;
    private int num;
    
    
    // Images
    private Image f1;
    private Image f2;
    private Image f3;
    private Image f4;
    
    
    public Fireball (Mario player, int a) // Sets up location in constructor
    {
        f1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "fireball1.png").getImage();
        f2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "fireball2.png").getImage();
        f3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "fireball3.png").getImage();
        f4 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "fireball4.png").getImage();
        
        if(player.getFaceRight() == true)
        {
            x = player.getX() + 40;
            changeX = 3;
        }
        else
        {
            x = player.getX() - 4;
            changeX = -3;
        }
        
        y = player.getY() + 10;
        num = a;
    }
    
    public void paintComponent(Graphics g, MarioCanvas panel) // Places on canvas
    {
        int count = panel.getBlockCount();
        screen = panel;
        if(!isGone)
        {
            if (count < 16)
            {
                g.drawImage(f1, x, y, 20, 20, null);
            }
            else if (count < 31)
            {
                g.drawImage(f2, x, y, 20, 20, null);
            }
            else if (count < 46)
            {
                g.drawImage(f3, x, y, 20, 20, null);
            }
            else
            {
                g.drawImage(f4, x, y, 20, 20, null);
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
    
    public int getNum()
    {
        return num;
    }
    
    public void setX (int a)
    {
        x = a;
    }
    
    public void act()
    {
        x += changeX;
        if(y == 626)
            changeY = -1;
        if(y == 606)
            changeY = 1;
        y += changeY;
        
        if(x < -10 || x > 1000)
        {
            screen.removeFireball(num);
            isGone = true;
        }
    }
    
    public boolean touched (Goomba b) // Checks if fireball has hit a goomba
    {
        if(b.getX() <= x+50 && b.getX() >= x-50)
        {
            if(b.getY() <= y+50 && b.getY() >= y-50)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    public void hit(Goomba b) // Will give points and a coin to player
    {
        b.setDefeat(true);
    }
    
}