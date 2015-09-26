import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class is used to create the main menu screen with all its buttons and the functionality for these buttons are
 * applied through ActionListeners.
 * @author peruman, gerbernd
 *
 */
public class BackGroundFrame extends JFrame {
	public int width;
	public int height;
	public JPanel main_menu;
	
	/**
	 * Constructor for the BackGroundFrame
	 * @throws HeadlessException
	 * @throws IOException
	 */
	public BackGroundFrame() throws HeadlessException, IOException {
		setTitle("DIGGER!");
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(new BorderLayout());
		final JLabel background=new JLabel(new ImageIcon("src/black2.jpg"));
		add(background);
		background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
		

		final JPanel main_menu = new JPanel();
		main_menu.setLayout(new BoxLayout(main_menu, BoxLayout.Y_AXIS));
		background.add(Box.createRigidArea(new Dimension(0, 300)));

		JLabel title = new JLabel("Digger");
		title.setFont(new Font("Calibri", Font.BOLD, 50));
		title.setForeground(Color.BLUE);
		background.add(title);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(Box.createRigidArea(new Dimension(0, 20)));

		JButton newGame = new JButton("New Game");
		newGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(newGame);
		background.add(Box.createRigidArea(new Dimension(0, 20)));
		
		JButton load = new JButton("Load Game");
		load.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(load);
		background.add(Box.createRigidArea(new Dimension(0,20)));

		JButton exit = new JButton("Exit");
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(exit);
		background.add(Box.createRigidArea(new Dimension(0, 20)));

		JButton help = new JButton("Help");
		help.setAlignmentX(Component.CENTER_ALIGNMENT);
		background.add(help);
		
		/**
		 * Creates a button listener to implement functionality for the buttons on the main menu
		 * @author localmgr
		 *
		 */
		class ButtonListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				
				JButton button_selected = (JButton) e.getSource();

				if (((JButton) e.getSource()).getText() == "Exit")
					System.exit(0);
				if (((JButton) e.getSource()).getText() == "Help") {
//					System.out.println("SUPERSHIT");
					helpfunction();
				}
				
				if (button_selected.getText() == "Load Game") {
					try {
						JPanel other = (JPanel) main_menu;
						background.remove(other);
						background.repaint();
						
						Scanner save = new Scanner(new File("src/LevelData"));
						int lvl = save.nextInt();
						int score = save.nextInt();
						int lives = save.nextInt();
						String name = save.next();
//						Scanner scanner = new Scanner(new File("src/LevelData"+lvl));
						DigGame data;
						save.close();
						try {
							data = new DigGame();
							data.levelConstructor("src/Level"+lvl);
							data.setScore(score);
							data.setLevel(lvl);
							data.setLives(lives);
							
							BackGround backdrop = new BackGround(800, 800, data, name);
							data.frame = backdrop.backdrop;
							backdrop.backdrop.setVisible(true);
							BackGroundFrame.this.setVisible(false);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					};
				}
				
				if (((JButton) e.getSource()).getText() == "New Game") {
					String name = JOptionPane
							.showInputDialog("What is your name?");
					if(!(name == null)){
						if(name.isEmpty()){
							name = "Anonymous";
						}
					//JPanel other = (JPanel) button_selected.getParent();
					JPanel other = (JPanel) main_menu;
					background.remove(other);
					background.repaint();
					DigGame game;
					
					try {
						game = new DigGame();
						BackGround backdrop = new BackGround(800, 800, game, name);
						game.frame = backdrop.backdrop;
						backdrop.backdrop.setVisible(true);
						BackGroundFrame.this.setVisible(false);
					} catch (IOException e1) {
						// Done Auto-generated catch block
						e1.printStackTrace();
					}
					background.validate();

				}
				}
				

			}

		}

		ActionListener menu_listener = new ButtonListener();
		newGame.addActionListener(menu_listener);
		exit.addActionListener(menu_listener);
		help.addActionListener(menu_listener);
		load.addActionListener(menu_listener);

		//main_menu.setBackground(Color.BLACK);

	
		background.add(BorderLayout.CENTER, main_menu);
		main_menu.setOpaque(true);
		//background.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		background.setVisible(true);
		setSize(700,700);
		setSize(800,800);
		
		
		
	}
	
	/**
	 * This function is called in the help button code and brings up a window that shows you how to play
	 */
	public void helpfunction() {
		String s = "How to play Digger:\n"
				+ " Use the arrow keys to navigate with your character.\n"
				+ " The weapon can be shot by hitting space.\n"
				+ " Collect emeralds and gold to gather points.\n";

		String html1 = "<html><body style='width:";
		String html2 = "px'>";
		JOptionPane.showMessageDialog(null, new JLabel(html1 + "400" + html2
				+ s));
	}

}
