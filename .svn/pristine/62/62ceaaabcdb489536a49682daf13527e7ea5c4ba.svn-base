import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is the Hero object.  It is the main controllable character of the game
 * @author peruman, weny
 *
 */
public class Hero extends AbstractCharacter{
	private Color color1= Color.blue;
	final int height = 800;
	final int width = 800;
	public Point previous= new Point(0,0);
	private BufferedImage img;

	/**
	 * Constructs the Hero object.
	 * @param x
	 * @param y
	 * @param digGame
	 */
	public Hero(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		 try {
	   			this.img = ImageIO.read(new File("src/hero.png"));
	   		} catch (IOException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		};

	}

	/**
	 * Draws the image to the hero object
	 */
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(this.img, this.x, this.y, 40, 40, null);
	}
	
	/**
	 * Returns the image of the hero.
	 * @return
	 */
	public boolean getimg() {
		if (this.img == null) return true;
		else return false;
	}
	
	/**
	 * Checks the edges of the game screen so the hero cant just move off the screen
	 */
	public void checkEdge(){
	
		if(this.x_Veloctiy<0 && this.x<=0){
			this.x_Veloctiy=0;
		}
		if(this.x_Veloctiy>0 && this.x > this.width-40){
			this.x_Veloctiy=0;
		}
		if(this.y_Velocity<0 && this.y <= 0){
			this.y_Velocity=0;
		}
		if(this.y_Velocity>0 && this.y>this.height-40){
			this.y_Velocity=0;
		}
	}
	
	/**
	 * Is a method used in ensuring the hero stays on the grid by x-coordinates.
	 * @return
	 */
	public boolean correctX(){
		return this.y%40==0;
	}
	
	/**
	 * Is a method used in ensuring the hero stays on the grid by y-coordinates.
	 * @return
	 */
	public boolean correctY(){
		return this.x%40==0;
	}

	/**
	 * Stores the current velocity into the previous variable for use where needed for the weapon.
	 */
	public void updatePrevious(){
		if(this.x_Veloctiy==0 && this.y_Velocity==0){
			return;
		}
		this.previous.x = this.x_Veloctiy;
		this.previous.y = this.y_Velocity;
	}
	
	/**
	 * Sets the current velocity to the previous velocity.
	 */
	public void setPrevious(){
		this.x_Veloctiy = this.previous.x;
		this.y_Velocity = this.previous.y;
	}
	

	

}
