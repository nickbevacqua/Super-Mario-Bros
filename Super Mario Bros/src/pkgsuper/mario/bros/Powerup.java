// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;

import javax.swing.*;

public class Powerup extends JComponent
{
    private int x;
    private int y;
    private int content;
    
    
    // Images
    private Image star1;
    private Image star2;
    private Image star3;
    private Image star4;
    private Image life;
    private Image mushroom;
    private Image flower1;
    private Image flower2;
    private Image flower3;
    private Image flower4;
    private Image c1;
    
    // 0 = NOTHING
    // 1 = MUSHROOM / FLOWER
    // 2 = STAR
    // 3 = COIN
    // 4 = 1UP
    
    public Powerup(Block b)
    {
        star1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "star1.png").getImage();
        star2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "star2.png").getImage();
        star3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "star3.png").getImage();
        star4 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "star4.png").getImage();
        life = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "1upmushroom.png").getImage();
        mushroom = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "mushroom.png").getImage();
        flower1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "flower1.png").getImage();
        flower2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "flower2.png").getImage();
        flower3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "flower3.png").getImage();
        flower4 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Powerup" + File.separator + "flower4.png").getImage();
        c1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Coin" + File.separator + "coin1.png").getImage();
        
        x = b.getX();
        y = b.getY()-50;
        content = b.getContent();
    }
    
    public void paintComponent(Graphics g, MarioCanvas panel)
    {
        int count = panel.getBlockCount();
        
        if (content == 1)
            if(panel.getMario().getStatus() == 1)
                g.drawImage(mushroom, x, y, 50, 50, null);
            else
            {
                if (count < 16)
                {
                    g.drawImage(flower1, x, y, 50, 50, null);
                }
                else if (count < 31)
                {
                    g.drawImage(flower2, x, y, 50, 50, null);
                }
                else if (count < 46)
                {
                    g.drawImage(flower3, x, y, 50, 50, null);
                }
                else
                {
                    g.drawImage(flower4, x, y, 50, 50, null);
                }
            }
        if (content == 2)
        {
            if (count < 16)
            {
                g.drawImage(star1, x, y, 50, 50, null);
            }
            else if (count < 31)
            {
                g.drawImage(star2, x, y, 50, 50, null);
            }
            else if (count < 46)
            {
                g.drawImage(star3, x, y, 50, 50, null);
            }
            else
            {
                g.drawImage(star4, x, y, 50, 50, null);
            }
        }
        if (content == 3)
        {
            g.drawImage(c1, x, y, 30, 50, null);
        }
        if (content == 4)
            g.drawImage(life, x, y, 50, 50, null);
    }
    
    public int getPowerUp()
    {
        return content;
    }
    
    public boolean touched (Mario player) // Checks if player has touched a coin
    {
        if(player.getX() <= x+50 && player.getX() >= x-50)
        {
            if(player.getY() <= y+50 && player.getY() >= y-50)
                return true;
            else
                return false;
        }
        else
            return false;
    }
    
    public void hit(Mario player) // Will give points and a coin to player
    {
        player.upgrade(content);
    }
    
}