// Nick Bevacqua
//package Classes;

import java.io.*;
import java.awt.*;
import javax.swing.*;

public class MarioFrame extends JFrame // Window which displays game
{
    private static final int CANVAS_WIDTH = 1000;
    private static final int CANVAS_HEIGHT = 720;
    private int currentLevel = 1;
    private MarioCanvas canvas;
    private MarioTitle title;
    private MarioLevelScreen intro;
    private boolean fromMain = true;
    private int highscore = 0;
    private int currentStatus = 1;
    private int currentLives = 3;
    
    public MarioFrame() // Constructor which adds menu and canvas
    {
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        title = new MarioTitle(this);
        title.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        getContentPane().add(title);
        //canvas = new MarioCanvas(2, this, title, 0, 1);
        //canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        //getContentPane().add(canvas);
        setTitle("Super Mario Bros.");
        setIconImage(new ImageIcon(".." + File.separator + "Pics" + File.separator + "marioicon.png").getImage());
    }
    
    public void toTitle() // Returns the game to the menu after winning, losing or quitting
    {
        highscore = canvas.getScore();
        getContentPane().remove(canvas);
        title = new MarioTitle(this);
        title.setHighScore(highscore);
        title.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        getContentPane().add(title);
        title.requestFocusInWindow();
        fromMain = true;
        display();
    }
    
    public void toLevelScreen(int lifes, int c, int sc, String lev, int sts) // Enters screen revealing lives and level
    {
        if(fromMain) // Just starting the game
        {
            getContentPane().remove(title);
            fromMain = false;
        }
        else // Leveled up or died
        {
            getContentPane().remove(canvas);
        }
        currentLives = lifes;
        currentStatus = sts;
        intro = new MarioLevelScreen(lifes, c, sc, lev, sts, this);
        intro.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        getContentPane().add(intro);
        display();
    }
    
    public void toGame(int score) // Begins game following level screen
    {
        getContentPane().remove(intro);
        canvas = new MarioCanvas(currentLevel, this, score, currentStatus, currentLives);
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        getContentPane().add(canvas);
        canvas.requestFocusInWindow();
        display();
    }
    
    public void upLevel()
    {
        currentLevel++;
    }
    
    public void setStatus(int s)
    {
        currentStatus = s;
    }
    
    public void display() // Displays the window on screen
    {
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) // Runner
    {
        MarioFrame frame = new MarioFrame();
        frame.display();
    }
}