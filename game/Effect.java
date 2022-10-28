package game;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

abstract public class Effect implements Animatable
{
	// Fields
	
    protected Point position;
	protected BufferedImage image;
	protected GameState game;
	
	/** Constructor - contract needed.  
     *     (What does the constructor set up?  This contract, provided by the
     *     student, may be short, but it should clearly describe how the
     *     new object is set up (or configured).  Since this is a superclass,
     *     the comment should be fairly generic.
     *     
     * @param game      What is this for?
     * @param position  What is this for?
     */	
	public Effect (GameState game, Point position)
	{
		this.game = game;
		this.position = position;
	}
	
	/**
	 * Draws the image associated with this effect, centered on the effect's
	 * location.  (Note:  It is up to the subclass to initialize the image
	 * variable.)
	 * 
	 * @param g a graphics context
	 */
	public void draw (Graphics2D g)
	{
        Point c = getLocation();
        
        g.drawImage(image,  c.x-image.getWidth()/2, c.y-image.getHeight()/2, null); 
	}
	
	/**
	 * Returns the location of this effect.
	 * 
	 * Caution:  The returned Point object may / may not be stored
	 * in this effect.  The caller should not change the contents of the
	 * returned Point object.
	 * 
	 * @return a Point object that corresponds to the location of this effect
	 */
	public Point getLocation ()
	{
		return position;
	}
}
