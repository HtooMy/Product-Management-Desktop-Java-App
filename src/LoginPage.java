import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LoginPage {

	public JFrame frame;
	private JPanel contentPane;
	private JTextField UserName;
	private JPasswordField Password;
	private String username;
	private String password;
	private String success = "FALSE";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 600, 400);
		frame.setLocationRelativeTo(null);  
		frame.setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		frame.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 165, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(172, 132, 268, 52);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		UserName = new JTextField();
		UserName.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		UserName.setBounds(76, 6, 192, 40);
		panel_1.add(UserName);
		UserName.setColumns(10);
		
		JLabel usericon = new JLabel("Username");
		usericon.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		usericon.setBounds(6, 6, 92, 40);
		panel_1.add(usericon);
		
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(172, 196, 268, 52);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		Password = new JPasswordField();
		Password.setBounds(78, 6, 184, 40);
		panel_1_1.add(Password);
		
		
		JLabel lock = new JLabel("Password");
		lock.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lock.setBounds(6, 6, 82, 40);
		panel_1_1.add(lock);
		
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBounds(170, 312, 270, 52);
		panel.add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JButton login = new JButton("Login");
		login.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("mouse.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				username = UserName.getText();
				password = Password.getText();
				if(username.equals("YeeShin") && password.equals("YeeShin527845")) {
					success = "TRUE";
					MainFrame load = new MainFrame();
				    load.frmYeeShinProduct.setVisible(true);
				    frame.dispose();
				}
				else {
					UserName.setText("");
					Password.setText("");
					JOptionPane.showMessageDialog(null, "Incorrect");
				}
			}
		});
		login.setBounds(6, 6, 258, 40);
		panel_1_2.add(login);
		
		JLabel lblNewLabel = new JLabel("Yee Shin Electronic Thipaw");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(38, 17, 531, 84);
		panel.add(lblNewLabel);
	}

}
