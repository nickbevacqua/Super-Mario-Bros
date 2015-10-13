// Nick Bevacqua
//package Classes;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.net.*;
import java.io.*;
import javax.sound.sampled.*;

// Panel which will contain the objects of the game
public class MarioCanvas extends JPanel implements ActionListener, KeyListener
{
    private int timeLeft = 400; // Displayed time left
    private int timeCount = 0; // Counter until time to decrease timeLeft
    private int score = 0; // Displayed score
    private int speed = 5; // Speed of timer
    private Timer time = new Timer (speed,this); // Timer that controls movement
    private Mario player = new Mario(this); // Controllable object
    private int blockCount = 1; // Sets image of ? blocks
    private boolean foundFloor = false;
    private int levelX = 0; // Current position of level picture
    private int flagX = 10260;
    private int flagY = 130;
    private boolean levelComplete = false;
    private boolean inPause = false;
    private boolean quitScreen = false;
    private MarioFrame window;
    private boolean gameOver = false;
    private int gameOverTimer = 0;
    private int coinCount = 0;
    private int winCount = 0;
    private int fireNum = 0;
    private int firePresent = 0;
    private Fireball[] fireballs = new Fireball[5];
    private boolean dead = false;
    private int flagDownCount;
    
    // Instance data set in constructor
    
    
    private int[] yChanges;
    private int[] yValues;
    // Coin coordinates
    private int[] coinXs;
    private int[] coinYs;
    // Floating block coordinates
    private int[] blockXs;
    private int[] blockYs;
    // Enemy coordinates
    private int[] goombaXs;
    private int[] goombaYs;
    private Goomba[] goombas;
    private int[] turtleXs;
    private int[] turtleYs;
    private Turtle[] turtles;
    private String[] turtleTypes;
    private int[] blockContents; // Content for block
    private int[] blockContentsOut; // Content for powerup
    private int[] blockContentsVisibility; // Visibility of powerup
    private boolean[] powerupUsed; // Booleans determining if powerups are used
    private int[] holeStarts; // X coordinates of holes
    private int[] holeEnds; // X coordinates of hole ends
    private String levelNum; // Level number for display
    private int lev;
    
    
    // Music
    
    private File musicFile;
    private URI musicURI;
    private URL musicURL;
    private Clip musicClip;
    private AudioInputStream musicInput;
    
    private File soundFile;
    private URI soundURI;
    private URL soundURL;
    private Clip soundClip;
    private AudioInputStream soundInput;
    
    // Images for levels
    private Image flag;
    private Image level1;
    private Image level2;
    private Image level3;
    private Image level4;
    private Image level;
    
    // Coin image
    private Image c1;
    
    public MarioCanvas (int x, MarioFrame frame, int sc, int sts, int lives) // Starts the timer and listeners
    {
        flag = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Level" + File.separator + "flag.png").getImage();
        level1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Level" + File.separator + "level11.gif").getImage();
        level2 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Level" + File.separator + "level12.gif").getImage();
        level3 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Level" + File.separator + "level13.gif").getImage();
        level4 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Level" + File.separator + "level14.gif").getImage();
        c1 = new ImageIcon(".." + File.separator + "Pics" + File.separator + "Coin" + File.separator + "coin1.png").getImage();
        
        playMusic(x, false, false);
        this.setLevel(x);
        time.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        window = frame;
        score = sc;
        player.setStatus(sts);
        player.setLives(lives);
    }
    
    public void playMusic(int level, boolean hurry, boolean dead) // Plays music based on situation
    {
        try
        {
            if(dead)
                musicFile = new File(".." + File.separator + "Music" + File.separator + "dead.wav");
            else if (player.getX() ==  300)
                musicFile = new File(".." + File.separator + "Music" + File.separator + "complete.wav");
            else if (player.getInvincible() == true)
                musicFile = new File(".." + File.separator + "Music" + File.separator + "invincible.wav");
            else if (level == 1 || level == 3)
            {
                if(hurry)
                    musicFile = new File(".." + File.separator + "Music" + File.separator + "level11hurry.wav");
                else
                    musicFile = new File(".." + File.separator + "Music" + File.separator + "level11.wav");
            }
            else if (level == 2)
            {
                if(hurry)
                    musicFile = new File(".." + File.separator + "Music" + File.separator + "level12hurry.wav");
                else
                    musicFile = new File(".." + File.separator + "Music" + File.separator + "level12.wav");
            }
            else
            {
                if(hurry)
                    musicFile = new File(".." + File.separator + "Music" + File.separator + "level14hurry.wav");
                else
                    musicFile = new File(".." + File.separator + "Music" + File.separator + "level14.wav");
            }
            
            musicURI = musicFile.toURI();
            musicURL = musicURI.toURL();
            musicClip = AudioSystem.getClip();
            musicInput = AudioSystem.getAudioInputStream(musicURL);
            
            musicClip.open(musicInput);
            musicClip.loop(0);
        }
        catch (Exception e)
        {
            System.out.print("No Music File Found");
        }
    }
    
    public void dieMusic() // Music played with mario dies
    {
        musicClip.stop();
        playMusic(1, false, (player.getStatus() == 0));
    }
    
    public void playSFX(int x) // Plays sound effect based on situation
    {
        /*
        1 = smalljump  DONE
        2 = bigjump DONE
        3 = powerup DONE
        4 = fireball DONE
        5 = 1up DONE
        6 = coin DONE
        7 = blocksmash DONE
        8 = stomp DONE
        9 = pipe DONE
        10 = flagpole
        11 = bowserfire
        12 = bowserfall
        13 = pause DONE
        14 = appear DONE
        15 = kick
        16 = gameover DONE
        17 = ending
        */
        
        try
        {
            if(x == 1)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "smalljump.wav");
            else if (x == 2)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "bigjump.wav");
            else if (x == 3)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "powerup.wav");
            else if (x == 4)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "fireball.wav");
            else if (x == 5)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "1up.wav");
            else if (x == 6)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "coin.wav");
            else if (x == 7)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "blocksmash.wav");
            else if (x == 8)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "stomp.wav");
            else if (x == 9)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "pipe.wav");
            else if (x == 10)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "flagpole.wav");
            else if (x == 11)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "bowserfire.wav");
            else if (x == 12)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "bowserfall.wav");
            else if (x == 13)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "pause.wav");
            else if (x == 14)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "appear.wav");
            else if (x == 15)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "kick.wav");
            else if (x == 16)
                soundFile = new File(".." + File.separator + "sound" + File.separator + "gameover.wav");
            else
                soundFile = new File(".." + File.separator + "sound" + File.separator + "ending.wav");
            
            
            soundURI = soundFile.toURI();
            soundURL = soundURI.toURL();
            soundClip = AudioSystem.getClip();
            soundInput = AudioSystem.getAudioInputStream(soundURL);
            
            soundClip.open(soundInput);
            soundClip.loop(0);
        }
        catch (Exception e)
        {
            System.out.print("No SFX File Found");
        }
    }
    
    public void setLevel(int x) // Sets up screen based on which level is going on
    {
        lev = x;
        if(x == 1)
        {
            yChanges = new int[]{1428, 1552, 1948, 2072, 2362, 2488, 2930, 3062, 3599, 3664, 4474, 4596,
                6934, 6988, 7034, 7084, 7134, 7246, 7334, 7384, 7434, 7484, 7662, 7712, 7762, 7812, 7912,
                8024, 8112, 8162, 8212, 8262, 8442, 8578, 9282, 9408, 9428, 9478, 9528, 9578, 9628, 9578,
                9628, 9880, 10264};
            yValues = new int[]{490, 596, 440, 596, 388, 596, 388, 596, 800, 596, 800, 596, 540, 484, 428, 372, 596, 372,
                428, 484, 540, 596, 540, 484, 428, 372, 800, 372, 428, 484, 540, 596, 490, 596, 490, 540, 484, 428, 372,
                316, 260, 204, 148, 596, 540};
            yChanges = new int[]{};
            yValues =  new int[]{};
            coinXs = new int[]{400};
            coinYs = new int[]{420};
            blockXs = new int[]{850 ,1050,1100,1150, 1150, 1200, 1250, 3350, 4000, 4050,
                4100, 4150, 4200, 4250, 4300, 4350, 4400, 4450, 4500, 4750,
                4800, 4850, 4900,  4900, 5200, 5250, 5500, 5650, 5650, 5800,
                6150, 6250, 6300, 6350,  6650, 6700, 6700, 6750, 6750, 6800,
                8750, 8800, 8850, 8900};
            blockYs = new int[] {440,440, 440, 230, 440, 440, 440, 384, 440, 440,
                440, 230, 230, 230, 230, 230, 230, 230, 230, 230,
                230, 230, 230, 440, 440, 440, 440, 440, 230, 440,
                440, 230, 230, 230, 230, 230, 440, 440, 230, 230,
                440, 440, 440, 440};
            // 0 = nothing, 1 = mushroom/flower, 2 = star, 3 = coin, 4 = 1up
            blockContents = new int[] {3,0,1,3,0,3,0,4,0,1,
                /* Block */       	0,0,0,0,0,0,0,0,0,0,
                0,0,3,3,0,2,3,3,1,3,
                0,0,0,0,0,3,3,3,3,0,
                0,0,3,0};
            blockContentsOut = new int[] {3,0,1,3,0,3,0,4,0,1,
                /* Block */       	0,0,0,0,0,0,0,0,0,0,
                0,0,3,3,0,2,3,3,1,3,
                0,0,0,0,0,3,3,3,3,0,
                0,0,3,0};
            blockContentsVisibility = new int[] {0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0};
            powerupUsed = new boolean[]{false,false,false,false, false,false,false,false,false, false,
                false,false,false,false, false,false,false,false,false, false,
                false,false,false,false, false, false,false,false,false, false,
                false,false,false,false, false,false,false,false,false, false,
                false,false,false,false};
            goombaXs = new int[]{1074, 2100, 2734, 2816, 4150, 4240, 4936, 5016, 6420, 6492,
                6596, 6668, 9008, 9086};
            goombaYs = new int[]{596, 596, 596, 596, 596, 596, 596, 596, 596, 596,
                596, 596, 596, 596};
            goombas = new Goomba[goombaXs.length];
            for(int a = 0; a < goombaXs.length; a++)
            {
                Goomba e = new Goomba(goombaXs[a], goombaYs[a]);
                goombas[a] = e;
            }
            turtleXs = new int[] {5496};
            turtleYs = new int[] {566};
            turtleTypes = new String[]{"green"};
            turtles = new Turtle[turtleXs.length];
            for(int a = 0; a < turtleXs.length; a++)
            {
                Turtle k = new Turtle(turtleXs[a], turtleYs[a], turtleTypes[a]);
                turtles[a] = k;
            }
            level = level1;
            levelNum = "1-1";
        }
        else if (x == 2)
        {
            coinXs = new int[]{};
            coinYs = new int[]{};
            blockXs = new int[]{ 700, 750, 800, 850, 900, 1800, 2400, 2400, 2400, 2450,
                2500, 2500, 2500, 2550, 2600, 2650, 2650, 2650, 2700, 2750,
                2750, 2750};
            blockYs = new int[] {440,440, 440, 230, 440, 440, 440, 384, 440, 440,
                440, 230, 230, 230, 230, 230, 230, 230, 230, 230,
                230, 230};
            // 0 = nothing, 1 = mushroom/flower, 2 = star, 3 = coin, 4 = 1up
            blockContents = new int[] {1,3,0,3,0,4,3,1,3,0,
                /* Block */		 0,0,0,0,0,0,0,0,0,0,
                1,3,3,};
            blockContentsOut = new int[] {1,3,0,3,0,4,3,1,3,0,
                /* Powerup */          0,0,0,0,0,0,0,0,0,0,
                1,3,3};
            blockContentsVisibility = new int[] {0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0};
            powerupUsed = new boolean[]{false,false,false,false, false,false,false,false,false, false,
                false,false,false,false, false,false,false,false,false, false,
                false,false,false};
            goombaXs = new int[]{500};
            goombaYs = new int[]{596};
            goombas = new Goomba[goombaXs.length];
            for(int a = 0; a < goombaXs.length; a++)
            {
                Goomba e = new Goomba(goombaXs[a], goombaYs[a]);
                goombas[a] = e;
            }
            turtleXs = new int[] {900};//{5650};
            turtleYs = new int[] {596};
            turtleTypes = new String[]{"green"};
            turtles = new Turtle[turtleXs.length];
            for(int a = 0; a < turtleXs.length; a++)
            {
                Turtle k = new Turtle(turtleXs[a], turtleYs[a], turtleTypes[a]);
                turtles[a] = k;
            }
            holeStarts = new int[]{3742, 4164, 8088};
            holeEnds = new int []{3842, 4758, 8188};
            level = level2;
            levelNum = "1-2";
        }
        else
        {
            coinXs = new int[]{400};
            coinYs = new int[]{420};
            blockXs = new int[]{850 ,1050,1100,1150, 1150, 1200, 1250, 3350, 4000, 4050,
                4100, 4150, 4200, 4250, 4300, 4350, 4400, 4450, 4500, 4750,
                4800, 4850, 4900,  4900, 5200, 5250, 5500, 5650, 5650, 5800,
                6150, 6250, 6300, 6350,  6650, 6700, 6700, 6750, 6750, 6800,
                8750, 8800, 8850, 8900};
            blockYs = new int[] {440,440, 440, 230, 440, 440, 440, 384, 440, 440,
                440, 230, 230, 230, 230, 230, 230, 230, 230, 230,
                230, 230, 230, 440, 440, 440, 440, 440, 230, 440,
                440, 230, 230, 230, 230, 230, 440, 440, 230, 230,
                440, 440, 440, 440};
            // 0 = nothing, 1 = mushroom/flower, 2 = star, 3 = coin, 4 = 1up
            blockContents = new int[] {1,3,0,3,0,4,3,1,3,0,
                /* Block */		     		0,0,0,0,0,0,0,0,0,0,
                1,3,3,0,2,3,3,1,3,3,
                0,0,0,0,0,3,3,3,3,0,
                0,0,3,0};
            blockContentsOut = new int[] {1,3,0,3,0,4,3,1,3,0,
                /* Powerup */			     0,0,0,0,0,0,0,0,0,0,
                1,3,3,0,2,3,3,1,3,3,
                0,0,0,0,0,3,3,3,3,0,
                0,0,3,0};
            blockContentsVisibility = new int[] {0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0,0,0,0,0,0,0,
                0,0,0,0};
            powerupUsed = new boolean[]{false,false,false,false, false,false,false,false,false, false,
                false,false,false,false, false,false,false,false,false, false,
                false,false,false,false, false, false,false,false,false, false,
                false,false,false,false, false,false,false,false,false, false,
                false,false,false,false};
            goombaXs = new int[]{500};
            goombaYs = new int[]{596};
            goombas = new Goomba[goombaXs.length];
            for(int a = 0; a < goombaXs.length; a++)
            {
                Goomba e = new Goomba(goombaXs[a], goombaYs[a]);
                goombas[a] = e;
            }
            turtleXs = new int[] {900};//{5650};
            turtleYs = new int[] {596};
            turtleTypes = new String[]{"green"};
            turtles = new Turtle[turtleXs.length];
            for(int a = 0; a < turtleXs.length; a++)
            {
                Turtle k = new Turtle(turtleXs[a], turtleYs[a], turtleTypes[a]);
                turtles[a] = k;
            }
            holeStarts = new int[]{3742, 4164, 8088};
            holeEnds = new int []{3842, 4758, 8188};
            level = level2;
            levelNum = ("1-2");
        }
    }
    
    public void paintComponent (Graphics g) // Fills in objects
    {
        super.paintComponent(g);
        g.drawImage(level, levelX, 0, 11000, 720, null);
        g.drawImage(flag, flagX, flagY, 70, 70, null);
        setUpLevel(g);
        player.paintComponent(g);
        g.setColor(new Color(255,255,255));
        Font top = new Font("Monospaced", Font.PLAIN, 20);
        g.setFont(top);
        // Displays score
        g.drawString("MARIO", 75, 25);
        g.drawString("" + score, 75, 45);
        // Displays coins collected
        g.drawImage(c1, 185, 8, 10, 20, null);
        g.drawString(" * " + player.getCoins(), 200, 25);
        // Displays level
        g.drawString("WORLD", 700, 25);
        g.drawString("" + levelNum, 700, 45);
        // Displays time left
        g.drawString("TIME", 900, 25);
        g.drawString( "" + timeLeft, 900, 45);
        
        if(gameOver)
        {
            g.setColor(Color.BLACK);
            g.fillRect(300, 300, 400, 140);
            Font bold = new Font("Monospaced", Font.PLAIN, 40);
            g.setFont(bold);
            g.setColor(Color.RED);
            g.drawString("GAME OVER", 390, 350);
            g.setFont(top);
            g.drawString("Your Score:  " + score, 400, 400);
        }
        
        if(inPause) // Paused
        {
            if(quitScreen)
            {
                g.setColor(Color.BLACK);
                g.fillRect(200, 300, 600, 160);
                g.setColor(Color.WHITE);
                g.setFont(top);
                g.drawString("Are you sure you want to quit?", 220, 380);
                g.drawString("Press Y for yes and N for no.", 220, 420);
            }
            else
            {
                g.setColor(Color.BLACK);
                g.fillRect(200, 300, 600, 160);
                Font bold = new Font("Monospaced", Font.PLAIN, 40);
                g.setFont(bold);
                g.setColor(Color.RED);
                g.drawString("PAUSED", 220, 350);
                g.setFont(top);
                g.drawString("Press P to continue." /*+ player.getDistance() + " " + player.getY()*/, 220, 400);
                g.drawString("Press Q to return to main menu.", 220, 440);
            }
        }
        if(blockCount < 60)
            blockCount++;
        else
            blockCount = 1;
        
    }
    
    public int getBlockCount()
    {
        return blockCount;
    }
    
    public int getTimeLeft()
    {
        return timeLeft;
    }
    
    public Mario getMario()
    {
        return player;
    }
    
    public int getLevelNum()
    {
        return lev;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public void setUpLevel(Graphics g) // Sets up level
    {
        placeCoins(g);
        placeBlocks(g);
        placePowerUps(g);
        placeEnemies(g);
        placeFireballs(g);
    }
    
    public void placeCoins(Graphics g) // Puts coins on the level
    {
        for(int x = 0; x < coinXs.length; x++)
        {
            Coin c = new Coin(coinXs[x], coinYs[x]);
            if(coinXs[x] > -50 && coinXs[x] < 1000)
                c.paintComponent(g, this);
        }
    }
    
    public void placeBlocks(Graphics g) // Places floating blocks on level
    {
        for(int x = 0; x < blockXs.length; x++)
        {
            Block b = new Block(blockXs[x], blockYs[x], blockContents[x], lev);
            if(blockXs[x] > -50 && blockXs[x] < 1000)
                b.paintComponent(g, this);
        }
    }
    
    public void placePowerUps(Graphics g)
    {
        for(int x = 0; x < blockContentsVisibility.length; x++)
        {
            if(blockContentsVisibility[x] == 1 && powerupUsed[x] == false)
            {
                Powerup p = new Powerup (new Block(blockXs[x], blockYs[x], blockContentsOut[x], lev));
                if(blockXs[x] > -50 && blockXs[x] < 1000)
                    p.paintComponent(g, this);
            }
        }
    }
    
    public void placeEnemies(Graphics g)
    {
        for(int x = 0; x < goombas.length; x++)
        {
            if(goombas[x].getX() > -50 && goombas[x].getX() < 1000)
                goombas[x].paintComponent(g, this);
        }
        for(int x = 0; x < turtles.length; x++)
        {
            if(turtles[x].getX() > -50 && turtles[x].getX() < 1000)
                turtles[x].paintComponent(g, this);
        }
    }
    
    public void addFireball()
    {
        if(firePresent < 5)
        {
            fireballs[fireNum] = new Fireball (player, fireNum);
            playSFX(4);
            firePresent ++;
            fireNum++;
            if(fireNum == 5)
            {
                fireNum = 0;
            }
        }
    }
    
    public void removeFireball(int num)
    {
        firePresent--;
        fireballs[num] = null;
    }
    
    public void placeFireballs(Graphics g)
    {
        if(player.getStatus() == 3)
        {
            for(int x = 0; x < fireballs.length; x++)
            {
                if(fireballs[x] != null)
                {
                    fireballs[x].paintComponent(g, this);
                }
            }
        }
    }
    
    public void checkSurface () // Checks if player is on top of floating block
    {
        foundFloor = false; // Boolean to find block below player
        Block b;
        int k = blockXs.length-1;
        for(int x = 0; x <= k; x++) // Each block
        {
            if (player.getChangeX() >= 0)
                b = new Block(blockXs[x], blockYs[x], blockContents[x], lev);
            else
                b = new Block(blockXs[k-x], blockYs[k-x], blockContents[k-x], lev);
            if(b.onTopOf(player) && foundFloor == false) // Checks if player is on block at moment
            {
                player.setGround(b.getY()); // Sets y-coordinate of ground
                player.setFloor(b); // Sets block below
                foundFloor = true; // Doesnt check any more
            }
            if(b.beneath(player)) // Checks if player is right below block
            {
                if(b.getContent() == 0 && player.getStatus() > 1) // If nothing is present in block
                {
                    blockYs[x] = 1000;
                    player.setChangeY(2);
                    playSFX(7);
                }
                else if (b.getContent() == 0 && player.getStatus() == 1)
                    player.setChangeY(2);
                else // If the block has content
                {
                    blockContentsVisibility[x] = 1; // Content set visible
                    if(blockContents[x] == 3)
                        playSFX(6);
                    else
                        if(blockContents[x] != -1)
                            playSFX(14);
                    blockContents[x] = -1; // Removes content from block
                    player.setChangeY(2);
                }
            }
        }
        if (foundFloor == false)
            player.setFloor(null);
    }
    
    public void checkCoins () // Checks if player is touching a coin
    {
        for(int x = 0; x < coinXs.length; x++)
        {
            Coin c = new Coin(coinXs[x], coinYs[x]);
            if(c.touched(player))
            {
                c.hit(player);
                changeScore(100);
                coinXs[x] = 10900;
            }
        }
    }
    
    public void checkPowerups () // Checks if player is touching a powerup or if coin comes out
    {
        for(int x = 0; x < blockContentsVisibility.length; x++)
        {
            if(blockContentsVisibility[x] == 1 && powerupUsed[x] == false)
            {
                Powerup p = new Powerup (new Block(blockXs[x], blockYs[x], blockContentsOut[x], lev));
                if(blockContentsOut[x] == 3)
                    coinCount++;
                if(coinCount == 10)
                {
                    player.collectCoin();
                    changeScore(100);
                    coinCount = 0;
                    blockContentsVisibility[x] = 0;
                    blockContentsOut[x] = -1;
                    powerupUsed[x] = true;
                }
                if(p.touched(player))
                {
                    p.hit(player);
                    changeScore(500);
                    blockContentsVisibility[x] = 0;
                    blockContentsOut[x] = -1;
                    powerupUsed[x] = true;
                }
            }
        }
    }
    
    public void checkEnemies()
    {
        for(int x = 0; x < goombas.length; x++) // Each enemy
        {
            if(goombas[x].onTopOf(player)) // Checks if player is on block at moment
            {
                goombas[x].setDefeat(false);
                player.setChangeY(-2);
                changeScore(200);
                player.setGround(player.getY() + 200);
                playSFX(8);
            }
            else
            {
                if(goombas[x].touched(player)) // Checks if player is right below block
                {
                    if(player.getStar() == true)
                    {
                        goombas[x].setDefeat(false);
                        changeScore(200);
                        playSFX(8);
                    }
                    else
                    {
                        if(player.getInvincible() == false && player.getStatus() > 0)
                            player.downgrade();
                    }
                }
            }
        }
        for(int x = 0; x < turtles.length; x++) // Each enemy
        {
            if(turtles[x].onTopOf(player)) // Checks if player is on block at moment
            {
                turtles[x].setDefeat(false);
                player.setChangeY(-2);
                changeScore(200);
                player.setGround(player.getY() + 200);
                playSFX(8);
            }
            else
            {
                if(turtles[x].touched(player)) // Checks if player is right below block
                {
                    player.downgrade();
                }
            }
        }
    }
    
    public void checkFireballs()
    {
        for(int x = 0; x < goombas.length; x++) // Each enemy
            for(int y = 0; y < fireballs.length; y++)
                if(fireballs[y] != null)
                    if(goombas[x].touched(fireballs[y])) // Checks if player is on block at moment
                    {
                        goombas[x].setDefeat(false);
                        removeFireball(y);
                        changeScore(200);
                        playSFX(8);
                    }
        
        for(int x = 0; x < turtles.length; x++) // Each enemy
            for(int y = 0; y < fireballs.length; y++)
                if(fireballs[y] != null)
                    if(turtles[x].touched(fireballs[y])) // Checks if player is on block at moment
                    {
                        turtles[x].setDefeat(false);
                        removeFireball(y);
                        changeScore(200);
                        playSFX(8);
                    }
    }
    
    public void changeScore (int x) // Adjusts the current score
    {
        score += x;
        if (score < 0)
            score = 0;
    }
    
    public void shiftBoard()
    {
        levelX -= 2;
        player.setX(player.getX()-2);
        flagX -= 2;
        for (int a = 0; a < blockXs.length; a++)
        {
            blockXs[a] -= 2;
        }
        for (int a = 0; a < coinXs.length; a++)
        {
            coinXs[a] -= 2;
        }
        for (int a = 0; a < goombas.length; a++)
        {
            goombas[a].setX(goombas[a].getX()-2);
        }
        for (int a = 0; a < turtles.length; a++)
        {
            turtles[a].setX(turtles[a].getX()-2);
        }
        for (int a = 0; a < fireballs.length; a++)
        {
            if(fireballs[a] != null)
                fireballs[a].setX(fireballs[a].getX()-2);
        }
    }
    
    public void reset()
    {
        window.toLevelScreen(player.getLives()-1, player.getCoins(), score, levelNum, 1);
    }
    
    public void gameOver()
    {
        gameOver = true;
        playSFX(16);
    }
    
    public void actionPerformed (ActionEvent e) // Takes one step by calling all methods
    {
        if(gameOver) // Game over
        {
            gameOverTimer++;
            if(gameOverTimer == 900)
                window.toTitle();
        }
        
        else if(dead) // Player dead
        {
            player.act(holeStarts, holeEnds);
        }
        
        else if(!levelComplete) // Level incomplete
        {
            player.act(yChanges, yValues); // Player
            for (int a = 0; a < goombas.length; a++)
                if(goombas[a].getX() <= 1000)
                    goombas[a].act(yChanges, yValues);
            for (int a = 0; a < turtles.length; a++)
                if(turtles[a].getX() <= 1000)
                    turtles[a].act(yChanges, yValues);
            for(int a = 0; a < fireballs.length; a++)
                if(fireballs[a] != null)
                    fireballs[a].act();
            checkPowerups();
            checkSurface();
            checkCoins();
            checkEnemies();
            checkFireballs();
            
            if((player.getX() == 152) && (player.getDistance() < 10150))
            {
                shiftBoard();
            }
            
            timeCount++; // Adjusts time remaining
            
            if(timeCount == 50) // Lowers time left
            {
                if(timeLeft > 0)
                    timeLeft--;
                timeCount = 0;
            }
            
            if(timeLeft == 100 && timeCount == 0)
            {
                musicClip.stop();
                playMusic(lev, true, false);
            }
            
            if(timeLeft == 0 && dead == false)
            {
                player.setStatus(1);
                player.downgrade();
                player.die();
                dead = true;
            }
            
            if(player.getX() == 300)
            {
                musicClip.stop();
                levelComplete = true;
                playMusic(lev, true, false);
            }
        }
        else // Level complete
        {
            if(player.getY() > 100 && player.getY() < 550)
            {
                player.setChangeY(2);
                player.setOnFlag(true);
            }
            else if (player.getY() < 100)
            {
                player.setChangeY(2);
            }
            else
            {
                if(player.getStatus() == 1 && player.getY() == 550)
                    player.setY(596);
                if(player.getStatus() > 1 && player.getY() == 550)
                    player.setY(556);
                player.setChangeX(1);
                player.setOnFlag(false);
                
                if(player.getX() == 800)
                    player.setGone(true);
            }
            
            timeCount++;
            
            
            if(timeCount>4 && timeLeft > 0)
            {
                timeLeft--;
                changeScore(100);
                timeCount = 0;
            }
            
            flagDownCount++;
            if(flagDownCount == 2)
            {
                if(flagY < 520)
                    flagY++;
                flagDownCount = 0;
                repaint();
            }
            
            winCount++;
            
            if(winCount == 2000)
                window.toLevelScreen(player.getLives(), player.getCoins(), score , "1-" + (lev+1), player.getStatus());
        }
        
        repaint(); // Calls the paintComponent to repaint
    }
    
    public void keyPressed(KeyEvent e) // Detects buttons pressed
    {
        int c = e.getKeyCode();
        if(c == KeyEvent.VK_P && !gameOver)
        {
            if(inPause)
            {
                inPause = false;
                time.start();
                musicClip.loop(1);
            }
            else
            {
                inPause = true;
                time.stop();
                repaint();
                musicClip.stop();
                playSFX(13);
            }
        }
        else if(c == KeyEvent.VK_Q)
        {
            if(inPause)
                if(!quitScreen)
                {
                    quitScreen = true;
                    repaint();
                }
        }
        else if (c == KeyEvent.VK_N)
        {
            if(inPause)
                if(quitScreen)
                {
                    quitScreen = false;
                    repaint();
                }
        }
        else if (c == KeyEvent.VK_Y)
        {
            if(inPause)
                if(quitScreen)
                    window.toTitle();
        }
        else
            if (!levelComplete)
                player.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e) // Detects buttons released
    {
        if(!levelComplete)
            player.keyReleased(e);
    }
    
    public void keyTyped(KeyEvent e) // Not used in program
    {
    }
}