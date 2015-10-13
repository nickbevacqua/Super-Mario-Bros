// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Mario extends JComponent // Controllable character
{
    // Controls of character
    private MarioCanvas screen;
    private int x = 50;
    private int y = 596; // Ground = 632
    private int changeX = 0;
    private int changeY = 0;
    private int jumpY = 600;
    private int holdCount = 0;
    private int coinsCollected = 0;
    private int lives = 3;
    private int status = 1;
    private int walkNum = 1; // For animation
    private int disTraveled = 50; // Total distance
    private boolean faceRight = true;
    private boolean crouch = false;
    private boolean throwFire = false;
    private int throwFireCount = 1; // Timing fire throw animation
    private Block floor; // Block below player if in air
    private boolean gone = false; // In door to castle
    private boolean onFlag = false;
    private boolean isInvincible = false;
    private boolean fromStar = false;
    private boolean hasDied = false;
    private int deadCount = 0;
    private int invincibleCount = 0;
    
    // Images
    
    // Dead sprite
    private Image d;
    
    // Sprites as a small character
    private Image sr1;
    private Image w1r1;
    private Image w2r1;
    private Image jr1;
    private Image hr1;
    private Image sl1;
    private Image w1l1;
    private Image w2l1;
    private Image jl1;
    private Image hl1;
    
    // Sprites as a big character
    private Image sr2;
    private Image w1r2;
    private Image w2r2;
    private Image jr2;
    private Image cr2;
    private Image hr2;
    private Image sl2;
    private Image w1l2;
    private Image w2l2;
    private Image jl2;
    private Image cl2;
    private Image hl2;
    
    // Sprites as a fireball character
    private Image sr3;
    private Image w1r3;
    private Image w2r3;
    private Image jr3;
    private Image cr3;
    private Image hr3;
    private Image sl3;
    private Image w1l3;
    private Image w2l3;
    private Image jl3;
    private Image tr3;
    private Image tl3;
    private Image cl3;
    private Image hl3;
    
    
    public Mario(MarioCanvas x) // Constructor
    {
        super();
        screen = x;
        // Dead sprite
        d = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smalldied.png").getImage();
        
        // Sprites as a small character
        sr1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallstillright.png").getImage();
        w1r1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallwalkright.png").getImage();
        w2r1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallwalk2right.png").getImage();
        jr1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smalljumpright.png").getImage();
        hr1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallhangright.png").getImage();
        sl1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallstillleft.png").getImage();
        w1l1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallwalkleft.png").getImage();
        w2l1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallwalk2left.png").getImage();
        jl1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smalljumpleft.png").getImage();
        hl1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "smallhangleft.png").getImage();
        
        
        
        // Sprites as a big character
        sr2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigstillright.png").getImage();
        w1r2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigwalkright.png").getImage();
        w2r2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigwalk2right.png").getImage();
        jr2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigjumpright.png").getImage();
        cr2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigcrouchright.png").getImage();
        hr2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bighangright.png").getImage();
        sl2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigstillleft.png").getImage();
        w1l2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigwalkleft.png").getImage();
        w2l2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigwalk2left.png").getImage();
        jl2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigjumpleft.png").getImage();
        cl2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bigcrouchleft.png").getImage();
        hl2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "bighangleft.png").getImage();
        
        // Sprites as a fireball character
        sr3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firestillright.png").getImage();
        w1r3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firewalkright.png").getImage();
        w2r3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firewalk2right.png").getImage();
        jr3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firejumpright.png").getImage();
        cr3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firecrouchright.png").getImage();
        hr3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firehangright.png").getImage();
        sl3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firestillleft.png").getImage();
        w1l3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firewalkleft.png").getImage();
        w2l3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firewalk2left.png").getImage();
        jl3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firejumpleft.png").getImage();
        tr3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firethrowright.png").getImage();
        tl3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firethrowleft.png").getImage();
        cl3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firecrouchleft.png").getImage();
        hl3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Mario" + File.separator + "firehangleft.png").getImage();
    }
    
    public void paintComponent(Graphics g) // Places object on canvas based on status
    {
        if (hasDied) // Dead
            g.drawImage(d, x, y, 36, 50, null);
        
        else if(gone)
            g.drawImage(d, x, y, 0, 0, null);
        
        else
        {
            if(!isInvincible || (invincibleCount % 2 == 0))
            {
                if (faceRight) // Facing right
                {
                    if (status == 1) // Small
                    {
                        if(onFlag) // On the flag
                            g.drawImage(hr1, x, y, 36, 50, null);
                        else if (changeX == 0 && changeY == 0) // Still
                            g.drawImage(sr1, x, y, 36, 50, null); // Not moving since cannot crouch
                        else if (changeY == -2 || changeY == 2) // Jumping
                            g.drawImage(jr1, x, y, 36, 50, null);
                        else // Walking
                        {
                            if(walkNum <= 10) // First walk animation
                            {
                                g.drawImage(w1r1, x, y, 36, 50, null);
                                walkNum++;
                            }
                            else // Second walk animation
                            {
                                g.drawImage(w2r1, x, y, 36, 50, null);
                                if (walkNum == 20)
                                    walkNum = 1;
                                else
                                    walkNum++;
                            }
                        }
                    }
                    else if (status == 2) // Big
                    {
                        if(onFlag) // On the flag
                            g.drawImage(hr2, x, y, 36, 90, null);
                        else if (changeX == 0 && changeY == 0) // Still
                        {
                            if(crouch) // Crouching
                                g.drawImage(cr2, x, y+30, 36, 60, null);
                            else
                                g.drawImage(sr2, x, y, 36, 90, null); // Not moving
                        }
                        else if (crouch && changeX == 0)
                            g.drawImage(cr2, x, y+30, 36, 60, null);
                        else if (changeY == -2 || changeY == 2) // Jumping
                            g.drawImage(jr2, x, y, 36, 90, null);
                        else // Walking
                        {
                            if(walkNum <=10) // First walk animation
                            {
                                g.drawImage(w1r2, x, y, 36, 90, null);
                                walkNum++;
                            }
                            else // Second walk animation
                            {
                                g.drawImage(w2r2, x, y, 36, 90, null);
                                if (walkNum == 20)
                                    walkNum = 1;
                                else
                                    walkNum++;
                            }
                        }
                    }
                    else // Fire Powered
                    {
                        if(onFlag) // On the flag
                            g.drawImage(hr3, x, y, 36, 90, null);
                        else if (throwFire == true)
                        {
                            g.drawImage(tr3, x, y, 36, 90, null);
                            throwFireCount++;
                            if(throwFireCount == 15)
                            {
                                throwFire = false;
                                throwFireCount = 1;
                            }
                        }
                        else if (changeX == 0 && changeY == 0) // Still
                        {
                            if(crouch) // Crouching
                                g.drawImage(cr3, x, y+30, 36, 60, null);
                            else
                                g.drawImage(sr3, x, y, 36, 90, null); // Not moving
                        }
                        else if (crouch && changeX == 0)
                            g.drawImage(cr3, x, y+30, 36, 60, null);
                        else if (changeY == -2 || changeY == 2) // Jumping
                            g.drawImage(jr3, x, y, 36, 90, null);
                        else // Walking
                        {
                            if(walkNum <=10) // First walk animation
                            {
                                g.drawImage(w1r3, x, y, 36, 90, null);
                                walkNum++;
                            }
                            else // Second walk animation
                            {
                                g.drawImage(w2r3, x, y, 36, 90, null);
                                if (walkNum == 20)
                                    walkNum = 1;
                                else
                                    walkNum++;
                            }
                        }
                    }
                }
                else // Facing left
                {
                    if (status == 1) // Small
                    {
                        if(onFlag) // On the flag
                            g.drawImage(hl1, x, y, 36, 50, null);
                        else if (changeX == 0 && changeY == 0) // Still
                            g.drawImage(sl1, x, y, 36, 50, null); // Not moving since cannot crouch
                        else if (changeY == -2 || changeY == 2) // Jumping
                            g.drawImage(jl1, x, y, 36, 50, null);
                        else // Walking
                        {
                            if(walkNum <=10) // First walk animation
                            {
                                g.drawImage(w1l1, x, y, 36, 50, null);
                                walkNum++;
                            }
                            else // Second walk animation
                            {
                                g.drawImage(w2l1, x, y, 36, 50, null);
                                if (walkNum == 20)
                                    walkNum = 1;
                                else
                                    walkNum++;
                            }
                        }
                    }
                    else if (status == 2) // Big
                    {
                        if(onFlag) // On the flag
                            g.drawImage(hl2, x, y, 36, 90, null);
                        else if (changeX == 0 && changeY == 0) // Still
                        {
                            if(crouch) // Crouching
                                g.drawImage(cl2, x, y+30, 36, 60, null);
                            else
                                g.drawImage(sl2, x, y, 36, 90, null); // Not moving
                        }
                        else if (crouch && changeX == 0)
                            g.drawImage(cl2, x, y+30, 36, 60, null);
                        else if (changeY == -2 || changeY == 2) // Jumping
                            g.drawImage(jl2, x, y, 36, 90, null);
                        else // Walking
                        {
                            if(walkNum <=10) // First walk animation
                            {
                                g.drawImage(w1l2, x, y, 36, 90, null);
                                walkNum++;
                            }
                            else // Second walk animation
                            {
                                g.drawImage(w2l2, x, y, 36, 90, null);
                                if (walkNum == 20)
                                    walkNum = 1;
                                else
                                    walkNum++;
                            }
                        }
                    }
                    else // Fire Powered
                    {
                        if(onFlag) // On the flag
                            g.drawImage(hl3, x, y, 36, 50, null);
                        else if(throwFire == true)
                        {
                            g.drawImage(tl3, x, y, 36, 90, null);
                            throwFireCount++;
                            if(throwFireCount == 15)
                            {
                                throwFire = false;
                                throwFireCount = 1;
                            }
                        }
                        else if (changeX == 0 && changeY == 0) // Still
                        {
                            if(crouch) // Crouching
                                g.drawImage(cl3, x, y+30, 36, 60, null);
                            else
                                g.drawImage(sl3, x, y, 36, 90, null); // Not moving
                        }
                        else if (crouch && changeX == 0)
                            g.drawImage(cl3, x, y+30, 36, 60, null);
                        else if (changeY == -2 || changeY == 2) // Jumping
                            g.drawImage(jl3, x, y, 36, 90, null);
                        else // Walking
                        {
                            if(walkNum <=10) // First walk animation
                            {
                                g.drawImage(w1l3, x, y, 36, 90, null);
                                walkNum++;
                            }
                            else // Second walk animation
                            {
                                g.drawImage(w2l3, x, y, 36, 90, null);
                                if (walkNum == 20)
                                    walkNum = 1;
                                else
                                    walkNum++;
                            }
                        }
                    }
                }
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
    
    public int getChangeX() // Returns x-change
    {
        return changeX;
    }
    
    public int getGround() // Returns coordinate jumped from
    {
        return jumpY;
    }
    
    public int getStatus() // Returns status
    {
        return status;
    }
    
    public int getHold() // Returns length of holding key
    {
        return holdCount;
    }
    
    public Block getFloor() // Returns the current floating block player was on
    {
        return floor;
    }
    
    public int getCoins() // Returns the current coins player has
    {
        return coinsCollected;
    }
    
    public int getDistance() // Returns distance traveled
    {
        return disTraveled;
    }
    
    public boolean getFaceRight()
    {
        return faceRight;
    }
    
    public int getLives() // Returns number of lives
    {
        return lives;
    }
    
    public boolean getInvincible()
    {
        return isInvincible;
    }
    
    public boolean getStar()
    {
        return fromStar;
    }
    
    public void setGround(int x) // Sets the level where the player jumped off of
    {
        jumpY = x;
    }
    
    public void setFloor(Block b) // Sets the current block player is on
    {
        floor = b;
    }
    
    public void setChangeX(int a)
    {
        changeX = a;
    }
    
    public void setChangeY(int y) // Sets change of y
    {
        changeY = y;
    }
    
    public void setStatus(int a)
    {
        if(status == 1 && a > 1)
            y -= 40;
        status = a;
    }
    
    public void setX(int a) // Sets x coordinate
    {
        x = a;
    }
    
    public void setY(int b)
    {
        y = b;
    }
    
    public void setGone(boolean b) // Sets whether player is visible
    {
        gone = b;
    }
    
    public void setLives(int x)
    {
        lives = x;
    }
    
    public void die()
    {
        status = 0;
        hasDied = true;
        screen.dieMusic();
    }
    
    public void setOnFlag(boolean b) // Sets whether player is on flag
    {
        onFlag = b;
    }
    
    public void upgrade(int x) // Upgrades based on powerup
    {
        if(x == 1) // Mushroom or Flower
        {
            if (status < 3)
            {
                screen.playSFX(3);
                status++;
                x -= 40;
            }
        }
        else if (x == 4) // 1UP
        {
            lives++;
            screen.playSFX(5);
        }
        else // Star
        {
            isInvincible = true;
            fromStar = true;
            screen.dieMusic();
        }
    }
    
    public void downgrade() // Gets smaller or dies
    {
        status -= 1;
        if(status == 1)
            y += 40;
        if(status > 0)
        {
            isInvincible = true;
            screen.playSFX(9);
        }
        else
        {
            die();
        }
    }
    
    public void collectCoin() // When Mario touches a coin
    {
        if(coinsCollected < 99)
        {
            coinsCollected++;
            screen.playSFX(6);
        }
        else
        {
            coinsCollected = 0;
            screen.playSFX(5);
            lives++;
        }
    }
    
    public void act (int[] holebegins, int[] holeends) // Moving
    {
        if(hasDied)
        {
            deadCount++;
            if(deadCount == 900)
            {
                if(lives > 0)
                    screen.reset();
                else
                    screen.gameOver();
            }
        }
        else
        {
            if(isInvincible)
            {
                invincibleCount++;
                if(fromStar)
                {
                    if(invincibleCount == 2050)
                    {
                        isInvincible = false;
                        fromStar = false;
                        screen.dieMusic();
                        invincibleCount = 0;
                    }
                }
                else
                {
                    if(invincibleCount == 300)
                    {
                        isInvincible = false;
                        invincibleCount = 0;
                    }
                }
            }
            
            if((x > 0 || changeX > 0) && (x < 964 || changeX < 0))
            {
                // Moving the player sideways
                x += changeX;
                disTraveled += changeX;
            }
            
            boolean onHole = false; // Checks if player is over a hole
            for(int b = 0; b < holebegins.length; b++)
            {
                if(x >= holebegins[b] && x <= holeends[b]-36)
                {
                    if (status == 1)
                        if(y >= 596) // Small
                        {
                            changeY = 2;
                            onHole = true;
                        }
                        else
                            if(y >= 556) // Big
                            {
                                changeY = 2;
                                onHole = true;
                            }
                }
            }
            
            
            if(status == 1) // Small mario
            {
                if(!onHole) // Checks if not falling down hole
                {
                    if (changeY == 2 &&  y == 596) // Checks if player is on bottom surface
                    {
                        changeY = 0;
                        setGround(596);
                    }
                    if(floor != null) // Checks if there is a floor
                    {
                        if(floor.onTopOf(this) && (changeY == 2 || changeY == 0))
                            changeY = 0;
                        if(!(floor.onTopOf(this)))
                            changeY = 2;
                        
                    }
                }
                else // Falling
                {
                    changeY = 2;
                    if(y == 700)
                    {
                        die();
                    }
                }
            }
            else // Big Mario
            {
                if(!onHole) // Checks if not falling
                {
                    
                    if (changeY == 2 &&  y == 556) // Checks if player is on bottom surface
                    {
                        changeY = 0;
                        setGround(556);
                    }
                    if(floor != null)
                    {
                        if(floor.onTopOf(this) && (changeY == 2 || changeY == 0))
                            changeY = 0;
                        if(!(floor.onTopOf(this)))
                            changeY = 2;
                    }
                }
                else // Falling
                {
                    if(y == 700)
                    {
                        die();
                    }
                }
            }
            
            if(y!=650 || changeY<0) // Moves player into air
                y += changeY;
            
            if (y == (jumpY-250)) // Brings the player downwards to imitate gravity
                changeY = 2;
        }
    }
    
    public void keyPressed(KeyEvent e)  // Response to button pressed
    {
        int c = e.getKeyCode();
        
        if (c == KeyEvent.VK_LEFT) // Moving left
        {
            changeX = -2;
            faceRight = false;
            holdCount++;
        }
        if (c == KeyEvent.VK_RIGHT) // Moving right
        {
            changeX = 2;
            faceRight = true;
            holdCount++;
        }
        
        if (c == KeyEvent.VK_UP)
        {
            if(((status == 1 && y == 596) || (status > 1 && y == 556)) || floor != null) // Makes sure on surface
            {
                jumpY = y;
                changeY = -2;
                if(status == 1)
                    screen.playSFX(1);
                else
                    screen.playSFX(2);
            }
        }
        
        if (c == KeyEvent.VK_DOWN)
            crouch = true;
        else
            crouch = false;
        
        if(c == KeyEvent.VK_SPACE)
            if(status == 3)
            {
                throwFire = true;
                screen.addFireball();
            }
    }
    
    public void keyReleased(KeyEvent e) // Response to button released
    {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT || c == KeyEvent.VK_RIGHT)
        {
            changeX = 0;
            holdCount = 0;
        }
        if (c == KeyEvent.VK_UP)
        {
            changeY = 2;
        }
        if(c == KeyEvent.VK_DOWN)
            crouch = false;
    }
    
}