// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Turtle extends JComponent
{
    // AI Characteristics
    private int x;
    private int y;
    private int changeX = -1;
    private int changeY = 0;
    private String status;
    private boolean isDead = false;
    private boolean inShell = false;
    private boolean gone = false;
    private boolean faceRight = false;
    private int deathCount = 0;
    private int inShellCount = 0;
    
    // red
    private Image gr1;
    private Image gr2;
    private Image gl1;
    private Image gl2;
    private Image gs1;
    private Image gs2;
    // Red
    private Image rr1;
    private Image rr2;
    private Image rl1;
    private Image rl2;
    private Image rs1;
    private Image rs2;
    // Dark
    private Image dr1;
    private Image dr2;
    private Image dl1;
    private Image dl2;
    private Image ds1;
    private Image ds2;
    
    
    public Turtle (int a, int b, String color) // Constructor
    {
        gr1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "greenright1.png").getImage();
        gr2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "greenright2.png").getImage();
        gl1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "greenleft1.png").getImage();
        gl2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "greenleft2.png").getImage();
        gs1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "greenshell1.png").getImage();
        gs2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "greenshell2.png").getImage();
        rr1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "redright1.png").getImage();
        rr2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "redright2.png").getImage();
        rl1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "redleft1.png").getImage();
        rl2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "redleft2.png").getImage();
        rs1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "redshell1.png").getImage();
        rs2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "redshell2.png").getImage();
        dr1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "darkright1.png").getImage();
        dr2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "darkright2.png").getImage();
        dl1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "darkleft1.png").getImage();
        dl2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "darkleft2.png").getImage();
        ds1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "darkshell1.png").getImage();
        ds2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Turtle" + File.separator + "darkshell2.png").getImage();
        
        x = a;
        y = b;
        status = color;
    }
    
    public void paintComponent(Graphics g, MarioCanvas panel) // Places on canvas
    {
        int count = panel.getBlockCount();
        
        if(faceRight) // Facing right
        {
            if(panel.getLevelNum() != 2) // Not level 2
            {
                if(status.equals("green")) // Green
                {
                    if(isDead) // Killed by shell or fireball
                    {
                        g.drawImage(gr1, x, y, 50, 80, null);
                        deathCount++;
                    }
                    else if(inShell) // Killed by mario
                    {
                        g.drawImage(gs1, x, y+40, 50, 40, null);
                        inShellCount++;
                        if(inShellCount == 750)
                        {
                            inShell = false;
                            changeX = 1;
                        }
                    }
                    else // Walking
                    {
                        if (count < 16)
                        {
                            g.drawImage(gr1, x, y, 50, 80, null);
                        }
                        else if (count < 31)
                        {
                            g.drawImage(gr2, x, y, 50, 80, null);
                        }
                        else if (count < 46)
                        {
                            g.drawImage(gr1, x, y, 50, 80, null);
                        }
                        else
                        {
                            g.drawImage(gr2, x, y, 50, 80, null);
                        }
                    }
                }
                else // Red
                {
                    if(isDead) // Killed by shell or fireball
                    {
                        g.drawImage(rr1, x, y, 50, 80, null);
                        deathCount++;
                    }
                    else if(inShell) // Killed by mario
                    {
                        g.drawImage(rs1, x, y+40, 50, 40, null);
                        inShellCount++;
                        if(inShellCount == 750)
                        {
                            inShell = false;
                            changeX = 1;
                        }
                    }
                    else // Walking
                    {
                        if (count < 16)
                        {
                            g.drawImage(rr1, x, y, 50, 80, null);
                        }
                        else if (count < 31)
                        {
                            g.drawImage(rr2, x, y, 50, 80, null);
                        }
                        else if (count < 46)
                        {
                            g.drawImage(rr1, x, y, 50, 80, null);
                        }
                        else
                        {
                            g.drawImage(rr2, x, y, 50, 80, null);
                        }
                    }
                }
                
            }
            else // Level 2
            {
                if(isDead) // Killed by shell or fireball
                {
                    g.drawImage(dr1, x, y, 50, 80, null);
                    deathCount++;
                }
                else if(inShell) // Killed by mario
                {
                    g.drawImage(ds1, x, y+40, 50, 40, null);
                    inShellCount++;
                    if(inShellCount == 750)
                    {
                        inShell = false;
                        changeX = 1;
                    }
                }
                else // Walking
                {
                    if (count < 16)
                    {
                        g.drawImage(dr1, x, y, 50, 80, null);
                    }
                    else if (count < 31)
                    {
                        g.drawImage(dr2, x, y, 50, 80, null);
                    }
                    else if (count < 46)
                    {
                        g.drawImage(dr1, x, y, 50, 80, null);
                    }
                    else
                    {
                        g.drawImage(dr2, x, y, 50, 80, null);
                    }
                }
            }
        }
        else // Facing left
        {
            if(panel.getLevelNum() != 2) // Not level 2
            {
                if(status.equals("green")) // Green
                {
                    if(isDead) // Killed by shell or fireball
                    {
                        g.drawImage(gl1, x, y, 50, 80, null);
                        deathCount++;
                    }
                    else if(inShell) // Killed by mario
                    {
                        g.drawImage(gs1, x, y+40, 50, 40, null);
                        inShellCount++;
                        if(inShellCount == 750)
                        {
                            inShell = false;
                            changeX = -1;
                        }
                    }
                    else // Walking
                    {
                        if (count < 16)
                        {
                            g.drawImage(gl1, x, y, 50, 80, null);
                        }
                        else if (count < 31)
                        {
                            g.drawImage(gl2, x, y, 50, 80, null);
                        }
                        else if (count < 46)
                        {
                            g.drawImage(gl1, x, y, 50, 80, null);
                        }
                        else
                        {
                            g.drawImage(gl2, x, y, 50, 80, null);
                        }
                    }
                }
                else // Red
                {
                    if(isDead) // Killed by shell or fireball
                    {
                        g.drawImage(rl1, x, y, 50, 80, null);
                        deathCount++;
                    }
                    else if(inShell) // Killed by mario
                    {
                        g.drawImage(rs1, x, y+40, 50, 40, null);
                        inShellCount++;
                        if(inShellCount == 750)
                        {
                            inShell = false;
                            changeX = -1;
                        }
                    }
                    else // Walking
                    {
                        if (count < 16)
                        {
                            g.drawImage(rr1, x, y, 50, 80, null);
                        }
                        else if (count < 31)
                        {
                            g.drawImage(rl2, x, y, 50, 80, null);
                        }
                        else if (count < 46)
                        {
                            g.drawImage(rl1, x, y, 50, 80, null);
                        }
                        else
                        {
                            g.drawImage(rl2, x, y, 50, 80, null);
                        }
                    }
                }
                
            }
            else // Level 2
            {
                if(isDead) // Killed by shell or fireball
                {
                    g.drawImage(dl1, x, y, 50, 80, null);
                    deathCount++;
                }
                else if(inShell) // Killed by mario
                {
                    g.drawImage(ds1, x, y+40, 50, 40, null);
                    inShellCount++;
                    if(inShellCount == 750)
                    {
                        inShell = false;
                        changeX = -1;
                    }
                }
                else // Walking
                {
                    if (count < 16)
                    {
                        g.drawImage(dl1, x, y, 50, 80, null);
                    }
                    else if (count < 31)
                    {
                        g.drawImage(dl2, x, y, 50, 80, null);
                    }
                    else if (count < 46)
                    {
                        g.drawImage(dl1, x, y, 50, 80, null);
                    }
                    else
                    {
                        g.drawImage(dl2, x, y, 50, 80, null);
                    }
                }
            }
        }
    }
    
    public void setDefeat(boolean b) // Sets form of death (true if not crushed)
    {
        if(b)
            isDead = true;
        else
            inShell = true;
    }
    
    public int getX() // Returns x
    {
        return x;
    }
    
    public int getY() // Returns y
    {
        return x;
    }
    
    public boolean getGone() // Returns if turtle is gone
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
    
    public boolean touched (Mario player) // Checks if player has been hurt by the enemy
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
        else if (inShell)
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