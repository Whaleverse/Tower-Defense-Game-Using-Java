/**
 * This class create a saltTower
 * 
 * @author Yuntong Lu(u1060544)
 * @version April 22, 2017
 */
package game;

import java.awt.Point;

public class SaltTower extends Tower
{

	private int frameCount;
	
    /** Constructor - contract needed.  
     * 
     * get the art work and get the position
     * initial some values
     *     
     * @param game      a game object hold the GameState
     * @param position  a position helps me to find locations
     */
	public SaltTower (GameState game, Point p)
	{
		super(game, p);
		
		this.image = ResourceLoader.getLoader().getImage("salt.png");
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
        	if (frameCount % 30 ==0)
        	{
        		// use flying salt to kill
        	game.addAnimatable(new FlyingSalt (game, new Point(position), c.getLocation().x, c.getLocation().y));
        		// kill!
        	game.killingEnemy(c);
        	}
        	
        }
	}

}
