import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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

public class whenCanIBuy extends JFrame implements ChangeListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField monthsTextField;
	JSpinner  priceSpinner;
	
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
	public whenCanIBuy(User u) {
		user = u; //so it can be referenced in the external action
		setMinimumSize(new Dimension(275, 200));
		setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		setTitle("\r\n");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 328, 188);
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
		
		
		JLabel titleLabel = new JLabel("Saving Estimator");
		titleLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 17));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		titlePanel.add(titleLabel);
		
		JPanel featuresPanel = new JPanel();
		featuresPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
		featuresPanel.setBackground(new Color(212, 208, 200));
		contentPane.add(featuresPanel, BorderLayout.CENTER);
		featuresPanel.setLayout(new GridLayout(0, 1, 5, 10));
		
		JPanel pricePanel = new JPanel();
		pricePanel.setPreferredSize(new Dimension(300, 0));
		pricePanel.setBackground(new Color(212, 208, 200));
		featuresPanel.add(pricePanel);
		
		JLabel priceLabel = new JLabel("Item Price:\r\n");
		priceLabel.setPreferredSize(new Dimension(70, 13));
		priceLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		 
		priceSpinner = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 9999.99, .01));
		priceSpinner.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(212, 208, 200), new Color(212, 208, 200), Color.BLACK, Color.GRAY));
		priceSpinner.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		priceSpinner.setPreferredSize(new Dimension(100, 20));
		priceSpinner.addChangeListener(this);
		pricePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pricePanel.add(priceLabel);
		pricePanel.add(priceSpinner);
		
		JPanel monthsPanel = new JPanel();
		monthsPanel.setBackground(new Color(212, 208, 200));
		monthsPanel.setPreferredSize(new Dimension(300, 0));
		featuresPanel.add(monthsPanel);
		monthsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel monthsLabel = new JLabel("Months to Save:");
		monthsLabel.setPreferredSize(new Dimension(100, 13));
		monthsLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		monthsPanel.add(monthsLabel);
		
		monthsTextField = new JTextField();
		monthsTextField.setPreferredSize(new Dimension(100, 20));
		monthsTextField.setFont(new Font("8-bit Operator+", Font.PLAIN, 10));
		monthsTextField.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(212, 208, 200), new Color(212, 208, 200), new Color(0, 0, 0), new Color(128, 128, 128)));
		monthsTextField.setMinimumSize(new Dimension(100, 20));
		monthsPanel.add(monthsTextField);
		monthsTextField.setColumns(10);
		
		this.setVisible(true);
		
	}
	
	//add expense
	public void changePerformed(ChangeEvent e) {
		String source    = monthsTextField.getText();
		double amount    = (double) priceSpinner.getValue();
		int    frequency = 0;
		user.addSpending(new Expense(source, amount, frequency));
		Expenser.updateMonthlySavings(user);
		this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //close window
		return;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		double price  = (double) priceSpinner.getValue();
		int    months = (int) Math.ceil(price / user.monthlysavings);
		monthsTextField.setText(months + "");
	}

}
