import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class LoadScreen {

	public static JFrame loadframe;
	private ImageIcon icon;
	private final JLabel image = new JLabel("");
	private static JProgressBar progressBar;
	private static URL introMusic = LoadScreen.class.getResource("intro.wav");;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoadScreen window = new LoadScreen();
			window.loadframe.setVisible(true);
			load();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	/**
	 * Create the application.
	 */
	public LoadScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		loadframe = new JFrame();
		loadframe.setBounds(100, 100, 450, 300);
		loadframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadframe.setAlwaysOnTop(true);
		loadframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadframe.setBounds(100, 100, 671, 400);
		loadframe.setLocationRelativeTo(null);  
		loadframe.setUndecorated(true);
		JPanel panel = new JPanel();
		loadframe.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		image.setBounds(-18, -75, 721, 456);
		panel.add(image);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(64, 224, 208));
		progressBar.setBounds(0, 380, 683, 20);
		panel.add(progressBar);
		icon = new ImageIcon(this.getClass().getClassLoader().getResource("loadScreen.png"));
		image.setIcon(icon);
	}
	
	public static void load() {
		int x;
		try {
			playIntroMusic(introMusic);
			for(x = 0; x <=100; x++) {
				LoadScreen.progressBar.setValue(x);
				Thread.sleep(50);
				if(x == 100) {
					loadframe.dispose();
					LoginPage login = new LoginPage();
					login.frame.setVisible(true);
				}
			}
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void playIntroMusic(URL music) {
		try {
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(music);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
