package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

abstract public class Enemy implements Animatable
{
	// Fields
	
	protected double percentage;
	protected Path   path;
	protected BufferedImage image;
	protected GameState game;
		
	/** Constructor - contract needed.  
     *     (What does the constructor set up?  This contract, provided by the
     *     student, may be short, but it should clearly describe how the
     *     new object is set up (or configured).  Since this is a superclass,
     *     the comment should be fairly generic.
     *     
     * @param pathname  What is this for?
     * @param game      What is this for?
     */ 
	public Enemy (String pathname, GameState game)
	{
		this.percentage = 0;
		this.path = ResourceLoader.getLoader().getPath(pathname);
		this.game = game;
	}
	
	/**
     * Draws the image associated with this enemy, centered on the enemy's
     * location on the path.  (Note:  It is up to the subclass to initialize
     * the image variable.)
     * 
     * @param g a graphics context
     */
	public void draw (Graphics2D g)
	{
        Point c = getLocation();
        
        g.drawImage(image,  c.x-image.getWidth()/2, c.y-image.getHeight()/2, null); 
	}
	
	/**
     * Returns the location of this enemy on the path.  The location is
     * returned in a Point object (x, y coordinates).
     * 
     * Caution:  The returned Point object is not directly used by this
     * enemy.  Changing it will not affect the enemy's location.
     * 
     * @return a Point object that corresponds to the location of this enemy
     */
	public Point getLocation ()
	{
		return path.getPathPosition(percentage);
	}
}
