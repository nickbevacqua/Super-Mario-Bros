// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Bowser extends JComponent
{
    
    // AI Characteristics
    private int x;
    private int y;
    private int changeX = 0;
    private int changeY = 0;
    private String status;
    
    
    public Bowser(Mario player, String color)
    {
        
        x = player.getX() + 500;
        y = 620;
        status = color;
    }
    
    public void paintComponent(Graphics g)
    {
        g.setColor(new Color(50,205,50));
        g.fillRect(x,y,20,20);
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return x;
    }
    
    public void act() // Will act as a green turtle but does not fall off cliffs
    {
        if(x > 0 || changeX > 0)
            x += changeX;
        
        if (changeY == 1)
            changeY = 0;
        
        if(y!=650 || changeY<0)
            y += changeY;
    }
}