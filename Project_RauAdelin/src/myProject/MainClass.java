package myProject;

public class MainClass {

	public static HomeFrame homeFrame = new HomeFrame();
	public static EntertainFrame entertainFrame = new EntertainFrame();
	public static ContactsFrame contactsFrame = new ContactsFrame();
	public static LoginBankForm loginBankForm = new LoginBankForm();
	public static BankForm bankForm = new BankForm();
	public static ToDoFrame toDoForm = new ToDoFrame();
	
	public static void main(String[] args)
	{
		homeFrame.setVisible(true);
		entertainFrame.setVisible(false);
		contactsFrame.setVisible(false);
		loginBankForm.setVisible(false);
		bankForm.setVisible(false);
		toDoForm.setVisible(false);
	}
	
}
