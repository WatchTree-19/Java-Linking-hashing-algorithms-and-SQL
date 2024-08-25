//this frame is in case users forget their unique MID which is used to display their records and also save data in the tables



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class mIDReminder extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldName;
	private JTextField textFieldSurname;
	private JTextField textFieldUsername;
	private JTextField textFieldPassword;
	private JTextField textFieldAge;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//opens the screen
					mIDReminder frame = new mIDReminder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;//connection establishment
	private JTextField textFieldEnterUsername;
	
	/**
	 * Create the frame.
	 */
	public mIDReminder() {
		connection = sqliteConnection.dbConnector();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 180);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground( Color.decode("#66d9ff"));//changes background colour
		//where they will enter username
		textFieldEnterUsername = new JTextField();
		textFieldEnterUsername.setBounds(176, 33, 86, 20);
		contentPane.add(textFieldEnterUsername);
		textFieldEnterUsername.setColumns(10);
		//shows them where to enter username
		JLabel lblNewLabel = new JLabel("Enter your username");
		lblNewLabel.setBounds(38, 34, 128, 19);
		contentPane.add(lblNewLabel);
		//get MID button
		JButton btnNewButton = new JButton("Get your MID");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//selects the MID where the username corresponds
					String query = "select MID from MemberInfo where Username='"+ textFieldEnterUsername.getText()+"'";
					
					PreparedStatement pst = connection.prepareStatement(query);
					//stores the results in rs local variable
					ResultSet rs = pst.executeQuery();
					//converts result set into an integer so it will be easier to display in an output box
					int c = rs.getInt(1);
					
					
					//pop up telling them their MID value
					JOptionPane.showMessageDialog(null, "Your MID is " + c);
					
					rs.close();//closes result set
					pst.close();//closes connection so database doesnt lock
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(95, 64, 123, 23);
		contentPane.add(btnNewButton);
		//back to home button
		JButton btnBackToHome = new JButton("Back to home");
		btnBackToHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//opens the main menu
				MainMenu openMainMenu = new MainMenu();
				openMainMenu.NewScreen();
			}
		});
		btnBackToHome.setBounds(192, 107, 138, 23);
		contentPane.add(btnBackToHome);
		
		
		
		
		
		
				
	}
}
