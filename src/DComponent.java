import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Done This class is used to handle keypresses as well as repainting, etc. .
 * 
 * @author weny, peruman. Created Oct 31, 2014.
 */
public class DComponent extends JComponent {
	public Hero hero;
	public Weapon bullet;
	private BufferedImage img;
	private boolean isPaused;
	private String name;

	/**
	 * This class is used to repaint the frame repeatedly
	 * @author peruman, weny
	 *
	 */
	class Repainter implements Runnable {
		@Override
		public void run() {
			// Periodically asks Java to repaint this component
			try {
				while (true) {
					if (!isPaused) {
						Thread.sleep(REPAINT_INTERVAL_MS);

						repaint();
					}
				}
			} catch (InterruptedException exception) {
				// Stop when interrupted
			}
		}
	};

	private static final int FRAMES_PER_SECOND = 30;
	private static final long REPAINT_INTERVAL_MS = 60 / FRAMES_PER_SECOND;

	DigGame game;
	private Repainter repainter = new Repainter();
	private Thread run = new Thread(this.repainter);
	private int prevx = 0;
	private int prevy = 0;
	
	/**
	 * This constructor is used to construct the game and characters out of DigGame.
	 * @param game
	 * @param name
	 */
	public DComponent(DigGame game, String name) {
		this.game = game;
		this.hero = this.game.getHero();
		this.bullet = this.game.getWeapon();
		this.name = name;
		setFocusable(true);

		DigListner keyboard = new DigListner();
		this.addKeyListener(keyboard);
		try {
			img = ImageIO.read(new File("src/five.jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		;

		this.run.start();

	}

	public Thread getRunnable() {
		return this.run;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * This method is used to move you up a level;
	 */
	public void upLevel() {
			DComponent.this.game.setLevel(DComponent.this.game.getLevel() - 1);
			try {
				DComponent.this.game.Initialize();
			} catch (IOException exception) {

				exception.printStackTrace();
			}
	}
	
	/**
	 * This method was used to obtain the previous x-velocity of the hero to use this x-velocity with the bullet.
	 * @param x
	 */
	private void setprevx(int x) {
		this.prevx = 0;
		this.prevy = 0;
		this.prevx = x;
	}

	/**
	 * This method was used to obtain the previous y-velocity of the hero to use this y-velocity with the bullet.
	 * @param x
	 */
	private void setprevy(int y) {
		this.prevx = 0;
		this.prevy = 0;
		this.prevy = y;
	}
	
	/**
	 * Gets the hero's previous x
	 * @return
	 */
	private int getprevx() {
		return this.prevx;
	}

	/**
	 * Gets the hero's previous x
	 * @return
	 */
	private int getprevy() {
		return this.prevy;
	}

	/**
	 * This paintComponent is used to draw all the characters and blocks into the frame.
	 */
	@Override
	protected void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2;

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (this.game.getBlock()[j][i] == 1) {

					g.drawImage(this.img, j * 40, i * 40, 40, 40, null);
				}
			}
		}

		for (int i = 0; i < this.game.getEmerald().size(); i++) {
			this.game.getEmerald().get(i).draw(g);
		}

		//
		for (Nobbin m : this.game.getMonsters()) {
			if (!m.isDead)
				m.draw(g);

		}

		for (Hobbin m1 : this.game.getMonster1()) {
			if (!m1.isDead)
				m1.draw(g);
		}

		for (GoldBag b : this.game.getBags()) {
			if(!b.isDead()){
			b.draw(g);}
		}

		for (Gold gold : this.game.getGold()) {
			gold.draw(g);
		}

		if (this.game.getHero().isDead == false)
			this.game.getHero().draw(g);

		this.game.getWeapon().draw(g);

		g2.setColor(Color.CYAN);
		Font f = new Font("Calibri", Font.BOLD, 20);
		g2.setFont(f);
		g2.drawString("Score: " + this.game.getScore(), 10, 25);
		g2.drawString("Player: " + this.name, 110, 25);
		g2.drawString("Lives: " + this.game.getLives(), 300, 25);
		setFocusable(true);
		requestFocusInWindow();
	}

	/**
	 * Gets the previous velocity of the hero
	 * @return
	 */
	public double getPrevious() {
		if (this.game.getHero().y_Velocity != 0) {
			return this.game.getHero().y_Velocity;
		} else if (this.game.getHero().x_Veloctiy != 0) {
			return this.game.getHero().x_Veloctiy;
		}
		return 0;
	}
	
	/**
	 * This method is used to save a player's name, score, lives and level into a text file so they may continue later;
	 */
	protected void Save() {
		try {
			PrintWriter writer = new PrintWriter(new File(
					"src/LevelData"));
			writer.println(this.game.getLevel() + " " + this.game.getScore() + " " + this.game.getLives() + " " + this.name);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is set up for the weapon to shoot;
	 */
	public void shoot() {
		// Point2D spawn = this.game.getHero().getPosition();
		this.game.getWeapon().setPosition(this.game.getHero().getX(),
				this.game.getHero().getY());
		DComponent.this.bullet.isDead = false;
	}

	/**
	 * This KeyListener is the class that detects all keypresses and responds accordingly.
	 * @author localmgr
	 *
	 */
	public class DigListner implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {

			if (e.getKeyCode() == 38) {
				if (!DComponent.this.hero.correctY()) {
					DComponent.this.hero.setPrevious();
				} else {
					DComponent.this.hero.y_Velocity = -4;

					DComponent.this.setprevy(-6);

					DComponent.this.hero.previous = new Point(0, -4);
				}
			} else if (e.getKeyCode() == 40) {
				if (!DComponent.this.hero.correctY()) {
					DComponent.this.hero.setPrevious();
				} else {
					DComponent.this.hero.y_Velocity = 4;

					DComponent.this.setprevy(6);

					DComponent.this.hero.previous = new Point(0, 4);
				}
			} else if (e.getKeyCode() == 37) {
				if (!DComponent.this.hero.correctX()) {
					DComponent.this.hero.setPrevious();
				} else {
					DComponent.this.hero.x_Veloctiy = -4;

					DComponent.this.setprevx(-6);

					DComponent.this.hero.previous = new Point(-4, 0);
				}
			} else if (e.getKeyCode() == 39) {
				if (!DComponent.this.hero.correctX()) {
					DComponent.this.hero.setPrevious();
				} else {
					DComponent.this.hero.x_Veloctiy = 4;

					DComponent.this.setprevx(6);

					DComponent.this.hero.previous = new Point(4, 0);
				}
			} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				shoot();
				DComponent.this.bullet.x = DComponent.this.hero.getX();
				DComponent.this.bullet.y = DComponent.this.hero.getY();
				DComponent.this.bullet.x_Veloctiy = DComponent.this.getprevx();
				DComponent.this.bullet.y_Velocity = DComponent.this.getprevy();
			}

			else if (e.getKeyCode() == KeyEvent.VK_P) {
				if (DComponent.this.isPaused == false
						&& DComponent.this.game.isPaused1 == false) {
					DComponent.this.isPaused = true;
					DComponent.this.game.isPaused1 = true;
				} else {
					DComponent.this.isPaused = false;
					DComponent.this.game.isPaused1 = false;
				}
			}

			else if (e.getKeyCode() == KeyEvent.VK_Q) {
				System.exit(0);
			}
			
			else if (e.getKeyCode() == KeyEvent.VK_S) {
				Save();
			}
			
			else if (e.getKeyCode() == KeyEvent.VK_U) {
				DComponent.this.hero.isDead = false;
				for (Nobbin m : DComponent.this.game.getMonsters()) {
					System.out.println("null setter");
					m.isDead = true;
					m = null;
				}
				for (Hobbin h : DComponent.this.game.getMonster1()) {
					System.out.println("null setter");
					h.isDead = true;
					h = null;
				}
				DComponent.this.game
						.setLevel(DComponent.this.game.getLevel() + 1);
				try {
					DComponent.this.game.Initialize();
				} catch (IOException exception) {

					exception.printStackTrace();
				}
			}

			else if (e.getKeyCode() == KeyEvent.VK_D) {
				DComponent.this.hero.isDead = false;
				for (Nobbin m : DComponent.this.game.getMonsters()) {
					m.isDead = true;
					m = null;
				}
				for (Hobbin h : DComponent.this.game.getMonster1()) {
					h.isDead = true;
					h = null;
				}
				DComponent.this.game
						.setLevel(DComponent.this.game.getLevel() - 1);
				try {
					DComponent.this.game.Initialize();
				} catch (IOException exception) {

					exception.printStackTrace();
				}
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// if (e.getKeyCode() == 38) {
			// DComponent.this.hero.y_Velocity = 0;
			// }
			// if (e.getKeyCode() == 40) {
			// DComponent.this.hero.y_Velocity = 0;
			// }
			// if (e.getKeyCode() == 37) {
			// DComponent.this.hero.x_Veloctiy = 0;
			// }
			// if (e.getKeyCode() == 39) {
			// DComponent.this.hero.x_Veloctiy = 0;
			// }

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub.

		}

	}

}
