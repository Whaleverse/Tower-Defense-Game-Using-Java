package game;

import java.awt.image.BufferedImage; 
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This class represents a central point for accessing
 * images and text files.  Functions exist for loading
 * images and Path objects, and no image or path will ever
 * be loaded from a file twice.  (When an image or path
 * is loaded, it is saved in a map so that if it is
 * needed later, it can be easily and quickly returned.)
 * 
 * A ResourceLoader object is needed to call the functions
 * that load images or Path objects.  A static method exists
 * for getting a ResourceLoader object.  (You cannot create
 * one directly from outside of the class.)
 * 
 * Only one ResourceLoader object will ever be created during
 * any application.  (This is called a singleton.)
 * 
 * @author Peter Jensen
 * @version April 11, 2017
 */
public class ResourceLoader
{
    /* These static members are of the class, but not part of any object. */
    
    private static ResourceLoader singleInstance; 
    
    /**
     * Returns the singleton ResourceLoader object that can
     * be used to load images, etc.
     * 
     * @return a ResourceLoader object.
     */
    public static ResourceLoader getLoader ()
    {
        if (singleInstance == null) 
            singleInstance = new ResourceLoader();
        
        return singleInstance;
    }
    
    /* Object (non-static) members below this point */
    
    // Fields.  These maps are where we store the loaded data.
    
	Map<String,BufferedImage> imageMap;
	Map<String,Path> pathMap;
	
	/**
	 * Constructor - builds the maps.  This constructor
	 * is private, and not intended for general use.  It is
	 * only called from the getLoader() method above.
	 */
    private ResourceLoader ()
    {
    	// Set up anything this resource loader needs.
    	
    	imageMap = new HashMap<String,BufferedImage>();
    	pathMap  = new HashMap<String,Path>();
    }
    
    /**
     * Returns a Path object built from the specified file.
     * The filename should indicate a file located in the
     * "resources" directory of the Eclipse Java project.
     * The file will be opened, the data read, a Path object
     * built, and the Path object returned.
     * 
     * If the specified file has already been loaded and
     * put into a Path object, the existing Path object will
     * be returned.
     * 
     * @param pathName   the filename of a path data file (.txt)
     * @return           a Path object built from that file
     */
    public Path getPath (String pathName)
    {
        // Did we already load it?  If so, just return the path.
        
    	if (pathMap.containsKey(pathName))
    		return pathMap.get(pathName);
    	
    	// Load it.
    	
    	try
        {
        	// Locate the file, get a stream for it.
        	
        	ClassLoader myLoader = this.getClass().getClassLoader();
            InputStream pointStream = myLoader.getResourceAsStream("resources/" + pathName);
            
            // Build the path.
            
            Path path;
            path = new Path(new Scanner(pointStream));
            
            // Put the path in the map (with its name as the key).
            
            pathMap.put(pathName,  path);
            
            // Return the path.
            
            return path;
        }
        catch (Exception e)
        {
            // On error, just print a message and exit.  There should never
            //   be any missing images, so this error is fatal for the application.
            //   (You should make sure the files are in the correct place.)
            
            System.err.println ("Could not load this image file: " + pathName);
            System.exit(0);  // Bail out - application ends.
            return null;  // Never executes
        }    
    }
    
    /**
     * Returns an image object built from the specified file.
     * The filename should indicate a file located in the
     * "resources" directory of the Eclipse Java project.
     * A BufferedImage will be loaded from the specified file.
     * 
     * If the specified file has already been loaded and
     * put into a BufferedImage object, the existing object will
     * be returned.
     * 
     * @param imageName  the filename of an image
     * @return           a BufferedImage object loaded from that file
     */
    public BufferedImage getImage (String imageName)
    {
        // Did we already load it?  If so, just return the BufferedImage object.
        
        if (imageMap.containsKey(imageName))
    		return imageMap.get(imageName);
    	
        // Load it.
        
    	try
    	{    		
    		// Locate the file, get a stream for it.
            
    		ClassLoader myLoader = this.getClass().getClassLoader();
	        InputStream imageStream = myLoader.getResourceAsStream("resources/" + imageName);
	        
	        // Read the image.
	        
	        BufferedImage result;
	        result = javax.imageio.ImageIO.read(imageStream);  // A handy helper method
	        
	        // Put the BufferedImage into our map (use the filename as the key)
	        
	        imageMap.put(imageName, result);
	        
	        // Return the image.
	        
	        return result;
    	}
    	catch (Exception e)
        {
            // On error, just print a message and exit.  There should never
    	    //   be any missing images, so this error is fatal for the application.
            //   (You should make sure the files are in the correct place.)
            
            System.err.println ("Could not load this image file: " + imageName);
            System.exit(0);  // Bail out - application ends.
            return null;  // Never executes
        }    
    }
}
