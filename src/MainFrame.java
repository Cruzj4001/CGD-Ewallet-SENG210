import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;

public class MainFrame extends JFrame {

	public User actionU;

	public MainFrame() {

		// addeder here for a demo user as hardcoded
		actionU = new User("test", "123");

		actionU.addIncome(new Wage("Job A", 500, 1));
		actionU.addIncome(new Wage("Job B", 1200, 2));
		actionU.addIncome(new Wage("Side Work", 800, 3));

		setTitle("E-Wallet App");
		setSize(350, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setLayout(new BorderLayout(5,5));
		
		//header panel
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(10,40));
		titlePanel.setBackground(new Color(0,0,128));
		titlePanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));


		JLabel titleLabel = new JLabel("E-Wallet App");
		titleLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 17));
		titleLabel.setForeground(Color.WHITE);

		titlePanel.add(titleLabel);
		
		JPanel buttonPanel = new JPanel();

		buttonPanel.setBackground(new Color(212,208,200));
		buttonPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		buttonPanel.setLayout(new GridLayout(7,1,5,5));

		JButton savingBtn = new JButton("Saving Calculator");

		JButton currencyConversion = new JButton("Currency Convertor");

		JButton incomeReportButton = new JButton("Income Report");

		JButton exportIncomeButton = new JButton("Export Income Report");

		JButton expenseReportButton = new JButton("Expense Report");

		JButton addMonthlyIncomeButton = new JButton("Add Monthly Income");

		JButton addExpenseButton = new JButton("Add Expense");
		
		JButton[] buttons = {
				savingBtn,
				currencyConversion,
				incomeReportButton,
				exportIncomeButton,
				expenseReportButton,
				addMonthlyIncomeButton,
				addExpenseButton
		};


		for(JButton button : buttons)
		{
			button.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
			button.setBackground(new Color(212,208,200));
			button.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		}

		savingBtn.addActionListener(e -> {
			new whenCanIBuy(actionU);
		});

		currencyConversion.addActionListener(e -> {
			new CurrencyConversionFrame();
		});

		incomeReportButton.addActionListener(e -> {
			SwingUtilities.invokeLater(() -> {
				new PrintIncomeReport(actionU).setVisible(true); // foced open was not showing
			});
		});

		exportIncomeButton.addActionListener(e -> {

			new PrintIncomeReport(actionU);
		});

		expenseReportButton.addActionListener(e -> {

			SwingUtilities.invokeLater(() -> {
				new PrintExpenseReport(actionU).setVisible(true); // forced again
			});
		});

		addMonthlyIncomeButton.addActionListener(e -> {
			Wage w = new Wage("", 0.0, 1); // had to add this empty
			new AddMonthlyIncome(actionU);
		});
		
		addExpenseButton.addActionListener( e -> {
			Expense exp = new Expense("", 0, 1); // another empty
			new AddExpense(actionU); //should open frame now
		});
		
		add(titlePanel, BorderLayout.NORTH);
		buttonPanel.add(addExpenseButton);
		buttonPanel.add(addMonthlyIncomeButton);
		buttonPanel.add(expenseReportButton);
		buttonPanel.add(exportIncomeButton);
		buttonPanel.add(incomeReportButton);
		buttonPanel.add(currencyConversion);
		buttonPanel.add(savingBtn);
		add(buttonPanel, BorderLayout.CENTER);
		setVisible(true);
	}

	
}
