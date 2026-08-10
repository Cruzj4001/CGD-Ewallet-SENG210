import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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

public class AddExpense extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField sourceTextfield;

	
	//import ui elements
	JComboBox frequencyCombo;
	JButton   submitButton;
	JSpinner  amountSpinner;
	
	User user;
	
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
	public AddExpense(User u) {
		user = u; //so it can be referenced in the external action
		setMinimumSize(new Dimension(275, 300));
		setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		setTitle("Add Expense");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 318, 192);
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
		
		JLabel tileLabel = new JLabel(" Add Expense");
		tileLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 17));
		tileLabel.setForeground(Color.WHITE);
		tileLabel.setHorizontalAlignment(SwingConstants.LEFT);
		tileLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		titlePanel.add(tileLabel);
		
		JPanel featuresPanel = new JPanel();
		featuresPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		featuresPanel.setBackground(new Color(212, 208, 200));
		contentPane.add(featuresPanel, BorderLayout.CENTER);
		featuresPanel.setLayout(new GridLayout(0, 1, 5, 10));
		
		JPanel sourcePanel = new JPanel();
		sourcePanel.setBackground(new Color(212, 208, 200));
		sourcePanel.setPreferredSize(new Dimension(300, 0));
		featuresPanel.add(sourcePanel);
		sourcePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel sourceLabel = new JLabel("Source:");
		sourceLabel.setPreferredSize(new Dimension(70, 13));
		sourceLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		sourcePanel.add(sourceLabel);
		
		sourceTextfield = new JTextField();
		sourceTextfield.setPreferredSize(new Dimension(100, 20));
		sourceTextfield.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		sourceTextfield.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(212, 208, 200), new Color(212, 208, 200), new Color(0, 0, 0), new Color(128, 128, 128)));
		sourceTextfield.setMinimumSize(new Dimension(100, 20));
		sourcePanel.add(sourceTextfield);
		sourceTextfield.setColumns(10);
		
		JPanel amountPanel = new JPanel();
		amountPanel.setPreferredSize(new Dimension(300, 0));
		amountPanel.setBackground(new Color(212, 208, 200));
		featuresPanel.add(amountPanel);
		
		JLabel amountLabel = new JLabel("Amount:");
		amountLabel.setPreferredSize(new Dimension(70, 13));
		amountLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		 
		amountSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 9999.99, .01));
		amountSpinner.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(212, 208, 200), new Color(212, 208, 200), Color.BLACK, Color.GRAY));
		amountSpinner.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		amountSpinner.setPreferredSize(new Dimension(100, 20));
		amountPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		amountPanel.add(amountLabel);
		amountPanel.add(amountSpinner);
		
		JPanel frequencyPanel = new JPanel();
		frequencyPanel.setPreferredSize(new Dimension(300, 0));
		frequencyPanel.setBackground(new Color(212, 208, 200));
		featuresPanel.add(frequencyPanel);
		frequencyPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel frequencyLabel = new JLabel("Frequency:");
		frequencyLabel.setPreferredSize(new Dimension(70, 13));
		frequencyLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		frequencyPanel.add(frequencyLabel);
		
		
		//setup month array
				String[] frequencyArray = new String[3];
				frequencyArray[0]  = "Yearly";
				frequencyArray[1]  = "Monthly";
				frequencyArray[2]  = "Biweekly";
				
		frequencyCombo = new JComboBox(frequencyArray);
		frequencyCombo.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(212, 208, 200), new Color(212, 208, 200), Color.BLACK, Color.GRAY));
		frequencyCombo.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		frequencyCombo.setPreferredSize(new Dimension(100, 21));
		frequencyPanel.add(frequencyCombo);
		
		JPanel submitPanel = new JPanel();
		contentPane.add(submitPanel, BorderLayout.SOUTH);
		submitPanel.setPreferredSize(new Dimension(0, 50));
		submitPanel.setBackground(new Color(212, 208, 200));
		submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		submitButton = new JButton("Add Expense");
		submitButton.addActionListener(this);
			
		submitButton.setForeground(new Color(0, 0, 0));
		submitButton.setBackground(new Color(212, 208, 200));
		submitButton.setActionCommand("");
		submitButton.setPreferredSize(new Dimension(200, 30));
		submitButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, new Color(0, 0, 0), Color.GRAY));
		submitButton.setFont(new Font("8-bit Operator+", Font.PLAIN, 11));
		submitPanel.add(submitButton);
		this.setVisible(true);
		
	}
	
	//add expense
	public void actionPerformed(ActionEvent e) {
		String source    = sourceTextfield.getText();
		double amount    = (double) amountSpinner.getValue();
		int    frequency = 0;
		switch (frequencyCombo.getSelectedIndex()) {
			case 0: 
				frequency = 1;
				break;
			case 1:
				frequency = 12;
				break;
			case 2:
				frequency = 24;
				break;
		}
		user.addSpending(new Expense(source, amount, frequency));
		Expenser.updateMonthlySavings(user);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //close window
		return;
	}

}
