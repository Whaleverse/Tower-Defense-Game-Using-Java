/**
 * the bullet of the salt tower
 * 
 * @author Yuntong Lu(u1060544)
 * @version April 22, 2017
 */
package game;

import java.awt.Point;

public class FlyingSalt extends Effect{
	
	private int Ex, Ey; // enemy position
	private int frameCount;

	
    /** Constructor - contract needed.  
     * 
     * get the art work and get the position
     * initial some values
     *     
     * @param game      a game object hold the GameState
     * @param position  a position helps me to find locations
     */
	public FlyingSalt (GameState game, Point position, int Ex, int Ey)
	{
		super (game, position);
		
		image = ResourceLoader.getLoader().getImage("salt_crystals.png");
		
		this.Ex = Ex;
		this.Ey = Ey;
		this.frameCount = 0;
	}

	@Override
	public void update() {
		
		position.x += (Ex - position.x)/10;
		position.y += (Ey - position.y)/10;
		
		frameCount ++;
		if(frameCount == 15)
			game.removeAnimatable(this);
		
	}
	
	

}
