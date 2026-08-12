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

public class PrintExpenseReport extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	User user;
	ArrayList<Expense> spending;
	ArrayList<Expense> summaryArray;
	private JTextField totalText;
	private JTextField exportText;
	private JTextArea  expenseText;
	private JComboBox  sourceCombo;
	JButton exportButton;
	
	
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
	public PrintExpenseReport(User u) {
		user = u; //so it can be referenced in the external action
		
		//update ui with info
		String summaryString = "";
		double summaryTotal  = 0;
		spending             = user.getSpending();
		summaryArray         = new ArrayList<Expense>();
		ArrayList<String>  sourceArray  = new ArrayList<String>();
		sourceArray.add("All");
		
		if (spending.size() <= 0) {
			summaryString = "No expense data exists.";
		}
		else {
			for (Expense expense:spending) {
				//check that expense source is the same as filtered source
				//add to array
				summaryArray.add(expense);
				//add to summary string
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
				summaryString += (
						"Source: " + expense.source +
						" | Amount: $" + String.format("%.2f", expense.amount) +
						" | Frequency: " + yearlyFreqString +
						" | Yearly: $" + String.format("%.2f", yearlyExpense) +
						"\n");
				//add to total
				summaryTotal += yearlyExpense;
			
				if (!sourceArray.contains(expense.source)) {
					sourceArray.add(expense.source);
				}
			}
		}
		
		String[] sourceList = new String[sourceArray.size()];
		for (int i = 0; i < sourceArray.size(); i++) {
			sourceList[i] = sourceArray.get(i);
		}
		
		setMinimumSize(new Dimension(275, 500));
		setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 160, 411);
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
		
		JLabel tileLabel = new JLabel("Print Expense Report");
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
		
		JPanel summaryPanel = new JPanel();
		summaryPanel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(255, 255, 255))));
		summaryPanel.setPreferredSize(new Dimension(0, 0));
		summaryPanel.setBackground(new Color(212, 208, 200));
		featuresPanel.add(summaryPanel);
		summaryPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel sourcePanel = new JPanel();
		sourcePanel.setPreferredSize(new Dimension(10, 30));
		sourcePanel.setBackground(new Color(212, 208, 200));
		summaryPanel.add(sourcePanel, BorderLayout.NORTH);
		sourcePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel sourceLabel = new JLabel("Filter By Source:");
		sourceLabel.setPreferredSize(new Dimension(120, 20));
		sourceLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		sourcePanel.add(sourceLabel);
		
		sourceCombo = new JComboBox(sourceList);
		sourceCombo.setPreferredSize(new Dimension(100, 21));
		sourceCombo.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		sourceCombo.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(212, 208, 200), new Color(212, 208, 200), Color.BLACK, Color.GRAY));
		sourcePanel.add(sourceCombo);
		sourceCombo.addActionListener(this);
		
		JScrollPane expenseScrollPane = new JScrollPane();
		expenseScrollPane.setBackground(new Color(212, 208, 200));
		expenseScrollPane.setPreferredSize(new Dimension(2, 100));
		expenseScrollPane.setBorder(new CompoundBorder(new EmptyBorder(0, 0, 5, 0), new BevelBorder(BevelBorder.LOWERED, null, null, null, null)));
		summaryPanel.add(expenseScrollPane);
		
		expenseText = new JTextArea(summaryString);
		expenseText.setEditable(false);
		expenseScrollPane.setViewportView(expenseText);
		
		JPanel totalPanel = new JPanel();
		totalPanel.setBorder(null);
		totalPanel.setBackground(new Color(212, 208, 200));
		totalPanel.setPreferredSize(new Dimension(10, 30));
		summaryPanel.add(totalPanel, BorderLayout.SOUTH);
		totalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel totalLabel = new JLabel("Total (Yearly) Expense:");
		totalLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		totalPanel.add(totalLabel);
		
		totalText = new JTextField("$" + String.format("%.2f", summaryTotal));
		totalText.setPreferredSize(new Dimension(7, 20));
		totalText.setEditable(false);
		totalPanel.add(totalText);
		totalText.setColumns(10);
		
		JPanel exportNamePanel = new JPanel();
		exportNamePanel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new LineBorder(new Color(255, 255, 255))));
		featuresPanel.add(exportNamePanel, BorderLayout.SOUTH);
		exportNamePanel.setPreferredSize(new Dimension(0, 70));
		exportNamePanel.setBackground(new Color(212, 208, 200));
		exportNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel exportNameLabelPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) exportNameLabelPanel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		exportNameLabelPanel.setBackground(new Color(212, 208, 200));
		exportNamePanel.add(exportNameLabelPanel);
		
		JLabel exportNameLabel = new JLabel("Export File Name:");
		exportNameLabelPanel.add(exportNameLabel);
		exportNameLabel.setPreferredSize(new Dimension(120, 20));
		exportNameLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		
		exportText = new JTextField();
		exportNameLabelPanel.add(exportText);
		exportText.setPreferredSize(new Dimension(100, 21));
		exportText.setColumns(10);
		
		exportButton = new JButton("Export Report");
		exportNamePanel.add(exportButton);
		exportButton.setPreferredSize(new Dimension(120, 30));
		exportButton.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		exportButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		exportButton.setBackground(new Color(212, 208, 200));
		exportButton.addActionListener(this);
		
		this.setVisible(true);
	}
	
	//add expense
	public void actionPerformed(ActionEvent e) {
			
			//if source filter is changed
			//refills summary text with filtered expense info
			if (e.getSource() == sourceCombo) {
				
				int    sourceOverride = sourceCombo.getSelectedIndex(); //if this equals 0, overrides filter
				String sourceFilter   = (String) sourceCombo.getSelectedItem();
				String summaryString  = "";
				double summaryTotal   = 0;
				spending              = user.getSpending();
				summaryArray          = new ArrayList<Expense>();
				
				for (Expense expense:spending) {
					//check that expense source is the same as filtered source
					if (expense.source.equals(sourceFilter) || sourceOverride == 0) {
						//add to array
						summaryArray.add(expense);
						//add to summary string
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
						summaryString += (
								"Source: " + expense.source +
								" | Amount: $" + String.format("%.2f", expense.amount) +
								" | Frequency: " + yearlyFreqString +
								" | Yearly: $" + String.format("%.2f", yearlyExpense) +
								"\n");
						//add to total
						summaryTotal += yearlyExpense;
					}
				}
				if (summaryArray.size() <= 0) {
					summaryString = "No expense data exists.";
				}
				
				//update ui
				expenseText.setText(summaryString);
				totalText.setText("$" + String.format("%.2f", summaryTotal));
			}
			//if export button is clicked
			if (e.getSource() == exportButton) {
				try
	    		{

	        java.io.PrintWriter writer = new java.io.PrintWriter(exportText.getText() + ".csv");

	        for (Expense expense : summaryArray)
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

