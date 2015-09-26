import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * The abstract class for character.  This class holds all the common methods between characters and their properties.
 * @author peruman, weny
 *
 */
public abstract class AbstractCharacter {
	public int x;
	public int y;
	public Color color;
	public boolean isDead=false;
	public int x_Veloctiy=0;
	public int y_Velocity=0;


	/**
	 * Update position is used to update the position of each character of type AbstractCharacter
	 */
	public void updatePosition(){
		
		this.x += this.x_Veloctiy;
		this.y += this.y_Velocity;
		
	}
	
	/**
	 * This function can set the position of a character anywhere on the game.
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter method for the x coordinate
	 * @return
	 */
	public int getX() {
		return this.x;
	}
	
	/**
	 * Getter method for the y coordinate
	 * @return
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * This is used to draw the objects to the screen
	 * @param g
	 */
	public abstract void draw(Graphics2D g);
	
	/**
	 * Stops movement of a character.
	 */
	public void freeze(){
		this.x_Veloctiy=0;
		this.y_Velocity=0;
	}
	
	/**
	 * Returns the x and y coordinates of a given character
	 * @return
	 */
	public Point2D getPosition(){
		return new Point2D.Double(this.x,this.y);
	}
	
	/**
	 * Lets the programmer know if the character has been killed.
	 * @return
	 */
	public boolean isDead(){
		return this.isDead;
	}
	

	
}
