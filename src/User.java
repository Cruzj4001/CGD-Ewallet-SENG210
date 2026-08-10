import java.util.ArrayList;

public class User {
	private ArrayList <Currency>currencyRates;
	private ArrayList <Wage>Income;  // user income sources that user can record or view or search by type or month 
	private ArrayList <Expense>Spending; //user's expenses 
	
	String username;
	String pwd;
	//current total income - total 
	double balance;
	// possible monthly savings, calculated using monthly income (most recent) assuming the data we have is for one year, and monthly and biweekly expenses, here you can assume yearly expenses that are recorded have already been paid. 
	double monthlysavings;	
	//should add constructor(s)
	User(String username,String password){
		Spending      = new ArrayList<Expense>();
		Income        = new ArrayList<Wage>();
		currencyRates = new ArrayList<Currency>();
	}
	
	//adds expense to end of spending
	public void addSpending(Expense e) {
		Spending.add(e);
	}
	
	//adds wage to end of income
	public void addIncome(Wage w) {
		Income.add(w);
	}
	
	//gets spending array
	public ArrayList <Expense> getSpending() {
		return Spending;
	}
	
	//gets income array
	public ArrayList<Wage> getIncome() {
		return Income;
	}
}
