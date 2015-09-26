import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This is the Hobbin object. It is one of the antagonists of the game.
 * 
 * @author peruman, weny
 * 
 */
public class Hobbin extends AbstractCharacter {
	private BufferedImage img1;
	private BufferedImage img2;
	private boolean change;
	private int count;

	/**
	 * Constructs the Hero object.
	 * @param x
	 * @param y
	 * @param digGame
	 */
	public Hobbin(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		try {
			this.img1 = ImageIO.read(new File("src/nobbin.png"));
			this.img2 = ImageIO.read(new File("src/hobbin.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}
	
	/**
	 * Draws the image to the hero object
	 */
	@Override
	public void draw(Graphics2D g) {
		if (this.change) {
			g.drawImage(this.img1, this.x, this.y, 40, 40, null);
		} else {
			g.drawImage(this.img2, this.x, this.y, 40, 40, null);
		}
	}
	/**
	 * One of the methods used to control the AI of the hobbin
	 */
	public void check() {
		if (this.count > 500) {
			this.change = !this.change;
			this.count = 0;
		} else {
			this.count++;
		}
	}
	/**
	 * Returns the Buffered Image used as the image of the hobbin
	 * @return
	 */
	public boolean getimg() {
		if (this.img1 == null)
			return true;
		return false;
	}
	
	/**
	 * Checks the heros movements and tries to track him.
	 * @param HeroPosition
	 */
	public void checkDirect(Point2D HeroPosition) {

		if (this.x != HeroPosition.getX() && this.y % 40 == 0) {
			this.x_Veloctiy = (this.x > HeroPosition.getX()) ? -1 : 1;
			this.y_Velocity = 0;
		} else {
			if (this.x % 40 == 0) {
				this.y_Velocity = (this.y > HeroPosition.getY()) ? -1 : 1;
				this.x_Veloctiy = 0;
			}
		}

	}
	/**
	 * Updates the position of the Hobbin based on its velocity.
	 */
	@Override
	public void updatePosition() {
		this.check();
		this.x += this.x_Veloctiy;
		this.y += this.y_Velocity;

	}
	
	/**
	 * Sets the Hobbins position to a desired place on the map.
	 */
	@Override
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
