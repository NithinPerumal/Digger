import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


/**
 * Done This is an object that was previously used but is no longer called..
 *
 * @author weny, peruman.
 *         Created Oct 31, 2014.
 */
public class Block extends AbstractBlock {


	private Color color=Color.YELLOW;
	
	public Block(int x, int y) {
		super(x, y);
	}

	
	
	@Override
	public void draw(Graphics2D g){
		Rectangle temp = new Rectangle(this.x*40, this.y*40,40,40);
		g.setColor(this.color);
		g.fill(temp);
	}



	@Override
	public String returnType() {
		// TODO Auto-generated method stub
		return "Block";
	}
	

	
}
