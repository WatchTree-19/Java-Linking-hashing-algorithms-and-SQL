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
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;

public class RegisterPage extends JFrame {

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
					RegisterPage frame = new RegisterPage();
					frame.setVisible(true);
					//opens the page
				} catch (Exception e) {
					e.printStackTrace();
					//catches other errors
				}
			}
		});
	}
	Connection connection = null;
	/**
	 * Create the frame.
	 */
	public RegisterPage() {
		connection = sqliteConnection.dbConnector();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground( Color.decode("#66d9ff"));//change background colour
		
		//creation of textFields, labels and button all the variables are appropriately named
		textFieldName = new JTextField();
		textFieldName.setBounds(262, 101, 112, 40);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldSurname = new JTextField();
		textFieldSurname.setColumns(10);
		textFieldSurname.setBounds(262, 157, 112, 40);
		contentPane.add(textFieldSurname);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setColumns(10);
		textFieldUsername.setBounds(262, 208, 112, 40);
		contentPane.add(textFieldUsername);
		
		textFieldPassword = new JTextField();
		textFieldPassword.setColumns(10);
		textFieldPassword.setBounds(262, 259, 112, 40);
		contentPane.add(textFieldPassword);
		
		textFieldAge = new JTextField();
		textFieldAge.setColumns(10);
		textFieldAge.setBounds(262, 310, 112, 40);
		contentPane.add(textFieldAge);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(122, 114, 59, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblSurname = new JLabel("Username");
		lblSurname.setBounds(122, 215, 59, 27);
		contentPane.add(lblSurname);
		
		JLabel lblUsername = new JLabel("Surname");
		lblUsername.setBounds(122, 164, 76, 27);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(122, 272, 76, 27);
		contentPane.add(lblPassword);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(122, 323, 59, 27);
		contentPane.add(lblAge);
		//jlabel instructing the user
		JLabel lblEnterYourDetails = new JLabel("Enter your details to be registered");
		lblEnterYourDetails.setBounds(158, 63, 236, 40);
		contentPane.add(lblEnterYourDetails);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblRegister.setBounds(158, 22, 189, 41);
		contentPane.add(lblRegister);
		//insert a new users details into MemberInfo
		//register button
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					int Age = Integer.parseInt(textFieldAge.getText());
			        //making sure the Age is a likely number
					if (Age > 0 && Age < 150){
			        	//making sure blank input isnt saved in the MemberInfo table
			        if ( textFieldName.getText()!= "" && textFieldSurname.getText()!= "" && textFieldUsername.getText()!= "" && textFieldPassword.getText()!= "" && textFieldAge.getText()!= "") {
					//SQL query THESE ARE 2 GROUP A Technical skills! 
			        //1)Interlinked tables, in SQLITE manager I made MID(the primary key for MemberInfo) the foreign key in other tables 
			        //2)User generated DDL script, the user input via the textFields is inserted by DDL method insert into MemberInfo
			        String query ="insert into MemberInfo ('Name','Surname','Username','password','Age') values (?,?,?,?,?)";
					PreparedStatement register = connection.prepareStatement(query);
					//Getting user input
					register.setString(1,textFieldName.getText());
					register.setString(2,textFieldSurname.getText());
					register.setString(3,textFieldUsername.getText());
					register.setString(4,textFieldPassword.getText());
					register.setString(5,textFieldAge.getText());
					
					register.execute();
					//tells the user they made it
					JOptionPane.showMessageDialog(null, "Congratulations on registering your account!");
			        }}
			        else {
			        	
			        	JOptionPane.showMessageDialog(null, "Age is not valid");
			        }
			        } catch (Exception e1) {
						e1.printStackTrace();
						//catches other errors
					}
				
				
			}
		});
		btnRegister.setBounds(191, 368, 135, 34);
		contentPane.add(btnRegister);
	}
}
