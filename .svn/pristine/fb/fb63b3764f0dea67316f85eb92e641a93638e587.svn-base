import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Is the weapon of the hero.  The hero can kill Nobbins and Hobbins with this weapon.
 * @author peruman
 *
 */
public class Weapon extends AbstractCharacter {
	private Color weapon_color = Color.CYAN;
	// private int xvelocity;
	// private int yvelocity;
	final int height = 800;
	final int width = 800;
	
	/**
	 * Constructs the weapon object for the hero to use.
	 * @param x
	 * @param y
	 * @param xvelocity
	 * @param yvelocity
	 */
	public Weapon(int x, int y, int xvelocity, int yvelocity) {
		this.x = x;
		this.y = y;
		this.x_Veloctiy = xvelocity;
		this.y_Velocity = yvelocity;
	}
	
	/**
	 * Draws the weapon object to the screen when its created.
	 */
	@Override
	public void draw(Graphics2D g) {
		Ellipse2D.Double temp = new Ellipse2D.Double(this.x + 10, this.y - 10,
				20, 20);
		g.setColor(this.weapon_color);
		g.fill(temp);

	}
	
	/**
	 * Checks the edges of the map so as not to violate the game's rules.
	 */
	public void checkEdge() {

		if (this.x_Veloctiy < 0 && this.x <= 0) {
			this.x_Veloctiy = 0;
			this.y_Velocity = 0;
			setPosition(1500, 1500);
		}
		if (this.x_Veloctiy > 0 && this.x > this.width - 40) {
			this.x_Veloctiy = 0;
			this.y_Velocity = 0;
			setPosition(1500, 1500);
		}
		if (this.y_Velocity < 0 && this.y <= 0) {
			this.x_Veloctiy = 0;
			this.y_Velocity = 0;
			setPosition(1500, 1500);
		}
		if (this.y_Velocity > 0 && this.y > this.height - 40) {
			this.x_Veloctiy = 0;
			this.y_Velocity = 0;
			setPosition(1500, 1500);

		}
	}
	
	/**
	 * Sets the velocity of the bullet if it is to travel in the x direction
	 * @param xvel
	 */
	public void setXVelocity(int xvel) {
		this.x_Veloctiy = xvel;
	}

	/**
	 * Sets the velocity of the bullet if it is to travel in the y direction
	 * @param yvel
	 */
	public void setYVelocity(int yvel) {
		this.x_Veloctiy = yvel;
	}

}
