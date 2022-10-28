/**
 * The beer tower frame can appears on the menu
 * and can be selected  
 * 
 * @author Yuntong Lu(u1060544)
 * @version April 22, 2017
 */
package game;

import java.awt.Point;

public class BeerTowerMenuItem extends Effect {
	
	
    /** Constructor - contract needed.  
     * 
     * get the art work and get the position
     * initial some values
     *     
     * @param game      a game object hold the GameState
     * @param position  a position helps me to find locations
     */
	public BeerTowerMenuItem (GameState game, Point position)
	{
		super(game, position);
		
		image = ResourceLoader.getLoader().getImage("beer.png");
	}
	
    /**
     * Update - called once a frame on active effects.
     * (Detail needed - students should breifly descrive the behavior
     * of this type of object.)
     */
	@Override
	public void update()
	{
		// if user selected the beer tower, and the credits is enough
		// user can drag the tower 
        if (game.getChooseTower() && game.getMousePos().distance(position) < 20
        		&& game.credits >= 2000)
        {
        	// can drag the tower
        	game.addAnimatable(new BeerTowerMoving(game, position));
        	//cost money
        	game.adjustCredits(-2000);
        	//when done, reset
        	game.clearChooseTower();
        }
        		
	}


}
