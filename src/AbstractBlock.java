import java.awt.Graphics2D;


/**
 * Done The abstract class for block objects.  This class carries the common methods and properties for all types of block
 * objects.
 *
 * @author weny, peruman.
 *         Created Oct 31, 2014.
 */
public abstract class AbstractBlock {

	protected int x;
	protected int y;

	
	
	public AbstractBlock(int x,int y){
		this.x = x;
		this.y = y;
		
	}
	
	/**
	 * Sets the location of a block to a given x and y coordinate
	 * @param x
	 * @param y
	 */
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Draws a given block on the screen.
	 * @param g
	 */
	public abstract void draw(Graphics2D g);
	
	/**
	 * Ended up being unused but was initially used to return a String with the type of block
	 * @return
	 */
	public abstract String returnType();


}
