// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import javax.swing.*;

public class Block extends JComponent// Block containing a powerup, coin, or nothing
{
    private int x;
    private int y;
    private int c; // Content
    
    
    // Images
    private Image q1;
    private Image q2 ;
    private Image q3;
    private Image q4;
    private Image brick;
    private Image hit;
    
    
    public Block(int b, int h, int a, int level) // Constructor
    {
        q1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Block" + File.separator + "questionblock1.png").getImage();
        q2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Block" + File.separator + "questionblock2.png").getImage();
        q3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Block" + File.separator + "questionblock3.png").getImage();
        q4 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Block" + File.separator + "questionblock4.png").getImage();
        if(level == 2)
            brick = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Block" + File.separator + "brickdark.png").getImage();
        else
            brick = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Block" + File.separator + "brick.png").getImage();
        hit = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Block" + File.separator + "hitquestionblock.jpg").getImage();
        
        x = b;
        y = h;
        c = a;
    }
    
    public void paintComponent(Graphics g, MarioCanvas panel) // Places on canvas
    {
        int count = panel.getBlockCount();
        if (c == 1 || c == 3) // Has something
        {
            // Checks animations of ?
            if (count < 16)
            {
                g.drawImage(q1, x, y, 50, 50, null);
            }
            else if (count < 31)
            {
                g.drawImage(q2, x, y, 50, 50, null);
            }
            else if (count < 46)
            {
                g.drawImage(q3, x, y, 50, 50, null);
            }
            else
            {
                g.drawImage(q4, x, y, 50, 50, null);
            }
        }
        else if (c == 0 || c == 2)// Brick
            g.drawImage(brick, x, y, 50, 50, null);
        else if (c == 4)
            g.drawImage(null, x, y, 50, 50, null);
        else // Used
            g.drawImage(hit, x, y, 50, 50, null);
        
    }
    
    public int getX() // Returns current x-coordinate
    {
        return x;
    }
    
    public int getY() // Returns current y-coordinate
    {
        return y;
    }
    
    public int getContent() // Returns content
    {
        return c;
    }
    
    public void setContent(int x) // Sets content
    {
        c = x;
    }
    
    public boolean onTopOf (Mario player) // Checks if player is on top of floating block
    {
        if(player.getStatus() == 1) // Small mario
        {
            if(player.getX() <= x+50 && player.getX() >= x-50)
            {
                if(player.getY() == y-50)
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
        else // Big mario
        {
            if(player.getX() <= x+50 && player.getX() >= x-50)
            {
                if(player.getY() == y-90)
                    return true;
                else
                    return false;
            }
            else
                return false;
        }
    }
    
    public boolean beneath (Mario player) // Checks if player is below floating block
    {
        if(player.getX() <= x+40 && player.getX() >= x-40)
        {
            if(player.getY() == y+50)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    public boolean aside (Mario player)
    {
        if((player.getX() == x-36 || player.getX() == x+50))
        {
            if(player.getStatus() == 1) // Small mario
            {
                if(player.getY() >= y-50 && player.getY() <= y+50)
                    return true;
                else
                    return false;
            }
            else // Big mario
            {
                if(player.getY() >= y+90 && player.getY() <= y+50)
                    return true;
                else
                    return false;
            }
        }
        return false;
    }
}