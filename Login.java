import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.Window;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.color.*;
import java.awt.Image;
import java.awt.Font;

public class Login {//name of the class

         private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
					//opens the window
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
Connection connection=null;//connects to the database
private JTextField textFieldUN;//variable of the textField
private JPasswordField passwordField;

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection=sqliteConnection.dbConnector();//calls the sqliteConnection class to launch connection
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 521, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground( Color.decode("#66d9ff"));//sets the colour of the background
		//making the labels,buttons and textFields, the labels prompt the user what to input into the textField
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Garamond", Font.PLAIN, 15));
		lblUsername.setBounds(117, 192, 105, 50);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Garamond", Font.PLAIN, 15));
		lblPassword.setBounds(117, 240, 74, 29);
		frame.getContentPane().add(lblPassword);
		
		textFieldUN = new JTextField();
		textFieldUN.setBounds(232, 207, 145, 24);
		frame.getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);
		//login button
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			//the button checks whether the values entered matches a database record, or if they match two records or dont match at all
			public void actionPerformed(ActionEvent arg0) {
				try{
					String query="select * from MemberInfo where Username =? and password =?";//creates sql query which selects all fields/columns of data where the password and username are to be determined.
					PreparedStatement loginpst = connection.prepareStatement(query);//creates an object of the query and then passes it to the PreparedStatement variable
					loginpst.setString(1, textFieldUN.getText());//the user enters a value into the textField and it is stored in the index ( this is why I used ? in the query
					loginpst.setString(2, passwordField.getText());
					
					ResultSet loginrs = loginpst.executeQuery();//the query is executed and then saved.
					int count =0;//setting count to 0
					while(loginrs.next()){//until the data is retrived from the database this method will repeat
					count = count+1;//incrementing the variable so that when data is retrived it is 1 ( so we can use it later )
					
					}
					if(count ==1){//if count =1 ( when one record is retrieved that matches )
						JOptionPane.showMessageDialog(null,"Username and password is correct");//display successful login message
						frame.dispose();//close the login screen
						//open main menu if login successful
						MainMenu openMainMenu = new MainMenu();
						openMainMenu.NewScreen();
					}
					else if ( count>1)//else if count is above 1 ( the results have retrieved and there is more than one matching record )
					{
						//error message for duplicate records
						JOptionPane.showMessageDialog(null, "Duplicate Username and password");
					
					}else{//else if no records match
						JOptionPane.showMessageDialog(null, "Username and password is not correct");
					}
					loginrs.close();//closes result set
					loginpst.close();//closes connection so database doesnt lock
				}catch(Exception e)
				
				{
					JOptionPane.showMessageDialog(null, e);
					
				}
				
			}
			
			});
		btnLogin.setBounds(186, 280, 120, 37);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(232, 242, 145, 24);
		frame.getContentPane().add(passwordField);
		//Putting my logo into a jlabel 
		JLabel lblFitBud = new JLabel("");
		Image logo = new ImageIcon(this.getClass().getResource("FitBud.png")).getImage();
		lblFitBud.setIcon(new ImageIcon(logo));
		lblFitBud.setBounds(10, 54, 406, 133);
		frame.getContentPane().add(lblFitBud);
		//button to load register screen
		JButton btnRegisterHere = new JButton("Dont have an account? Register here");
		btnRegisterHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterPage nw = new RegisterPage();
				nw.setVisible(true);
			}
		});
		btnRegisterHere.setBounds(117, 328, 260, 33);
		frame.getContentPane().add(btnRegisterHere);
		//button to change password screen
		JButton btnChangePassword = new JButton("Change your password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				changePassword changePasswordPage = new changePassword();
				changePasswordPage.setVisible(true);
			}
		});
		btnChangePassword.setBounds(149, 372, 189, 29);
		frame.getContentPane().add(btnChangePassword);
		//this button checks to see if the entered values match the database, then if it matches and is identical to specified Admin details then they may enter
		JButton buttonAdministrator = new JButton("Adminidtrator login");
		buttonAdministrator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//the following local variables are used to see whether the login details match them , as the variables are the admin login
					String wojtok = "10wojtok";
					String wojtokPassword = "ACDC1";
					//the same but for the backUpAdmin
					String backUpAdmin = "10rais";
					String backUpAdminPassword ="Metallica"	;	
					//a query to see whhether the details match
					String query="select * from MemberInfo where Username =? and password =?";
					PreparedStatement loginpst = connection.prepareStatement(query);
					//gets the users username and password
					loginpst.setString(1, textFieldUN.getText());
					loginpst.setString(2, passwordField.getText());
					
					ResultSet loginrs = loginpst.executeQuery();
					//when the query is successfully executed count variable will be incremented
					int count =0;
					while(loginrs.next()){
					count = count+1;
					
					}
					//logs them in the query matches 1 record
					if(count ==1 || textFieldUN.getText() == wojtok || passwordField.getText() == wojtokPassword){
						JOptionPane.showMessageDialog(null,"Username and password is correct");
						
						administratorAccess openAA = new administratorAccess();
						openAA.setVisible(true);
						openAA.setLocationRelativeTo(null);
					}
					//if two records match the details
					else if ( count>1)
					{
						//error message 
						JOptionPane.showMessageDialog(null, "Denied Access");
					
					}else{
						JOptionPane.showMessageDialog(null, "Denied Access");
					}
					loginrs.close();
					loginpst.close();
				}catch(Exception e)
				
				{
					JOptionPane.showMessageDialog(null, e);
					
				}
			}
		});
		buttonAdministrator.setBounds(10, 443, 167, 29);
		frame.getContentPane().add(buttonAdministrator);
	}

	
		
	}
	

