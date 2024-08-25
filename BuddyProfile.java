
/*
 * this frame displays buddy data that workers at the leisure centre will determine
 * so that people can reference something which is like simulation to their own data
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BuddyProfile extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//opens the buddy profile
					BuddyProfile frame = new BuddyProfile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	//global variables used as they are used in multiple buttons
	private JTable table;
	private JTable table_1;
	/**
	 * Create the frame.
	 */
	public BuddyProfile() {
		//establishes connection to database
		connection =sqliteConnection.dbConnector();
		
		/*
		 * I commented this off so that all windows dont close when the
		 * user closes the buddy profile
		 */
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1038, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground( Color.decode("#66d9ff"));
		//surrounds table with scroll pane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 121, 519, 472);
		contentPane.add(scrollPane);
		//creates table
		table = new JTable();
		scrollPane.setViewportView(table);
		//load buddy exercise information
		JButton btnNewButton = new JButton("Load buddy exercise tracker");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//selects all the data from the table
					String query ="select * from BuddyExerciseInfo";
				    PreparedStatement pst = connection.prepareStatement(query);
					//stores the results in a local variable rs
					ResultSet rs = pst.executeQuery();
					//using rs displays the results in a table
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(10, 601, 196, 54);
		contentPane.add(btnNewButton);
		//second table created and surrounded by scroll pane
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(519, 121, 503, 472);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		//load buddy weight info table
		JButton btnNewButton_1 = new JButton("Load buddy calorie tracker");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//selects all the data from the table
					String query ="select * from BuddyWeightInfo";
					PreparedStatement pst = connection.prepareStatement(query);
					//stores the results in a local variable rs
					ResultSet rs = pst.executeQuery();
					//using rs displays the results in a table
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e2) {//change variable to something meaningful
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(519, 604, 164, 54);
		contentPane.add(btnNewButton_1);
		//puts logo in a blank text jlabel 
		JLabel label = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("FitBud.png")).getImage();
		label.setIcon(new ImageIcon(logo));
		label.setBounds(643, 11, 392, 121);
		contentPane.add(label);
		
		JLabel lblBuddyProfile = new JLabel("Buddy Profile");
		lblBuddyProfile.setFont(new Font("Garamond", Font.PLAIN, 50));
		lblBuddyProfile.setBounds(10, 11, 337, 77);
		contentPane.add(lblBuddyProfile);
		//button that loads the main menu
		JButton btnBackToMain = new JButton("Back to main menu");
		btnBackToMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//opens the main menu 
				MainMenu openMainMenu = new MainMenu();
				openMainMenu.NewScreen();
			}
		});
		btnBackToMain.setBounds(853, 632, 159, 23);
		contentPane.add(btnBackToMain);
	}

}
