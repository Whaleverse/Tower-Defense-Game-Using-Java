/**
 * the overall view of the game
 * 
 * @author Yuntong Lu(u1060544)
 * @version April 22, 2017
 */
package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


import game.Animatable;
import game.Enemy;

/** 
 * This class is missing lots of contracts.  A wise student
 * will add them, as it will remind you about what each
 * method is for, and what it does.
 * 
 * @author Peter Jensen and yuntong lu (u1060544)
 * @version April 22, 2017
 */
public class GameState
{
	private ResourceLoader loader;
	
	// select tower
	private boolean isTowerChoosed;
	
	// Current mode (game over, playing, etc.)
	
	protected boolean isGameOver, isPlaying;
	
    // Score, money, etc.
	
	protected int credits, lives, score;
	
	
	// List of enemies, towers, etc.
	    
    private java.util.List<Animatable> active;
    private java.util.List<Animatable> expired;
    private java.util.List<Animatable> pending;
    
	// Mouse location / status

    private Point mouseLoc; 
    private boolean isButtonPressed;  // I renamed this variable in class to 'buttonActionPending'
                                      // For checkpoint 4, I also make sure to clear it at the end of the
                                      // game state update method.
    
    // Constructor
    
	public GameState ()
	{
        // Load resources
        
        loader = ResourceLoader.getLoader();
        
        // Make lists
        
        active  = new ArrayList<Animatable>();
        expired = new ArrayList<Animatable>();
        pending = new ArrayList<Animatable>();
        
        // Initialize the game.
        
        lives = 10;
        credits = 1500;
        
        mouseLoc = new Point(0,0);
        
        // Initialize menu.
        
        active.add(new Backdrop());
        active.add(new SaltTowerMenuItem(this, new Point(700, 250)));
        active.add(new BeerTowerMenuItem(this, new Point(700, 350)));
	}
	
	
	
	public void update ()
	{
		// works from professor 
		if (Math.random() < 0.05)
		{
			if (Math.random() < 0.1)
			   active.add(new EnemySCargo("path_2.txt", this));
			else
			   active.add(new EnemySnail("path_2.txt", this));
		}
		
		for (Animatable a : active)
			a.update();
		
		active.addAll(pending);
		pending.clear();
		
		active.removeAll(expired);
		expired.clear();
		
		
		// my work
		// reset game if the game is over
		if (isGameOver)
		{
			restart();
		}
		

	}
	
	
	public void draw (Graphics2D g)
	{
		// works from professor
		// Draw the menu / score / etc.
		
		g.setColor(Color.WHITE);
		g.fillRect(600,  0,  200,  600);
		
		g.setColor(Color.BLACK);
		g.drawString("Lives = " + lives, 650, 200);
		g.drawString("Credits = " + credits, 650, 150);
		g.drawString("Score = " + score, 650, 100);
		g.drawString("$500", 750, 250);
		g.drawString("$2000", 750, 350);
		
        // Draw the Animatables
        
		for (Animatable a : active)
			a.draw(g);
		
		
		
		// my work
		// if the game over, show the picture 
		if (isGameOver)
		{
			BufferedImage gameOver = loader.getImage("game_over.png");
			g.drawImage(gameOver, 0, 0, null);
		}
        
	}
	
	// Helper methods for the other classes.
	
	public void adjustCredits (int amount)
	{
		credits += amount;
		
		if (credits < 0)
			credits = 0;
	}
	
	
	/**
	 * 
	 * @param amount
	 */
	public void adjustLives (int amount)
	{
		lives += amount;
		
		if (lives <= 0)
		{
			lives = 0;
			
			// you lose (my work)
			isGameOver = true;
			isPlaying = false;
		}
	}
	
	
	/**
	 * my work
	 * 
	 * @param amount
	 */
	public void adjustScore (int amount)
	{
		score += amount;
		
	}
	
	
	public void removeAnimatable (Animatable expired)
	{
		this.expired.add(expired);
    }
	
	public void addAnimatable (Animatable a)
	{
		this.pending.add(a);  // Buggy
    }
	
	
	/**
	 * my work
	 * 
	 * Finds the enemy on the path that is closest to
	 * the point p.  If no enemies exist, null is returned.
	 * 
	 * @param p
	 * @return the nearest enemy, or null
	 */
	public Enemy findNearestEnemy(Point p)
	{
		Enemy closest = null;
		
		// check every enemies
		for(Animatable a : active) {
			if (a instanceof Enemy) {
				
				// initial the closest enemy
				// and find the closest one
				Enemy e = (Enemy) a;
				if(closest == null){
					closest = e;
				} else if (e.getLocation().distance(p) < closest.getLocation().distance(p)) {
					closest = e;
				}
				
			} // end if (instanceof)
		} // end for loop
		
		return closest;
	}
	
	public void  setMousePos (Point p)
	{
		mouseLoc = p;
	}
	
	public Point getMousePos()
    {
	    return mouseLoc;	
	}
	
	public void  setMousePressed  ()
    {
		isButtonPressed = true;
	}
	
	/**
	 *  my work
	 */
	public void setChooseTower ()
	{
		isTowerChoosed = true;
	}
	
	
	/**
	 *  my work
	 */
	public boolean getChooseTower ()
	{
		return isTowerChoosed;
	}
	
	
	/**
	 *  my work
	 */
	public void clearChooseTower ()
	{
		isTowerChoosed = false;
	}
	
	public void  clearMousePressed ()
    {
		isButtonPressed = false;	
	}
	
	public boolean getMousePressed ()
    {
		return isButtonPressed;
	}	
	
	/**
	 *  my work
	 *  
	 *  killing the enemy
	 */
	public void killingEnemy (Enemy c)
	{
		// kill it
    	this.removeAnimatable(c);
    	
    	// different dying methods
    	
    	// snail will die with spalt, get score and credits 
    	if (c instanceof EnemySnail )
    	{
        	this.addAnimatable(new DiedEnemySnail (this, new Point(c.getLocation())));
    		this.adjustCredits(10);
    		this.adjustScore(10);
    	}
    	// cargo will die with crash, get score and credits
    	if (c instanceof EnemySCargo )
    	{
    		this.addAnimatable(new DiedEnemySCargo (this, new Point(c.getLocation())));
    		this.adjustCredits(50);
    		this.adjustScore(20);
    	}
	}
	
	
	
	// two conditions: playing the game and game is over
	// those methods help to set and find the conditions
	
	/**
	 *  my work
	 */
	public void startgame ()
	{
		isPlaying = true;
	}
	
	/**
	 *  my work
	 */
	public boolean getPlaying ()
	{
		return isPlaying;
	}
	
	/**
	 *  my work
	 */
	public boolean getGameOver ()
	{
		return isGameOver;
	}
	
	/**
	 *  my work
	 */
	public void endGameOver ()
	{
		isGameOver = false;
	}
	
	
	/**
	 *  my work
	 *  
	 *  reset all the things to restart the game
	 */
	public void restart ()
	{
   // Load resources
        
        loader = ResourceLoader.getLoader();
        
        // Make lists
        
        active  = new ArrayList<Animatable>();
        expired = new ArrayList<Animatable>();
        pending = new ArrayList<Animatable>();
        
        // Initialize the game.
        
        lives = 10;
        credits = 1500;
        
        mouseLoc = new Point(0,0);
        
        // Initialize menu.
        
        active.add(new Backdrop());
        active.add(new SaltTowerMenuItem(this, new Point(700, 250)));
        active.add(new BeerTowerMenuItem(this, new Point(700, 350)));
	}
	
}
