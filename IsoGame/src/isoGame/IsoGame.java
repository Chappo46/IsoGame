package isoGame;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import java.awt.Dimension;

public class IsoGame extends JFrame {

	private JPanel contentPane;
	private int windowX = 1280;
	private int windowY = 720;
	private Dimension screen;
	public static Console console;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IsoGame frame = new IsoGame();
//					frame.setSize(screenSize);
					frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IsoGame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(IsoGame.class.getResource("/isoGame/bgImages/defaultImages/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, windowX, windowY);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		console = new Console();
	
		screen = new Dimension(windowX,windowY);
		
		Level level1 = new Level(screen,1);
		contentPane.add(level1);
		console.printConsole();
	}

}
