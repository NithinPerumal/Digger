import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;

/**
 * This is the gold bag object.  It is the bag that a hero can push around or drop to break open for emeralds or to kill
 * enemies.
 * @author peruman, weny
 *
 */
public class GoldBag extends AbstractCharacter {
	final int height = 800;
	final int width = 800;
	private boolean isFalling = false;
	private int time =0;
	private int fallDistance = 0;
	private DigGame game;
	private BufferedImage img;

	/**
	 * Constructs the goldBag object.
	 * @param x
	 * @param y
	 * @param digGame
	 */
	public GoldBag(int x, int y, DigGame digGame) {
		
		super();
		this.x = x;
		this.y = y;
		this.game = digGame;

		try {
			this.img = ImageIO.read(new File("src/GoldBag.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

	}

	/**
	 * Draws the image to the goldBag object
	 */
	@Override
	public void draw(Graphics2D g) {
		// Done Auto-generated method stub

		g.drawImage(this.img, this.x, this.y, 40, 40, null);
	}
	
	/**
	 * Returns the image of the gold bag.
	 * @return
	 */
	public boolean getimg() {
		if (this.img == null) return true;
		else return false;
	}
	
	/**
	 * This method calls many other methods on the object to check differnet properties of it.
	 * @throws InterruptedException
	 */
	public void TimePassed() throws InterruptedException {
		if(!this.isDead()){
		this.checkUnder();
		this.push();
		this.checkKill();
		this.updatePosition();}
	}
	
	/**
	 * Updates the position on screen of the gold bag.
	 */
	@Override
	public void updatePosition(){
		this.checkEdge();
		super.updatePosition();
		this.fallDistance += this.y_Velocity;
		
	}
	
	/**
	 * Checks if the gold bag is killing any monsters or the hero.
	 */
	private void checkKill() {
		if (this.isFalling) {
			for (Hobbin i : this.game.getMonster1()) {

				if (Math.abs(this.x - i.x) < 40 && i.y - this.y < 40) {
					i.isDead = true;
				}
			}
			for (Nobbin n : this.game.getMonsters()) {

				if (Math.abs(this.x - n.x) < 40 && Math.abs(this.y - n.y) < 40) {
					n.isDead = true;
				}
			}

			if (Math.abs(this.x - this.game.getHero().x) < 20
					&& Math.abs(this.y - this.game.getHero().y) < 20) {
				this.game.getHero().isDead = true;
			}
		}

	}

	
	//
	/**
	 * Done Allows the hero to push the gold back around
	 * 
	 */
	public void push() {
	
		if (this.y == this.game.getHero().y && Math.abs(this.x - this.game.getHero().x) < 40) {
			this.x_Veloctiy = (int) this.game.getHero().previous.getX();
			this.time = 41;
		} else {
			this.x_Veloctiy = 0;
			if(Math.abs(this.x - this.game.getHero().x)>70|| this.y != this.game.getHero().y){
				this.fix();
			}
		}
		
	}
	
	/**
	 * Checks under the GoldBag as it moves and only drops further if it has no block under it.
	 * @throws InterruptedException
	 */
	public void checkUnder() throws InterruptedException {
		if(this.y==800){
			this.isDead = true;
		}
		if (this.game.getBlock()[this.x / 40][this.y / 40 + 1] == 0) {
			if(this.time<40 && !this.isFalling){
				this.time++;
			}
			else{
				if(this.x%40 ==0){
				this.time=0;
				this.y_Velocity = 2;
				this.isFalling = true;}}

		} else {
			this.y_Velocity = 0;
			this.isFalling = false;
			if(this.fallDistance >=80){
				this.isDead = true;
			}
			this.fallDistance =0;

		}

	}
	
	/**
	 * Ensures the bag only falls along columns in the grid and not down at a random x-coordinate.
	 */
	public void fix(){
	
		if(this.x %40 !=0){
			if(this.x % 40 >=20){
				this.x ++; 
			}
			else{
				this.x --;
			}
		}
		
	}
	
	/**
	 * Checks the edges of the game screen so the gold cant just move off the screen
	 */
	public void checkEdge() {

		if (this.x_Veloctiy < 0 && this.x ==0) {
			this.x_Veloctiy = 0;
		}
		if (this.x_Veloctiy > 0 && this.x == this.width) {
			this.x_Veloctiy = 0;
		}
		if (this.y_Velocity < 0 && this.y ==0) {
			this.y_Velocity = 0;
		}
		if (this.y_Velocity > 0 && this.y == this.height) {
			this.y_Velocity = 0;
		}
	}
	
	/**
	 * Returns the x-coordinate of the Gold Bag.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Returns the y-coordinate of the Gold Bag.
	 */
	public int getY() {
		return this.y;
	}

}
