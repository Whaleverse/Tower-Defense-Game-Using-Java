package game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * A path test panel is a GUI panel that displays a tower
 * defense path on the screen, and animates a small object
 * moving along the path.
 * 
 * This class won't be part of the final project - we're just
 * using it for testing.
 * 
 * @author Peter Jensen and yuntong lu (u1060544)
 * @version April 22, 2017
 */
public class TowerDefense extends JPanel implements Runnable, ActionListener, 
                                                    MouseListener,
                                                    MouseMotionListener
{
    // This constant avoids an obnoxious warning, but it is totally unused by us.
    //   It would only be relevant if we were using object serialization.
    
    private static final long serialVersionUID = 42L;
    
    // Game state object
    
    private GameState state;
    
    
    // Methods

    /**
     * The application entry point.
     * 
     * @param args unused
     */
    public static void main (String[] args)
    {
        // Main runs in the 'main' execution thread, and the GUI
        //   needs to be built by the GUI execution thread.
        //   Ask the GUI thread to run our 'run' method (at some
        //   later time).
        
        SwingUtilities.invokeLater(new TowerDefense(1));
        
        // Done.  Let the main thread of execution finish.  All the
        //   remaining work will be done by the GUI thread.
    }
    
    // For debugging only
    public TowerDefense (int i)
    {
         System.out.println("In the constructor");	
    }
    
    /**
     * Builds the GUI for this application.  This method must
     * only be called/executed by the GUI thread. 
     */
    public void run ()
    {
        // Set the size of this panel to match the size of the image.
        
        Dimension panelSize = new Dimension(800, 600);
        
        this.setMinimumSize(panelSize);
        this.setPreferredSize(panelSize);
        this.setMaximumSize(panelSize);
        
        // Build the GUI
    	
        JFrame f = new JFrame("Path Tester");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f.setContentPane(this);
        
        f.pack();
        f.setLocationRelativeTo(null);  // Centers window
        f.setVisible(true);   
            
        // Build the game state
        
        state = new GameState();
        
        // Create a timer (for animation), have it call our actionPerformed
        //   method 60 times a second.  (We must start it.)
            
        Timer t = new Timer(17, this);  // The second parameter is the object to call
        t.start();     
        
        // Listen to our own mouse button presses.
        
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    
    /**
     * Draws the image, path, and the animating ball.
     * 
     * (The background is not cleared, it is assumed the backdrop
     * fills the panel.)
     * 
     * @param g the graphics object for drawing on this panel
     */
    @Override
    public void paint (Graphics g)
    {
    	// Graphics objects are inconvenient.  Fortunately, JPanels use
        //   Graphics2D objects (a subclass of the Graphics class).
        //
        // The GUI documentation indicates that our object, g, is actually
        //   a Graphics2D object, so cast it and use it as such.
        
        state.draw((Graphics2D)g);
    }
    
    /**
     * The actionPerformed method is called (from the GUI event loop)
     * whenever an action event occurs that this object is lisening to.
     * 
     * For our test panel, we assume that the Timer has expired, so
     * we advance our small sphere along the path.
     * 
     * @param e the event object 
     */
    @Override
    public void actionPerformed (ActionEvent e)
    {
    	// if the game is playing and not over
    	if (state.getPlaying() && !state.isGameOver)
    	{
    		state.update();
        
    		repaint();  // Important
    	}
    }
    
    /** 
     * The mousePressed event is called when a mouse button is first pressed.
     * If the click is a left-button, a point is added to the path.  If the
     * click is a right-button, a point is removed from the path.
     * 
     * @param e the event object 
     */    
    @Override 
    public void mousePressed(MouseEvent e){
    
    	state.setMousePressed();
    	state.startgame(); // is playing will be true
    	
    	// the press after game over
    	// game over will be false
    	if (state.isPlaying)
    		state.endGameOver();
    	
    	// the tower can only be choose in the menu bar and close to the picture 
    	if (state.getMousePos().x > 675 && state.getMousePos().x < 725
    			&& state.getMousePos().y > 225 && state.getMousePos().y < 375)
    		state.setChooseTower();
    }

    /* Unused mouse events - notice empty bodies on the same line.
     *   We need these methods becuase we implement MouseListener,
     *   but we don't use them.
     */
    
    @Override public void mouseClicked (MouseEvent e) {}
    @Override public void mouseEntered (MouseEvent e) {}
    @Override public void mouseExited  (MouseEvent e) {} 
    @Override public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e)
	{
	    state.setMousePos(e.getPoint());
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	    state.setMousePos(e.getPoint());
	}
    
}
