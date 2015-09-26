import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BackGround {
	public int height;
	public int width;
	public DigGame game;
	public JPanel menu;
	final JFrame backdrop = new JFrame();
	public String name;
	final int TOTALXBLOCKS = 20;
	final int TOTALYBLOCKS = 20;

	/**
	 * This clasas is mainly used to construct a new DComponent and create it with some other information.
	 * @param height
	 * @param width
	 * @param game
	 * @param name
	 */
	public BackGround(int height, int width, DigGame game, String name) {
		
		this.game = game;
		this.height = height;
		this.width = width;
		this.backdrop.setSize(height, width);
		this.backdrop.setBackground(Color.BLACK);
		this.name = name;
		this.backdrop.add(new DComponent(game, this.name));
		this.backdrop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
