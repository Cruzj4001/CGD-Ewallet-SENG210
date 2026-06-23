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

	@Override
	public void PrintIncomereport()
	{
		if (incomes.isEmpty())
		{
			System.out.println("No income data exists.");
			return;
		}

		double totalIncome = 0;
		
		System.out.println("===== INCOME REPORT =====");

		for (Wage wage : incomes)
		{
			System.out.println(
                "Source: " + wage.source +
                " | Amount: $" + wage.amount +
                " | Month: " + wage.Month);

			totalIncome += wage.amount;
		}

    	System.out.println("-------------------------");
    	System.out.println("Total Income: $" + totalIncome);
	}
  
	@Override
	public void PrintExpensereport()
	{
		if (expenses.isEmpty())
		{
			System.out.println("No expense data exists.");
			return;
		}

		double totalExpenses = 0;

		System.out.println("===== EXPENSE REPORT =====");

		for (Expense expense : expenses)
		{
			System.out.println(
					"Source: " + expense.source +
					" | Amount: $" + expense.amount +
					" | Frequency: " + expense.yearlyfrequency);

			totalExpenses += expense.amount;
		}

		System.out.println("--------------------------");
		System.out.println("Total Expenses: $" + totalExpenses);
		
	}
	
}
