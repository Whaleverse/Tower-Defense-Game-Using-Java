package game;

import java.awt.Graphics2D;

/**
 * An animatiable object is any object that can be updated once a frame,
 * and that knows how to draw itself when needed.
 * 
 * @author Peter Jensen
 * @version April 11, 2017
 *
 */
public interface Animatable
{
	public void update ();
	public void draw (Graphics2D g);
}
