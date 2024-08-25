import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class changePassword extends JFrame {

	//this is automatically done by java GUI when I drag from the pallete
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
					//opens the page
					changePassword frame = new changePassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					//catches other errors
				}
			}
		});
	}
	//connects to the database
	Connection connection = null;
	private JTextField textFieldUN;
	private JPasswordField passwordFieldPasswordChangePassword;
	private JPasswordField passwordFieldNewPassword;
	private JPasswordField passwordFieldReEnterPassword;
	/**
	 * Create the frame.
	 */
	public changePassword() {
		connection = sqliteConnection.dbConnector();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground( Color.decode("#66d9ff"));//changes background colour
		
		//creation of textFields, labels and button all the variables are appropriately named
		
		
		//change password button
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//query to select username and password 
					String query="select * from MemberInfo where Username =? and password =?";
					PreparedStatement loginpst = connection.prepareStatement(query);
					
					//where they equal user input
					loginpst.setString(1, textFieldUN.getText());
					loginpst.setString(2, passwordFieldPasswordChangePassword.getText());
					
					//assigning values to local variables from user input
					String newPassword = passwordFieldNewPassword.getText();
					String ReEnterPassword = passwordFieldReEnterPassword.getText();
					
					ResultSet loginrs = loginpst.executeQuery();
					int count =0;
					while(loginrs.next()){
					//incrementing count when query is successful (important in if statement)
						count = count+1;
					
					}
					//if query gets one record and passwords match
					if(count ==1 && newPassword == ReEnterPassword ){
						//updating the record with new password
						String queryChangePassword ="Update MemberInfo set password ='"+newPassword+"' where Username = '"+textFieldUN.getText()+"'";
						PreparedStatement pst = connection.prepareStatement(queryChangePassword);
						
						
						pst.execute();
						//to tell the user that they have successfully changed it
						JOptionPane.showMessageDialog(null, "Password successfully changed");
						
						pst.close();
						
						//this gives me the option of closing the frame
						//frame.dispose();
						
					}
					//if the query doesnt match a record
					else if ( count>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username and password");
					//tell the user that the have the same account details
					}
					//other problems with user input
					else{
						//if the details arent correct error message
						JOptionPane.showMessageDialog(null, "Password change failed!Please check your login details are correct and your passwords match");
					}
					loginrs.close();
					loginpst.close();
				}catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
					
				}
				
			}
		});
		btnChangePassword.setBounds(137, 217, 137, 34);
		contentPane.add(btnChangePassword);
		
		
		//creation of textFields, labels and button all the variables are appropriately named
		textFieldUN = new JTextField();
		textFieldUN.setBounds(231, 93, 86, 20);
		contentPane.add(textFieldUN);
		textFieldUN.setColumns(10);
		//creation of text fields and their correspodning jlabels telling the user what to input
		passwordFieldPasswordChangePassword = new JPasswordField();
		passwordFieldPasswordChangePassword.setBounds(231, 124, 86, 20);
		contentPane.add(passwordFieldPasswordChangePassword);
		
		passwordFieldNewPassword = new JPasswordField();
		passwordFieldNewPassword.setBounds(231, 155, 86, 20);
		contentPane.add(passwordFieldNewPassword);
		
		passwordFieldReEnterPassword = new JPasswordField();
		passwordFieldReEnterPassword.setBounds(231, 186, 86, 20);
		contentPane.add(passwordFieldReEnterPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(89, 96, 106, 20);
		contentPane.add(lblUsername);
		
		JLabel lblOldPassword = new JLabel("Old password");
		lblOldPassword.setBounds(89, 127, 106, 20);
		contentPane.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setBounds(89, 155, 106, 20);
		contentPane.add(lblNewPassword);
		
		JLabel lblReEnterPassword = new JLabel("Re enter password");
		lblReEnterPassword.setBounds(89, 189, 106, 20);
		contentPane.add(lblReEnterPassword);
		
		JLabel lblNewLabel = new JLabel("Change password");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(137, 40, 137, 34);
		contentPane.add(lblNewLabel);
		
		
		
		
				
	}
}
