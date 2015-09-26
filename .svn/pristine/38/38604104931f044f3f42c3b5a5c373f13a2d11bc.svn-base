import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Creates the gold object - was used previously until we decided to let bags contain an emerald instead of gold.
 * @author localmgr
 *
 */
public class Gold extends AbstractBlock{
	private Color color1 = Color.GREEN;
	final int height = 800;
	final int width = 800;
	
	/**
	 * Constructs the gold object
	 * @param x
	 * @param y
	 */
	public Gold(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * The draw method for the Emerald - used to draw the emerald to the screen
	 */
	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		Rectangle2D temp = new Rectangle2D.Double(this.x, this.y, 40, 40);
		g.setColor(this.color1);
		g.fill(temp);
		
	}

	/**
	 * Returns if the object has been collected by a hero by checking x and y
	 * coordinates
	 * 
	 * @param hero
	 * @return
	 */
	public boolean getPicked(Hero hero){
	
		
		if(Math.abs(hero.x-this.x)<=20 && Math.abs(hero.y-this.y)<=20){
		
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
		return "Gold";
	}

}
