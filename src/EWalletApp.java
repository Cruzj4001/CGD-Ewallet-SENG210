import java.util.ArrayList;
import java.util.Scanner;


public class EWalletApp implements Expenser
{

	private ArrayList<Wage> incomes = new ArrayList<>();
	private ArrayList<Expense> expenses = new ArrayList<>();

	private double monthlySavings;

	public static void main(String[] args)
	{
		EWalletApp app = new EWalletApp();
		Scanner input = new Scanner(System.in);
		User appUser  = new User("Username", "Password");
		System.out.println("Welcome to your Ewallet!");

		while (true)
		{
			System.out.println("\n---- MENU ----");
			System.out.println("1. Add Income");
			System.out.println("2. Add Expense");
			System.out.println("3. View Reports");
			System.out.println("4. When Can I Buy This?");
			System.out.println("5. Convert Currency");
			System.out.println("6. Export Report");
			System.out.println("7. Exit");
			System.out.print("Choose an option: ");

			int choice = input.nextInt();
			input.nextLine();

			if (choice == 1)
			{
				AddMonthlyIncome addIncomeWindow = new AddMonthlyIncome(appUser);
			}
			else if (choice == 2)
			{
				AddExpense addExpenseWindow = new AddExpense(appUser);
			}
			else if (choice == 3)
			{
				System.out.println("\n---- REPORT MENU ----");
    				System.out.println("1. Income Report");
    				System.out.println("2. Expense Report");
    				System.out.println("3. Detailed Finance Report");
    				System.out.print("Choose a report: ");

    				int reportChoice = input.nextInt();
    				input.nextLine();

    				if (reportChoice == 1)
    				{
    					PrintIncomeReport printExpenseReportWindow = new PrintIncomeReport(appUser);
    				}
    				else if (reportChoice == 2)
    				{
    					PrintExpenseReport printExpenseReportWindow = new PrintExpenseReport(appUser);
    				}
    				else if (reportChoice == 3)
    				{
        				PrintFullReport printFullReportWindow = new PrintFullReport(appUser);
    				}
    				else
    				{
        				System.out.println("Invalid report option.");
    				}
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
				System.out.print("\nEnter amount in base currency: ");
				double amount = input.nextDouble();
				input.nextLine();

				System.out.print("Enter target currency (ex. EUR): ");
				String currencyName = input.nextLine();

				System.out.print("Enter exchange rate (1 USD = ? " + currencyName + "): ");
				double rate = input.nextDouble();
				input.nextLine();

				Currency c = new Currency();
				c.name = currencyName;
				c.rate = rate;

				Currency result = app.convertForeignCurrency(c, amount);

				System.out.println("\n===== CURRENCY CONVERSION =====");
				System.out.println("Base Amount: $" + amount + " USD");
				System.out.println("Converted Amount: " + result.rate + " " + result.name);
			}
			else if (choice == 6)
			{
				System.out.print("\nEnter report file name: ");
				String reportTitle = input.nextLine();

				//app.exportReport(reportTitle);
			}
			else if (choice == 7)
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
			double yearlyIncome = wage.amount * wage.Month;

			System.out.println(
				"Source: " + wage.source +
				" | Amount: $" + wage.amount +
				" | Frequency: " + wage.Month +
				" | Yearly: $" + yearlyIncome);

			totalIncome += yearlyIncome;
		}

		System.out.println("\nTotal Income (Yearly): $" + totalIncome);
	}

	@Override
	public void PrintFullreport()
	{
   		double totalIncome = 0;
   		double totalExpenses = 0;

    		System.out.println("\n===== DETAILED FINANCE REPORT =====");

   		System.out.println("\n--- Income Entries ---");
    		if (incomes.isEmpty())
    		{
        		System.out.println("No income data exists.");
    		}
    		else
    		{
        		for (Wage wage : incomes)
        		{
           			System.out.println(
                   	 		"Source: " + wage.source +
                    			" | Amount: $" + wage.amount +
                    			" | Frequency: " + wage.Month);

            			totalIncome += wage.amount;
       			}
   		}

    		System.out.println("\n--- Expense Entries ---");
    		if (expenses.isEmpty())
    		{
        		System.out.println("No expense data exists.");
   		}
    		else
    		{
        		for (Expense expense : expenses)
        		{
            			System.out.println(
                    			"Source: " + expense.source +
                    			" | Amount: $" + expense.amount +
                    			" | Frequency: " + expense.yearlyfrequency);

            			totalExpenses += expense.amount;
        		}
    		}

    		double savings = totalIncome - totalExpenses;

    		System.out.println("\n--- Summary ---");
    		System.out.println("Total Income: $" + totalIncome);
    		System.out.println("Total Expenses: $" + totalExpenses);
		
    		if (savings >= 0)
    		{
        		System.out.println("Total Savings: $" + savings);
    		}
    		else
   		{
        		System.out.println("Total New Debt: $" + Math.abs(savings));
    		}
	}

	
	//public void PrintIncomereportbyTpe() {
		//throw new UnsupportedOperationException("Unimplemented method 'PrintIncomereportbyTpe'");
	//}

	
	//public void PrintExpensebyType() {
		//throw new UnsupportedOperationException("Unimplemented method 'PrintExpensebyType'");
	//}

	
	/*public void exportReport(String reportTitle)
	{	
   		try
    		{

        java.io.PrintWriter writer = new java.io.PrintWriter(reportTitle + ".csv");

        writer.println("Type,Source,Amount,Frequency");

        for (Wage wage : incomes)
        {
            writer.println("Income," + wage.source + "," + wage.amount + "," + wage.Month);
        }

        for (Expense expense : expenses)
        {
            writer.println("Expense," + expense.source + "," + expense.amount + "," + expense.yearlyfrequency);
        }

        writer.close();

        System.out.println("Report exported successfully to " + reportTitle + ".csv");
    }
    catch (Exception e)
    {
        System.out.println("Error exporting report: " + e.getMessage());
    }
}
*/
	@Override
	public Currency convertForeignCurrency(Currency C, double amount)
	{
		Currency converted = new Currency();

		converted.name = C.name;
		converted.rate = amount * C.rate;

		return converted;
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
			totalMonthlyIncome += yearlyIncome / 12.0;
		}

		for (Expense e : expenses)
		{
			double yearlyExpense = e.amount * e.yearlyfrequency;
			totalMonthlyExpenses += yearlyExpense / 12.0;
		}

		monthlySavings = totalMonthlyIncome - totalMonthlyExpenses;

		System.out.println("\nMonthly savings updated: $" + monthlySavings);
	}

	@Override
	public void AddExpense(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void PrintExpensereport() {
		// TODO Auto-generated method stub
		
	}
}