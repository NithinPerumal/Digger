import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Is a type of AbstractBlock that is the emerald
 * 
 * @author peruman, weny
 * 
 */
public class Emerald extends AbstractBlock {

	private Color color = Color.green;
	private BufferedImage img;

	/**
	 * This is a constructor for each emerald object that is primarily used to
	 * load the image that is used in emerald
	 * 
	 * @param x
	 * @param y
	 */
	public Emerald(int x, int y) {
		super(x, y);
		try {
			this.img = ImageIO.read(new File("src/fiveemerald.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	/**
	 * The draw method for the Emerald - used to draw the emerald to the screen
	 */
	@Override
	public void draw(Graphics2D g) {
		g.drawImage(this.img, this.x, this.y, 40, 40, null);
	}

	/**
	 * Returns the BufferedImage
	 * 
	 * @return
	 */
	public boolean getimg() {
		if (this.img == null)
			return true;
		else
			return false;
	}

	/**
	 * Returns if the object has been collected by a hero by checking x and y
	 * coordinates
	 * 
	 * @param hero
	 * @return
	 */
	public boolean getPicked(Hero hero) {

		if (Math.abs(hero.x - this.x) <= 20 && Math.abs(hero.y - this.y) <= 20) {

			return true;
		}
		return false;
	}

	/**
	 * Returns if the object has been collected by a hero by checking x and y
	 * coordinates
	 * 
	 * @param hero
	 * @return
	 */
	public boolean getPicked(Weapon weapon) {

		if (Math.abs(weapon.x - this.x) <= 20
				&& Math.abs(weapon.y - this.y) <= 20) {

			return true;
		}
		return false;
	}

	/**
	 * Returns if the object has been collected by a hero by checking x and y
	 * coordinates
	 * 
	 * @param hero
	 * @return
	 */
	public boolean getPicked(Hobbin hobbin) {

		if (Math.abs(hobbin.x - this.x) <= 20
				&& Math.abs(hobbin.y - this.y) <= 20) {

			return true;
		}
		return false;
	}

	/**
	 * Returns "Emerald" as a string - was previously used but we then changed our approach making this function obsolete.
	 */
	@Override
	public String returnType() {
		// TODO Auto-generated method stub
		return "Emerald";
	}

}
