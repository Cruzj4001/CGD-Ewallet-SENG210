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

		boolean running = true;

		System.out.println("Welcome to your Ewallet!");

		while (running)
		{
			System.out.println("\n---- MENU ----");
			System.out.println("1. Add Income");
			System.out.println("2. Add Expense");
			System.out.println("3. View Reports");
			System.out.println("4. Exit");
			System.out.print("Choose an option: ");

			int choice = input.nextInt();
			input.nextLine(); // clear buffer

			switch (choice)
			{
				case 1:
					System.out.print("\nEnter the source of income: ");
					String incomeSource = input.nextLine();

					System.out.print("Enter income amount: ");
					double incomeAmount = input.nextDouble();

					System.out.print("Enter income frequency for the year: ");
					int incomeFreq = input.nextInt();
					input.nextLine();

					Wage w = new Wage(incomeSource, incomeAmount, incomeFreq);
					app.addMonthlyIncome(w);
					break;

				case 2:
					System.out.print("\nEnter expense source: ");
					String expenseSource = input.nextLine();

					System.out.print("Enter expense amount: ");
					double expenseAmount = input.nextDouble();

					System.out.print("Enter expense frequency for the year: ");
					int expenseFreq = input.nextInt();
					input.nextLine();

					Expense ex = new Expense(expenseSource, expenseAmount, expenseFreq);
					app.addExpense(ex);
					break;

				case 3:
					app.PrintIncomereport();
					app.PrintExpensereport();
					break;

				case 4:
					running = false;
					System.out.println("Goodbye!");
					break;

				default:
					System.out.println("Invalid option. Try again.");
			}
		}

		input.close();
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
		
		System.out.println("\n===== INCOME REPORT =====");

		for (Wage wage : incomes)
		{
			System.out.println(
                "Source: " + wage.source +
                " | Amount: $" + wage.amount +
                " | Month: " + wage.Month);

			totalIncome += wage.amount;
		}

    	System.out.println("  ");
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

		System.out.println("\n===== EXPENSE REPORT =====");

		for (Expense expense : expenses)
		{
			System.out.println(
					"Source: " + expense.source +
					" | Amount: $" + expense.amount +
					" | Frequency: " + expense.yearlyfrequency);

			totalExpenses += expense.amount;
		}

		System.out.println(" ");
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
