package game;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * Represents a salt tower that the user is moving with the mouse.
 * 
 * @author Peter Jensen and yuntong lu (u1060544
 * @version April 22, 2017
 */
public class SaltTowerMoving extends Effect
{

    /** Constructor - contract needed.  
     * 
     * get the art work and get the position
     * initial some values
     *     
     * @param game      a game object hold the GameState
     * @param position  a position helps me to find locations
     */
    public SaltTowerMoving (GameState game, Point position)
	{
		super(game, position);
		
		image = ResourceLoader.getLoader().getImage("salt.png");
	}
	
    
    /**
     * Update - called once a frame on active effects.
     * (Detail needed - students should breifly descrive the behavior
     * of this type of object.)
     */
	@Override
	public void update()
	{
		// the tower will follow the mouse until
		// the user deciding to put the tower down 
		
		// follow the mouse
		position = game.getMousePos();
		BufferedImage temp = ResourceLoader.getLoader().getImage("path_2.jpg");
		
		// a position of the mouse
		int x = this.getLocation().x;
		int y = this.getLocation().y;
		
		// if the place can fit the tower 
		// user can put it down
		// a tower will be on the map		
		if (game.getMousePressed() && position.x < 600 
				&& !(temp.getRGB(x, y) < -10200000 && temp.getRGB(x, y) > -10500000))
		{
			// put down tower
			game.addAnimatable(new SaltTower(game, position));
			// reset
			game.removeAnimatable(this);
			game.clearMousePressed();
			
		}
		
		// place cannot fit the tower
		else
			game.clearMousePressed();
		
	}

}
