import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Done This class creates the elements and draws the graphics to the frame
 * 
 * @author weny, peruman. Created Oct 30, 2014.
 */
public class DigGame {

	private static final long UPDATE_INTERVAL_MS = 30;
	private int level = 1;
	private Hero hero;
	private Weapon weapon;
	private int[][] blockList = new int[20][20];
	private ArrayList<Nobbin> monsterList = new ArrayList<Nobbin>();
	private ArrayList<Hobbin> monsterList1 = new ArrayList<Hobbin>();
	private ArrayList<Emerald> emeraldList = new ArrayList<Emerald>();
	private ArrayList<Gold> goldList = new ArrayList<Gold>();
	private ArrayList<GoldBag> bags = new ArrayList<GoldBag>();
	public boolean isPaused1;
	public JFrame frame;
	private int startTime = (int) System.currentTimeMillis();
	private int lives;
	private String name;
	private int emeraldcount;
	private int Score = 0;
	
	/**
	 * This runnable object is used in the thread for the bags of gold
	 */
	Runnable bagRun = new Runnable() {
		@Override
		public void run() {
			try {
				while (true) {
					Thread.sleep(UPDATE_INTERVAL_MS);
					BagTimePassed();
				}
			} catch (InterruptedException exception) {
				// Stop when interrupted
			}
		}
	};

	/**
	 * This Runnable objects is used to control most of what happens on the screen.
	 */
	Runnable Hero = new Runnable() {
		@Override
		public void run() {
			try {
				while (true) {
					if (DigGame.this.getLives() > 0) {
						if (!DigGame.this.isPaused1) {
							Thread.sleep(UPDATE_INTERVAL_MS);
							HeroTimePassed();
							MonsterTimePassed();
							Monster1TimePassed();
							EmearaldTimepassed();
							WeaponTimePassed();
							MapTimePassed();
							GoldTimePassed();
							nextLevel();
							kill();
						}
					}
				}
			} catch (InterruptedException | IOException exception) {
				// Stop when interrupted
			}
		}
	};

	private Thread thread = new Thread(this.Hero);
	private Thread thread2 = new Thread(this.bagRun);

	/**
	 * Returns the hero thread
	 * @return
	 */
	public Thread getHeroRunnable() {
		return this.thread;
	}

	/**
	 * Returns the bag thread
	 * @return
	 */
	public Thread getBagRunnable() {
		return this.thread2;
	}

	/**
	 * This is the constructor for the game
	 * @throws IOException
	 */
	public DigGame() throws IOException {
		this.hero = new Hero(700, 360);
		this.weapon = new Weapon(700, 700, 0, 0);
		this.monsterList.add(new Nobbin(780, 0));
		this.monsterList1.add(new Hobbin(665, 0));
		this.lives = 3;
		this.emeraldcount = 6;
		this.thread.start();
		this.thread2.start();
		// this.thread3.start();
		Initialize();

	}
	
	/**
	 * This function is called to in the thread to check whether the level is complete
	 */
	public void nextLevel() {
		if (this.Score == 1500) {
			String s = "YOU WIN!";
			String html1 = "<html><body style='width:";
			String html2 = "px'>";
			JOptionPane.showMessageDialog(null, new JLabel(html1 + "200" + html2
					+ s));
			System.exit(0);
		}
		if (this.emeraldcount == 0) {
			System.out.println("level up");
			this.hero.isDead = false;
			for (Nobbin m : this.getMonsters()) {
//				System.out.println("null setter");
				m.isDead = true;
				m = null;
			}
			for (Hobbin h : this.getMonster1()) {
//				System.out.println("null setter");
				h.isDead = true;
				h = null;
			}
			this.setLevel(this.getLevel() + 1);
			try {
				this.Initialize();
				this.emeraldcount = this.emeraldList.size();
			} catch (IOException exception) {

				exception.printStackTrace();
			}
		}
	}
	
	

	@SuppressWarnings("deprecation")
	public void Initialize() throws IOException {

		this.thread.suspend();
		this.thread2.suspend();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				this.blockList[i][j] = 0;
			}

		}
		this.monsterList.clear();
		this.monsterList1.clear();
		this.emeraldList.clear();
		this.goldList.clear();
		this.bags.clear();
		this.bags.add(new GoldBag(160, 40, this));
		this.bags.add(new GoldBag(320, 40, this));
		this.bags.add(new GoldBag(480, 40, this));
		this.bags.add(new GoldBag(640, 40, this));
		this.hero.setPosition(360, 700);
		this.weapon.setPosition(900, 900);
		this.monsterList.add(new Nobbin(720, 0));
		this.monsterList1.add(new Hobbin(640, 0));
		String levelString = "src\\Level" + this.level;
		levelConstructor(levelString);
		this.thread.resume();
		this.thread2.resume();

	}
	
	/**
	 * This method is used to update information on the hero.
	 * @throws IOException
	 */
	public void HeroTimePassed() throws IOException {
		if (this.hero != null) {
			if (this.hero.isDead()) {
				this.hero.setPosition(360, 700);
				this.hero.isDead = false;
			}
			this.hero.checkEdge();
			this.hero.updatePosition();
			this.hero.freeze();

		}

	}
	
	/**
	 * This method is used to update information on the Nobbin
	 */
	public void MonsterTimePassed() {
		for (int i = 0; i < this.monsterList.size(); i++) {
			if (this.monsterList.get(i).isDead()) {
				this.monsterList.remove(i);
				// Nobbin newNobbin = new Nobbin(780, 0);
				// this.monsterList.add(newNobbin);
				i--;
			}
		}

		for (Nobbin h : this.monsterList) {

			h.move(this.blockList);
			h.updatePosition();
		}

		if (Math.abs((int) System.currentTimeMillis() - this.startTime) >= 10000
				&& this.monsterList.size() < 4) {
			this.startTime = (int) System.currentTimeMillis();
			this.monsterList.add(new Nobbin(720, 0));
		}
	}

	/**
	 * This method is used to update information on the Hobbin
	 */
	public void Monster1TimePassed() {
		for (int i = 0; i < this.monsterList1.size(); i++) {
			if (this.monsterList1.get(i).isDead()) {
				this.monsterList1.remove(i);
				Hobbin newHobbin = new Hobbin(665, 0);
				this.monsterList1.add(newHobbin);
			}
		}
		for (Hobbin i : this.monsterList1) {
			i.checkDirect(this.hero.getPosition());
			i.updatePosition();
		}

	}

	/**
	 * This method is used to update information on the Gold bags
	 */
	public void BagTimePassed() throws InterruptedException {
		for (GoldBag b : this.bags) {
			b.TimePassed();
		}
		for (int i = 0; i < this.bags.size(); i++) {
			if (this.bags.get(i).isDead) {
				this.emeraldList.add(new Emerald(this.bags.get(i).getX(),
						this.bags.get(i).getY()));
				this.bags.remove(i);
				i--;
			}
		}
	}

	/**
	 * This method is used to update information on the Gold from the bags
	 */
	public void GoldTimePassed() {
		for (Gold g : this.goldList) {
			if (g.getPicked(this.hero)) {
				g.setLocation(1000, 1000);
				this.Score += 150;
				System.out.println(this.Score);
			}
		}
	}

	/**
	 * This method is a getter for the score
	 * @return
	 */
	public int getScore() {
		return this.Score;
	}

	/**
	 * This method is used to update information on the weapon that is being used
	 */
	public void WeaponTimePassed() {
		this.weapon.checkEdge();
		this.weapon.updatePosition();

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (this.weapon.x * 40 == i && this.weapon.y * 40 == j
						&& this.blockList[i][j] == 0) {
					System.out.println("hihi");
					this.weapon.isDead = true;
					this.weapon.setPosition(900, 900);
					this.weapon.setXVelocity(0);
					this.weapon.setYVelocity(0);
				}
			}
		}

		for (Emerald e : this.emeraldList) {
			if (e.getPicked(this.weapon)) {
				this.weapon.isDead = true;
				this.weapon.setPosition(1500, 1500);
				this.weapon.setXVelocity(0);
				this.weapon.setYVelocity(0);
			}
		}

	}

	public Hero getHero() {
		return this.hero;
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	/**
	 * This method is called in the thread to control kill characters in the game where necessary.
	 */
	public void kill() {
		for (int k = 0; k < this.getMonster1().size(); k++) {
			if (Math.abs(this.getWeapon().getX()
					- this.getMonster1().get(k).getX()) < 50
					&& Math.abs(this.getWeapon().getY()
							- this.getMonster1().get(k).getY()) < 50) {
				this.getMonster1().get(k).isDead = true;
				// this.getMonster1().remove(k);

			}
		}
		for (int j = 0; j < this.getMonsters().size(); j++) {
			if (Math.abs(this.getWeapon().getX()
					- this.getMonsters().get(j).getX()) < 50
					&& Math.abs(this.getWeapon().getY()
							- this.getMonsters().get(j).getY()) < 50) {
				this.getMonsters().get(j).isDead = true;
				// this.getMonsters().set(j, null);
				this.getMonsters().remove(j);
				this.getMonsters().add(new Nobbin(740, 0));
				// this.getMonsters().get(j).setPosition(2100, 2000);
				// this.getMonsters().get(j).updatePosition();
			}
		}
		for (int h = 0; h < this.getMonster1().size(); h++) {
			if (Math.abs(this.hero.getX() - this.getMonster1().get(h).getX()) < 40
					&& Math.abs(this.hero.getY()
							- this.getMonster1().get(h).getY()) < 40) {
				this.hero.isDead = true;
				this.lives--;
				// this.hero = null;
				// this.hero.setPosition(800, 200);
				// this.hero.updatePosition();
			}
		}
		for (int i = 0; i < this.getMonsters().size(); i++) {
			if (Math.abs(this.hero.getX() - this.getMonsters().get(i).getX()) < 50
					&& Math.abs(this.hero.getY()
							- this.getMonsters().get(i).getY()) < 50) {
				this.hero.isDead = true;
				this.lives--;
				// this.hero = null;
				// this.hero.setPosition(360, 700);
				// this.hero.updatePosition();
			}
		}
	}

	/**
	 * LevelConstructor is called to read a text file and build a new level.
	 * @param fileName
	 * @throws IOException
	 */
	public void levelConstructor(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		for (int i = 0; i < 20; i++) {
			String s = reader.readLine();
			for (int j = 0; j < 20; j++) {
				if (s.charAt(j) == '1') {
					this.blockList[j][i] = 1;

				}

				if (s.charAt(j) == '3') {
					this.blockList[j][i] = 1;
					this.emeraldList.add(new Emerald(j * 40, i * 40));
				}

				if (s.charAt(j) == '2') {
					this.blockList[j][i] = 1;

				}

				if (s.charAt(j) == '4') {
					this.blockList[j][i] = 1;
					// this.bags.add(new GoldBag(j*40, i*40, this));
				}
			}
		}
		reader.close();
	}

	public int[][] getBlock() {
		return this.blockList;
	}

	public ArrayList<Emerald> getEmerald() {
		return this.emeraldList;
	}

	public ArrayList<Nobbin> getMonsters() {
		return this.monsterList;
	}

	public ArrayList<Hobbin> getMonster1() {
		return this.monsterList1;
	}

	// public ArrayList<GoldBag> getBags() {
	// return this.bagList;
	// }

	public ArrayList<Gold> getGold() {
		return this.goldList;
	}

	/**
	 * This method is used to update information on the Emeralds
	 */
	public void EmearaldTimepassed() {
		for (Emerald e : this.emeraldList) {
			if (e.getPicked(this.hero)) {
				this.emeraldcount--;
				e.setLocation(1000, 1000);
				this.Score += 100;
				System.out.println(this.Score);
			}
		}

	}
	
	public void setScore(int score) {
		this.Score = score;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(int i) {
		int a = i;
		if (a < 1) {
			a = 1;
		}
		if (a > 3) {
			a = 3;
		}
		this.level = a;
	}

	public int getLevel() {
		return this.level;
	}

	public int getLives() {
		return this.lives;
	}

	public ArrayList<GoldBag> getBags() {
		return this.bags;
	}

	public void MapTimePassed() {
		int x = this.hero.getX();
		int y = this.hero.getY();
		if (x % 40 >= 20) {
			x += 20;
		}
		if (y % 40 >= 20) {
			y += 20;
		}
		for (int i = 0; i < this.monsterList1.size(); i++) {
			int z = this.monsterList1.get(i).getX();
			int h = this.monsterList1.get(i).getY();
			if (z % 40 >= 20) {
				z += 20;
			}

			if (h % 40 >= 20) {
				h += 20;

			}
			this.blockList[z / 40][h / 40] = 0;

		}

		this.blockList[x / 40][y / 40] = 0;
	}


}
