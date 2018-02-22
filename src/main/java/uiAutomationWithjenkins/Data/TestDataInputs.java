package uiAutomationWithjenkins.Data;

import uiAutomationWithjenkins.TestBase.TestBase;

public class TestDataInputs extends TestBase {
	static TestBase obj = new TestBase();
	static String UserName;
	static String passWord;
	static String memorableWord;
	static String firstName;
	static String lastName;
	static String email;
	static String ConfirmEmail;
	static String contactnumber;
	static String Confirmcontactnumber;
	static String createUsername;

	public static String getUserName() {
		// return UserName=obj.getDatafromSheet("TestDataSheet1.xlsx", "Login", "UserName",1);
		String userName = System.getProperty("userName");
		System.out.println(userName);
		return userName;
	
	}
	public static String getPassWord() {
		//return passWord = obj.getDatafromSheet("TestDataSheet1.xlsx", "Login","Password", 1);
		String password = System.getProperty("Password");
		System.out.println(password);
		return password;
	}

	public static String getMemorableWord() {
		return memorableWord = obj.getDatafromSheet("TestDataSheet1.xlsx",
				"DPA Data", "MemorableWord", 1);
	}

	public static String getFirstName() {
		return firstName = obj.getDatafromSheet("TestDataSheet1.xlsx",
				"Add New Contact Data", "firstname", 1);
	}

	public static String getLastName() {
		return lastName = obj.getDatafromSheet("TestDataSheet1.xlsx",
				"Add New Contact Data", "lastname", 1);
	}

	public static String getEmail() {
		return email = obj.getDatafromSheet("TestDataSheet1.xlsx",
				"Add New Contact Data", "email", 1);
	}

	public static String getConfirmEmail() {
		return ConfirmEmail = obj.getDatafromSheet("TestDataSheet1.xlsx",
				"Add New Contact Data", "ConfirmEmail", 1);
	}

	public static String getContactnumber() {

		return contactnumber = obj.getDatafromSheet("TestDataSheet1.xlsx",
				"Add New Contact Data", "contactnumber", 1);

	}

	public static String getConfirmcontactnumber() {
		return Confirmcontactnumber = obj.getDatafromSheet(
				"TestDataSheet1.xlsx", "Add New Contact Data",
				"Confirmcontactnumber", 1);
	}

	public static String getCreateUsername() {
		return createUsername = obj.getDatafromSheet("TestDataSheet1.xlsx",
				"Add New Contact Data", "createUsername", 1);
	}

}
