import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HelpScreen {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpScreen window = new HelpScreen();
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
	public HelpScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 519);
		frame.setLocationRelativeTo(null);  
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 140, 0));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
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
				frame.dispose();
			}
		});
		panel.add(btnNewButton);
		
		JButton nextButton = new JButton("Next");
		panel.add(nextButton);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JTextArea txtrH = new JTextArea();
		txtrH.setText("(၁) “စာရင်းထည့်” သည်ပစ္စည်းအသစ်ထည့်ရန်အတွက်ဖြစ်သည်။ ပစ္စည်းအသစ်ထည့်လိုပါက “Add or Update or Delete \n\n Product”  ဇယားကွက်ထဲတွင် ပစ္စည်းအချက်အလက်ပြည့်စုံစွာထည့်ပြီးပစ္စည်းထည့်နိုင်ပါသည်။  \n\n ပစ္စည်းထည့်ရာတွင် ပစ္စည်းအမျိုးအစားဇယားအား မီနူးဘားပေါ်တွင် ရွေးချယိပြီး “စာရင်းထည့်” ခလုတ်အားနိပ်ပါ။ \n\n (ဥပမာ TV ပစ္စည်းသစ်ထည့်လိုပါက ဆော့ဝဲ၏အပေါ်ဘက်ခြမ်းတွင်ရှိသော TV&Project ခလုတ်ကို အရင်နိပ်ပြီး ပစ္စည်း \n\n အချက်လက်ပြည့်စုံစွာထည့်၍ “စာရင်းထည့်” ခလုတ်အားနိပ်ပါ။\n"
				+ "\n\n\n"
				+ "(၂) “စာရင်းပြင်” သည်ရှိထားပြီးသောပစ္စည်း၏အချက်အလက်များအားပြင်ဆင်ရန်ဖြစ်သည်။ ပစ္စည်း၏အချက်လက်များအား \n\n ပြင်ဆင်ရာတွင် ID ဖြင့်ပစ္စည်းရှာပြီး ပြင်ဆင်လိုသည်များကိုပြင်ဆင်၍ “စာရင်းပြင်” ခလုတ်အားနိပ်ပါ။\n"
				+ " \n"
				+ "\n"
				+ "\n"
				+ "(၃) “စာရင်းဖျက်” သည်ရှိထားပြီးသောပစ္စည်းစာရင်းအားဖျက်ရန်ဖြစ်သည်။ ပစ္စည်းဖျက်ရာတွင်  ID ဖြင့်ပစ္စည်းရှာပြီး \n\n “စာရင်းဖျက်” ခလုတ်အားနိပ်ပါ။\n"
				+ "\n"
				+ "\n\n"
				+ "(၄) “Clear” သည် text area ရှိစာများအားဖျက်ပစ်ရန်ဖြစ်သည်။\n"
				+ "\n\n"
				+ "(၅) “Refresh” သည် တစ်ဖန်ပြန်စရန်ဖြစ်သည်။");
		panel_1.add(txtrH, BorderLayout.CENTER);
	}
}
