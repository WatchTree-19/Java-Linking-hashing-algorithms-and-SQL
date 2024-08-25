import java.sql.*;//import in order to connect to the database the astrix adds all the classes related to sql
import javax.swing.*;
public class sqliteConnection {
	Connection conn=null;//one of the only global variables in my code which returns the connection
	public static Connection dbConnector(){ 
try{//exception handling so program will not run or crash when exception occurs
	
	Class.forName("org.sqlite.JDBC");//defines the class that will be connected  
	Connection conn=DriverManager.getConnection("JDBC:sqlite:C:\\Users\\SANDEEP\\Desktop\\Computer science\\Coursework\\Final\\MemberData.sqlite");// file path for the database file on the system
	//JOptionPane.showMessageDialog(null, "Connection complete");
	return conn;
}catch(Exception e)
{
	JOptionPane.showMessageDialog(null, e);
	return null;
	
}
}
}