import java.util.ArrayList;
import java.util.Scanner;

public class EWalletApp implements Expenser
{

	private ArrayList<Wage> incomes = new ArrayList<>();
	private ArrayList<Expense> expenses = new ArrayList<>();

	private double monthlySavings;

	public void CreateUser(String username, String password) 
	{
		
	}

	public static void main(String[] args)
	{
		EWalletApp app = new EWalletApp();
		Scanner input = new Scanner(System.in);

		System.out.println("Welcome to your Ewallet!");

		while (true)
		{
			System.out.println("\n---- MENU ----");
			System.out.println("1. Add Income");
			System.out.println("2. Add Expense");
			System.out.println("3. View Reports");
			System.out.println("4. When Can I Buy This?");
			System.out.println("5. Exit");
			System.out.print("Choose an option: ");

			int choice = input.nextInt();
			input.nextLine();

			if (choice == 1)
			{
				System.out.print("\nEnter the source of income: ");
				String incomeSource = input.nextLine();

				System.out.print("Enter income amount: ");
				double incomeAmount = input.nextDouble();

				System.out.print("Enter income frequency for the year: ");
				int incomeFreq = input.nextInt();
				input.nextLine();

				Wage w = new Wage(incomeSource, incomeAmount, incomeFreq);
				app.addMonthlyIncome(w);
			}
			else if (choice == 2)
			{
				System.out.print("\nEnter expense source: ");
				String expenseSource = input.nextLine();

				System.out.print("Enter expense amount: ");
				double expenseAmount = input.nextDouble();

				System.out.print("Enter expense frequency for the year: ");
				int expenseFreq = input.nextInt();
				input.nextLine();

				Expense ex = new Expense(expenseSource, expenseAmount, expenseFreq);
				app.addExpense(ex);
			}
			else if (choice == 3)
			{
				app.PrintIncomereport();
				app.PrintExpensereport();
			}
			else if (choice == 4)
			{
				app.updateMonthlySavings();

				System.out.print("\nEnter item name: ");
				String itemName = input.nextLine();

				System.out.print("Enter item price: ");
				double price = input.nextDouble();
				input.nextLine();

				System.out.println(" ");

				app.whenCanIBuy(itemName, price);
			}
			else if (choice == 5)
			{
				break;
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

		System.out.println();
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

		System.out.println();
		System.out.println("Total Expenses: $" + totalExpenses);
	}

	@Override
	public void PrintFullreport() {
		throw new UnsupportedOperationException("Unimplemented method 'PrintFullreport'");
	}

	@Override
	public void PrintIncomereportbyTpe() {
		throw new UnsupportedOperationException("Unimplemented method 'PrintIncomereportbyTpe'");
	}

	@Override
	public void PrintExpensebyType() {
		throw new UnsupportedOperationException("Unimplemented method 'PrintExpensebyType'");
	}

	@Override
	public void exportReport(String reportTitle) {
		throw new UnsupportedOperationException("Unimplemented method 'exportReport'");
	}

	@Override
	public Currency convertForeignCurrency(Currency C, double amount) {
		throw new UnsupportedOperationException("Unimplemented method 'convertForeignCurrency'");
	}

	@Override
	public boolean loadExpenseFile(String filePath) {
		throw new UnsupportedOperationException("Unimplemented method 'loadExpenseFile'");
	}

	@Override
	public boolean loadIncomeFile(String filePath) {
		throw new UnsupportedOperationException("Unimplemented method 'loadIncomeFile'");
	}

	@Override
	public int whenCanIBuy(String itemname, double price)
	{
		if (monthlySavings <= 0)
		{
			System.out.println("\nYou are not currently saving money each month.");
			return -1;
		}

		int months = (int) Math.ceil(price / monthlySavings);

		System.out.println("Item: " + itemname);
		System.out.println("Estimated months to buy: " + months);

		return months;
	}

	@Override
	public void updateMonthlySavings()
	{
		double totalMonthlyIncome = 0;
		double totalMonthlyExpenses = 0;

		for (Wage w : incomes)
		{
			double yearlyIncome = w.amount * w.Month;
			double monthlyIncome = yearlyIncome / 12.0;

			totalMonthlyIncome += monthlyIncome;
		}

		for (Expense e : expenses)
		{
			double yearlyExpense = e.amount * e.yearlyfrequency;
			double monthlyExpense = yearlyExpense / 12.0;

			totalMonthlyExpenses += monthlyExpense;
		}

		monthlySavings = totalMonthlyIncome - totalMonthlyExpenses;

		System.out.println("\nMonthly savings updated: $" + monthlySavings);
	}

}