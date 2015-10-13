// Nick Bevacqua
//package Classes;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Goomba extends JComponent
{
    // Images
    private Image g1;
    private Image g2;
    private Image gX;
    private Image gD1;
    private Image gD2;
    private Image gDX;
    
    // AI Characteristics
    private int x;
    private int y;
    private boolean isDead = false;
    private boolean isCrushed = false;
    private int changeX = -1;
    private int changeY = 0;
    private boolean gone = false;
    private int deathCount = 0;
    
    public Goomba(int a, int b) // Constructor
    {
        
        g1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Goomba" + File.separator + "goomba1.png").getImage();
        g2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Goomba" + File.separator + "goomba2.png").getImage();
        gX = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Goomba" + File.separator + "goombadead.png").getImage();
        gD1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Goomba" + File.separator + "goombaD1.png").getImage();
        gD2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Goomba" + File.separator + "goombaD2.png").getImage();
        gDX = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Goomba" + File.separator + "goombaDdead.png").getImage();
        
        x = a;
        y = b;
    }
    
    public void paintComponent(Graphics g, MarioCanvas panel) // Places on canvas
    {
        int count = panel.getBlockCount();
        if(panel.getLevelNum() != 2) // Not level 2
        {
            if(isDead) // Killed by shell or fireball
            {
                g.drawImage(g1, x, y, 50, 50, null);
                deathCount++;
            }
            else if(isCrushed) // Killed by mario
            {
                g.drawImage(gX, x, y+30, 50, 20, null);
                deathCount++;
            }
            else // Walking
            {
                if (count < 16)
                {
                    g.drawImage(g1, x, y, 50, 50, null);
                }
                else if (count < 31)
                {
                    g.drawImage(g2, x, y, 50, 50, null);
                }
                else if (count < 46)
                {
                    g.drawImage(g1, x, y, 50, 50, null);
                }
                else
                {
                    g.drawImage(g2, x, y, 50, 50, null);
                }
            }
        }
        else // Level 2
        {
            if(isDead) // Killed by shell or fireball
            {
                g.drawImage(gD1, x, y, 50, 50, null);
            }
            else if(isCrushed) // Killed by mario
            {
                g.drawImage(gDX, x, y+30, 50, 20, null);
            }
            else // Walking
            {
                if (count < 16)
                {
                    g.drawImage(gD1, x, y, 50, 50, null);
                }
                else if (count < 31)
                {
                    g.drawImage(gD2, x, y, 50, 50, null);
                }
                else if (count < 46)
                {
                    g.drawImage(gD1, x, y, 50, 50, null);
                }
                else
                {
                    g.drawImage(gD2, x, y, 50, 50, null);
                }
            }
        }
    }
    
    public void setDefeat(boolean b) // Sets form of death (true if not crushed)
    {
        if(b)
            isDead = true;
        else
            isCrushed = true;
    }
    
    public int getX() // Returns x
    {
        return x;
    }
    
    public int getY() // Returns y
    {
        return x;
    }
    
    public boolean defeated()
    {
        if(isDead || isCrushed)
            return true;
        else
            return false;
    }
    
    public boolean getGone() // Returns if goomba is gone
    {
        return gone;
    }
    
    public void setX(int a) // Sets x
    {
        x = a;
    }
    
    public boolean onTopOf (Mario player) // Checks if player is on top of floating block
    {
        if(player.getStatus() == 1) // Small mario
        {
            if(player.getX() <= x+36 && player.getX() >= x-36)
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
            if(player.getX() <= x+36 && player.getX() >= x-36)
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
    
    public boolean touched (Mario player) // Checks if player has been hurt by the enemy
    {
        if(player.getStatus() == 1)
        {
            if(player.getX() < x+36 && player.getX() > x-36)
            {
                if(player.getY() < y+10 && player.getY() > y-10)
                    return true;
                else
                    return false;
            }
            return false;
        }
        else
        {
            if(player.getX() < x+36 && player.getX() > x-36)
            {
                if(player.getY() < y+90 && player.getY() > y-50)
                    return true;
                else
                    return false;
            }
            return false;
        }
    }
    
    public boolean touched (Fireball b) // Checks if a fireball has defeated the enemy
    {
        if(b.getX() < x+50 && b.getX() > x-20)
        {
            if(b.getY() < y+50 && b.getY() > y-10)
                return true;
            else
                return false;
        }
        return false;
    }
    
    public void act(int[] holebegins, int[] holeends) // Moves straight but will turn around when hitting wall
    {
        if(deathCount == 10)
        {
            gone = true;
            y = 900;
        }
        if(isDead)
        {
            
        }
        else if (isCrushed)
        {
            changeX = 0;
            changeY = 0;
        }
        else
        {
            x += changeX; // Moving the player sideways
            
            boolean onHole = false; // Checks if player is over a hole
            
            for(int b = 0; b < holebegins.length; b++)
            {
                if(x >= holebegins[b] && x <= holeends[b]-50)
                {
                    if(y >= 596)
                    {
                        changeY = 2;
                        onHole = true;
                    }
                }
            }
            
            if(!onHole) // Checks if not falling down hole
            {
                if (changeY == 2 &&  y == 596) // Checks if goomba is on bottom surface
                {
                    changeY = 0;
                }
            }
            else
            {
                if(y == 700) // Checks if goomba has died
                {
                    isDead = true;
                }
            }
        }
    }
    
}