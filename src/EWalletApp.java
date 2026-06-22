import java.util.ArrayList;

public class EWalletApp implements Expenser
{

	//this is the app class, has the GUI and create one object of your expense calculator class. The expense calculator class is the implementation of the Expenser interface 
	
	public static void main(String[] args)
	{
		EWalletApp app = new EWalletApp();
	}
	
	private ArrayList<User> AllData;

	private ArrayList<Wage> incomes = new ArrayList<>();
    private ArrayList<Expense> expenses = new ArrayList<>();

	public void CreateUser(String username, String password) 
	{
		
	}

	@Override
	public void addMonthlyIncome(Wage W) 
	{
   		incomes.add(W);
	}

	@Override
	public void addExpense(Expense Ex) 
	{
   		expenses.add(Ex);
	}



	
}
