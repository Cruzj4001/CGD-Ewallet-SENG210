import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.SpringLayout;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.ComboBoxModel;

public class PrintFullReport extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	User user;
	ArrayList<Wage>    income;
	ArrayList<Expense> spending;
	ArrayList<Wage>    summaryArray;
	private JTextField incomeTotalText;
	private JTextField expenseTotalText;
	private JTextField exportText;
	private JTextArea  incomeText;
	private JTextArea  expenseText;
	private JButton exportButton;
	private JTextField totalText;
	
	
	/** for testing 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddExpense frame = new AddExpense(new User("a", "b"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the frame.
	 */
	public PrintFullReport(User u) {
		user = u; //so it can be referenced in the external action
		
		//update ui with info
		String incomeSummaryString  = "";
		String expenseSummaryString = "";
		double incomeSummaryTotal   = 0;
		double expenseSummaryTotal  = 0;
		double summaryTotal;
		income   = user.getIncome();
		spending = user.getSpending();
		
		//income data
		if (income.size() <= 0) {
			incomeSummaryString = "No income data exists.";
		}
		else {
			for (Wage wage:income) {
				//check that expense source is the same as filtered source
				
				String monthString = "";
				switch (wage.Month) {
					case 0:
						monthString = "January";
						break;
					case 1:
						monthString = "February";
						break;
					case 2:
						monthString = "March";
						break;
					case 3:
						monthString = "April";
						break;
					case 4:
						monthString = "May";
						break;
					case 5:
						monthString = "June";
						break;
					case 6:
						monthString = "July";
						break;
					case 7:
						monthString = "August";
						break;
					case 8:
						monthString = "September";
						break;
					case 9:
						monthString = "October";
						break;
					case 10:
						monthString = "November";
						break;
					case 11:
						monthString = "December";
						break;
				}
				//add to summary string
				incomeSummaryString += (
						"Source: " + wage.source +
						" | Amount: $"   + String.format("%.2f", wage.amount) +
						" | Month: $"    + monthString +
						"\n");
				//add to total
				incomeSummaryTotal += wage.amount;

			}
		}
		
		//expense data
		if (spending.size() <= 0) {
			expenseSummaryString = "No expense data exists.";
		}
		else {
			for (Expense expense:spending) {
				//check that expense source is the same as filtered source
				double yearlyExpense = expense.amount * expense.yearlyfrequency;
				String yearlyFreqString = "";
				switch (expense.yearlyfrequency) {
					case 1:
						yearlyFreqString = "Yearly";
						break;
					case 12:
						yearlyFreqString = "Monthly";
						break;
					case 24:
						yearlyFreqString = "Biweekly";
						break;
				}
				expenseSummaryString += (
						"Source: " + expense.source +
						" | Amount: $" + String.format("%.2f", expense.amount) +
						" | Frequency: " + yearlyFreqString +
						" | Yearly: $" + String.format("%.2f", yearlyExpense) +
						"\n");
				//add to total
				expenseSummaryTotal += yearlyExpense;
			}
		}
		
		summaryTotal = incomeSummaryTotal - expenseSummaryTotal;
		
		setMinimumSize(new Dimension(275, 500));
		setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 275, 719);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(212, 208, 200));
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setPreferredSize(new Dimension(10, 40));
		titlePanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		titlePanel.setBackground(new Color(0, 0, 128));
		contentPane.add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new BorderLayout(0, 0));
		
		JLabel tileLabel = new JLabel(" Print Full Report");
		tileLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 17));
		tileLabel.setForeground(Color.WHITE);
		tileLabel.setHorizontalAlignment(SwingConstants.LEFT);
		tileLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		titlePanel.add(tileLabel);
		
		JPanel featuresPanel = new JPanel();
		featuresPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		featuresPanel.setBackground(new Color(212, 208, 200));
		contentPane.add(featuresPanel, BorderLayout.CENTER);
		featuresPanel.setLayout(new BorderLayout(5, 5));
		
		JPanel summariesPanel = new JPanel();
		featuresPanel.add(summariesPanel, BorderLayout.CENTER);
		summariesPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel incomeSummaryPanel = new JPanel();
		incomeSummaryPanel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(255, 255, 255))));
		incomeSummaryPanel.setPreferredSize(new Dimension(0, 0));
		incomeSummaryPanel.setBackground(new Color(212, 208, 200));
		summariesPanel.add(incomeSummaryPanel);
		incomeSummaryPanel.setLayout(new BorderLayout(5, 0));
		
		JScrollPane incomeScrollPane = new JScrollPane();
		incomeScrollPane.setBackground(new Color(212, 208, 200));
		incomeScrollPane.setPreferredSize(new Dimension(2, 100));
		incomeScrollPane.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 5, 0), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		incomeSummaryPanel.add(incomeScrollPane);
		
		incomeText = new JTextArea(incomeSummaryString);
		incomeText.setEditable(false);
		incomeScrollPane.setViewportView(incomeText);
		
		JPanel incomeTotalPanel = new JPanel();
		incomeTotalPanel.setBorder(null);
		incomeTotalPanel.setBackground(new Color(212, 208, 200));
		incomeTotalPanel.setPreferredSize(new Dimension(10, 30));
		incomeSummaryPanel.add(incomeTotalPanel, BorderLayout.SOUTH);
		incomeTotalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel incomeTotalLabel = new JLabel("Total (Yearly) Income:");
		incomeTotalLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		incomeTotalPanel.add(incomeTotalLabel);
		
		incomeTotalText = new JTextField("$" + String.format("%.2f", incomeSummaryTotal));
		incomeTotalText.setPreferredSize(new Dimension(7, 20));
		incomeTotalText.setEditable(false);
		incomeTotalPanel.add(incomeTotalText);
		incomeTotalText.setColumns(10);
		
		JLabel incomeLabel = new JLabel("Incomes:");
		incomeLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		incomeSummaryPanel.add(incomeLabel, BorderLayout.NORTH);
		
		//expenses
		JPanel expenseSummaryPanel = new JPanel();
		expenseSummaryPanel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(255, 255, 255))));
		expenseSummaryPanel.setPreferredSize(new Dimension(0, 0));
		expenseSummaryPanel.setBackground(new Color(212, 208, 200));
		summariesPanel.add(expenseSummaryPanel);
		expenseSummaryPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane expenseScrollPane = new JScrollPane();
		expenseScrollPane.setBackground(new Color(212, 208, 200));
		expenseScrollPane.setPreferredSize(new Dimension(2, 100));
		expenseScrollPane.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 5, 0), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		expenseSummaryPanel.add(expenseScrollPane);
		
		expenseText = new JTextArea(expenseSummaryString);
		expenseText.setEditable(false);
		expenseScrollPane.setViewportView(expenseText);
		
		JPanel expenseTotalPanel = new JPanel();
		expenseTotalPanel.setBorder(null);
		expenseTotalPanel.setBackground(new Color(212, 208, 200));
		expenseTotalPanel.setPreferredSize(new Dimension(10, 30));
		expenseSummaryPanel.add(expenseTotalPanel, BorderLayout.SOUTH);
		expenseTotalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel expenseTotalLabel = new JLabel("Total (Yearly) Income:");
		expenseTotalLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		expenseTotalPanel.add(expenseTotalLabel);
		
		expenseTotalText = new JTextField("$" + String.format("%.2f", expenseSummaryTotal));
		expenseTotalText.setPreferredSize(new Dimension(7, 20));
		expenseTotalText.setEditable(false);
		expenseTotalPanel.add(expenseTotalText);
		expenseTotalText.setColumns(10);
		
		JLabel expenseLabel = new JLabel("Expenses:\r\n");
		expenseLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		expenseSummaryPanel.add(expenseLabel, BorderLayout.NORTH);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		featuresPanel.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setPreferredSize(new Dimension(0, 90));
		bottomPanel.setBackground(new Color(212, 208, 200));
		bottomPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel totalPanel = new JPanel();
		totalPanel.setBackground(new Color(212, 208, 200));
		bottomPanel.add(totalPanel, BorderLayout.NORTH);
		totalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel totalLabel = new JLabel("Yearly Change in Balance:");
		totalLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 9));
		totalPanel.add(totalLabel);
		
		totalText = new JTextField(summaryTotal + "");
		totalText.setEditable(false);
		totalPanel.add(totalText);
		totalText.setColumns(10);
		
		JPanel export_panel = new JPanel();
		export_panel.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new LineBorder(Color.WHITE)));
		bottomPanel.add(export_panel);
		export_panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel exportNameLabelPanel = new JPanel();
		exportNameLabelPanel.setPreferredSize(new Dimension(0, 80));
		export_panel.add(exportNameLabelPanel);
		FlowLayout flowLayout = (FlowLayout) exportNameLabelPanel.getLayout();
		flowLayout.setHgap(0);
		exportNameLabelPanel.setBackground(new Color(212, 208, 200));
		
		JLabel exportNameLabel = new JLabel("Export File Name:");
		exportNameLabelPanel.add(exportNameLabel);
		exportNameLabel.setPreferredSize(new Dimension(120, 20));
		exportNameLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		
		exportText = new JTextField();
		exportNameLabelPanel.add(exportText);
		exportText.setPreferredSize(new Dimension(100, 21));
		exportText.setColumns(10);
		
		exportButton = new JButton("Export Report");
		export_panel.add(exportButton);
		exportButton.setPreferredSize(new Dimension(120, 30));
		exportButton.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		exportButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		exportButton.setBackground(new Color(212, 208, 200));
		exportButton.addActionListener(this);
		
		
		
		this.setVisible(true);
	}
	
	//add expense
	public void actionPerformed(ActionEvent e) {
			//if export button is clicked
			if (e.getSource() == exportButton) {
				try
	    		{

	        java.io.PrintWriter writer = new java.io.PrintWriter(exportText.getText() + ".csv");

	        for (Wage wage : income)
	        {
	            writer.println("Income," + wage.source + "," + wage.amount + "," + wage.Month);
	        }
	        
	        for (Expense expense : spending)
	        {
	            writer.println("Expense," + expense.source + "," + expense.amount + "," + expense.yearlyfrequency);
	        }

	        writer.close();

	        System.out.println("Report exported successfully to " + exportText.getText() + ".csv");
	    }
	    catch (Exception exc)
	    {
	    	JOptionPane.showMessageDialog(null, "Error exporting report: " + exc.getMessage());
	    }
			}
			
		}
	}

