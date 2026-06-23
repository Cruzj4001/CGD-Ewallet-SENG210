import java.util.ArrayList;
import java.util.Scanner;

public class EWalletApp implements Expenser
{

	//this is the app class, has the GUI and create one object of your expense calculator class. The expense calculator class is the implementation of the Expenser interface 
	
	private ArrayList<User> AllData;

	private ArrayList<Wage> incomes = new ArrayList<>();
    private ArrayList<Expense> expenses = new ArrayList<>();

	public void CreateUser(String username, String password) 
	{
		
	}

	public static void main(String[] args)
	{
		EWalletApp app = new EWalletApp();
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to your Ewallet!");

		// Income stuff

		System.out.print("\nEnter income source: ");
		String incomeSource = input.nextLine();

		System.out.print("Enter income amount: ");
		double incomeAmount = input.nextDouble();

		System.out.print("Enter income frequency: ");
		int incomeFreq = input.nextInt();
		input.nextLine();

		Wage w = new Wage(incomeSource, incomeAmount, incomeFreq);
		app.addMonthlyIncome(w);

		// Expense stuff

		System.out.print("Enter expense source: ");
		String expenseSource = input.nextLine();

		System.out.print("Enter expense amount: ");
		double expenseAmount = input.nextDouble();

		System.out.print("Enter expense frequency: ");
		int expenseFreq = input.nextInt();

		Expense ex = new Expense(expenseSource, expenseAmount, expenseFreq);
		app.addExpense(ex);

		// test print the report
		
		System.out.println("\n--- INCOME REPORT ---");
		app.PrintIncomereport();

		System.out.println("\n--- EXPENSE REPORT ---");
		app.PrintExpensereport();
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

	@Override
	public void PrintFullreport() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'PrintFullreport'");
	}

	@Override
	public void PrintIncomereportbyTpe() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'PrintIncomereportbyTpe'");
	}

	@Override
	public void PrintExpensebyType() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'PrintExpensebyType'");
	}

	@Override
	public void exportReport(String reportTitle) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'exportReport'");
	}

	@Override
	public Currency convertForeignCurrency(Currency C, double amount) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'convertForeignCurrency'");
	}

	@Override
	public boolean loadExpenseFile(String filePath) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'loadExpenseFile'");
	}

	@Override
	public boolean loadIncomeFile(String filePath) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'loadIncomeFile'");
	}

	@Override
	public int whenCanIBuy(String itemname, double price) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'whenCanIBuy'");
	}

	@Override
	public void updateMonthlySavings() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateMonthlySavings'");
	}
	
}
