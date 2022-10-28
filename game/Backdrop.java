package game;

import java.awt.Graphics2D;

/**
 * Represents the background of the game.
 * 
 * @author Peter Jensen
 * @version April 12, 2017
 */
public class Backdrop implements Animatable
{
    // Note that the backdrop class does not inherit from anything else.
    //   It doesn't need object variables, and no constructor is needed.
    //   It just needs to draw the background.
    // 
    // For convenience, we have it implement the animatable interface. 
    //   This way, the game can add a Backdrop object as the first
    //   entry in the active list of animatable objects, and the
    //   background will then 'draw itself' from the game's perspective.
    //
    // Note - we could add variables and more fancy drawing.

	@Override
	public void update()
	{
		// Not needed - we don't do anything with our background.		
	}

	@Override
	public void draw(Graphics2D g)
	{
        // Draw the background.
        
        g.drawImage(ResourceLoader.getLoader().getImage("path_2.jpg"),  0, 0, null);   
		
	}

}
