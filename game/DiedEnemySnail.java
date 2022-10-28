/**
 * condition when the enemy should be died 
 * 
 * @author Yuntong Lu(u1060544)
 * @version April 22, 2017
 */
package game;

import java.awt.Point;

public class DiedEnemySnail extends Effect {
	
	private int frameCount;

	
	/**
	 * get the art work and get the position
     * initial some values
     * 
     * @param game      a game object hold the GameState
     * @param position  a position helps me to find locations
	 */
	public DiedEnemySnail (GameState game, Point position)
	{
		super (game, position);
		
		image = ResourceLoader.getLoader().getImage("splat.png");
		
		this.frameCount = 0;
	}

	
    /**
     * Update - called once a frame on active effects.
     * (Detail needed - students should breifly descrive the behavior
     * of this type of object.)
     */
	@Override
	public void update() {
		
		// enemy dying
		frameCount ++;
		if(frameCount == 15)
			game.removeAnimatable(this);
		
	}
	
	

}
