import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
public class MainMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//opens the frame
					MainMenu window = new MainMenu();
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
	public MainMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 570, 444);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground( Color.decode("#66d9ff"));//changes background colour
		//load exercise tracker button
		JButton btnExerciseTracker = new JButton("Exercise Tracker");
		btnExerciseTracker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//so the frame closes when clicked
				frame.dispose();
				
				ExerciseTracker exTrack = new ExerciseTracker();
				exTrack.setVisible(true);
				//opens the exercise tracker
				exTrack.setLocationRelativeTo(null);
				//makes it so the exercise tracker opens and is in the centre of the screen
				
			}
		});
		btnExerciseTracker.setBounds(90, 159, 164, 48);
		frame.getContentPane().add(btnExerciseTracker);
		//loads calorie tracker button
		JButton btnCalorieTracker = new JButton("Calorie Tracker");
		btnCalorieTracker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when opened the main menu closes
				frame.dispose();
				//opens the calorie tracker
				CalorieTracker calTrack = new CalorieTracker();
				calTrack.setVisible(true);
				////makes it so the exercise tracker opens and is in the centre of the screen
				calTrack.setLocationRelativeTo(null);
			}
		});
		btnCalorieTracker.setBounds(291, 159, 154, 48);
		frame.getContentPane().add(btnCalorieTracker);
		//button to open user profile
		JButton btnUserProfile = new JButton("User Profile");
		btnUserProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when opened the main menu closes
				frame.dispose();
				UserProfile userProfile = new UserProfile();
				userProfile.setVisible(true);
				//makes it so the exercise tracker opens and is in the centre of the screen
				userProfile.setLocationRelativeTo(null);
			}
		});
		btnUserProfile.setBounds(90, 218, 164, 48);
		frame.getContentPane().add(btnUserProfile);
		//button to open buddy profile
		JButton btnBuddyProfile = new JButton("Buddy Profile");
		btnBuddyProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when opened the main menu closes
				frame.dispose();
				//opens the buddy profile
				BuddyProfile budProfile = new BuddyProfile();
				budProfile.setVisible(true);
				//makes it so the exercise tracker opens and is in the centre of the screen
				budProfile.setLocationRelativeTo(null);
			}
		});
		btnBuddyProfile.setBounds(291, 218, 154, 48);
		frame.getContentPane().add(btnBuddyProfile);
		//button to open improve your lifestyle or suggestions page
		JButton btnSuggestions = new JButton("Improve your lifestyle!");
		btnSuggestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//when opened the main menu closes
				frame.dispose();
				//opens the suggestions page
				Suggestions suggestions = new Suggestions();
				suggestions.setVisible(true);
				//makes it so the exercise tracker opens and is in the centre of the screen
				suggestions.setLocationRelativeTo(null);
			}
		});
		btnSuggestions.setBounds(194, 277, 164, 48);
		frame.getContentPane().add(btnSuggestions);
		
		//JButton btnNewButton = new JButton("About Us ");
		//btnNewButton.addActionListener(new ActionListener() {
			//public void actionPerformed(ActionEvent e) {
				//when opened the main menu closes
				frame.dispose();
				//opens the about us page
				//AboutUs aboutUs = new AboutUs();
				//aboutUs.setVisible(true);
				//makes it so the exercise tracker opens and is in the centre of the screen
				//aboutUs.setLocationRelativeTo(null);
			//}
		//});
		//btnNewButton.setBounds(291, 277, 154, 48);
		//frame.getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("FitBud.png")).getImage();
		label.setIcon(new ImageIcon(logo));
		label.setBounds(45, 22, 457, 115);
		frame.getContentPane().add(label);
		
		JButton btnChangePassword = new JButton("Change password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//opens the change password page
				changePassword changePasswordPage = new changePassword();
				changePasswordPage.setVisible(true);
				//makes it so the exercise tracker opens and is in the centre of the screen
				changePasswordPage.setLocationRelativeTo(null);
			}
		});
		btnChangePassword.setBounds(10, 371, 150, 23);
		frame.getContentPane().add(btnChangePassword);
		
		JButton btnForgotMID = new JButton("Forgotten your MID?");
		btnForgotMID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {mIDReminder frame = new mIDReminder();
			//opens the forgotten your MID frame
			frame.setVisible(true);
			}
		});
		btnForgotMID.setBounds(170, 371, 164, 23);
		frame.getContentPane().add(btnForgotMID);
		
		
		
		
	}
	}

