/**
 * This class create a beerTower
 * 
 * @author Yuntong Lu(u1060544)
 * @version April 22, 2017
 */
package game;

import java.awt.Point;

public class BeerTower extends Tower{
	
	private int frameCount;
	
	
    /** Constructor - contract needed.  
     * 
     * get the art work and get the position
     * initial some values
     *     
     * @param game      a game object hold the GameState
     * @param position  a position helps me to find locations
     */
	public BeerTower (GameState game, Point p)
	{
		super(game, p);
		
		this.image = ResourceLoader.getLoader().getImage("beer.png");
	}
	
     
    /**
     * Update - called once a frame on active effects.
     * (Detail needed - students should breifly descrive the behavior
     * of this type of object.)
     */
	@Override
	public void update()
	{
		// Aim down the nearest enemy
		
		
        Enemy c = game.findNearestEnemy(position);
        if (c == null)
            return;
        
        // if the enemy is at the range
        if (c.getLocation().distance(position) < 100)
        {
        	frameCount++;
        	
        	// kill the enemy 
        	if (frameCount % 15 ==0) // fire rate
        	{
        		// use flying bubble to kill
        	game.addAnimatable(new FlyingBubble (game, new Point(position), c.getLocation().x, c.getLocation().y));
        		// kill!
        	game.killingEnemy(c);
        	}
        	
        }
	}
}
