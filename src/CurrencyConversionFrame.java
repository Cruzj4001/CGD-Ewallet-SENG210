import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.*;

public class CurrencyConversionFrame extends JFrame {

	private JTextField currAmount;
	private JComboBox<String> boxCombo;
	private CurrencyConversion conversionLogic;
	
	
	public CurrencyConversionFrame() {
		
	conversionLogic = new CurrencyConversion(); //needed to assign
	
	setTitle("Currency Convertor");
	setSize(275, 300);
	setLocationRelativeTo(null);
	setLayout(new BorderLayout());
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	

	//border panel
	JPanel borderPanel = new JPanel();
	borderPanel.setBackground(new Color(0, 0, 128));
	borderPanel.setPreferredSize(new Dimension(10, 40));
	borderPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	
	
	JLabel titleLabel = new JLabel("Currency Converter");
	titleLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 17));
	titleLabel.setForeground(Color.WHITE);
	borderPanel.add(titleLabel);
	
	//body panel
	JPanel bodyPanel = new JPanel();
	bodyPanel.setBackground(new Color(212, 208, 200));
	bodyPanel.setLayout(null);
	bodyPanel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	
	//AmountField
	JLabel amountLabel = new JLabel ("Amount:");
	amountLabel.setBounds(10, 20, 80, 25);
	amountLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
	bodyPanel.add(amountLabel);
	
	currAmount = new JTextField();
	currAmount.setBounds(90, 20, 150, 25);
	currAmount.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
	bodyPanel.add(currAmount);
	

	
	//Results button
	JButton calcButton = new JButton("Calculate");
	calcButton.setBounds(70, 110, 120, 30);
	calcButton.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
	calcButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	calcButton.setBackground(new Color(212, 208, 200));
	bodyPanel.add(calcButton);
	
	//ComboBox options stored as strings to pass to arrayList boxCombo
	String[] boxOptions = {
			"USDOLLAR TO JPYEN",
			"YPYEN TO USDOLLAR",
			"USDOLLAR TO EURO",
			"EURO TO USDOLLAR",
			"USDOLLAR TO POUND",
			"POUND TO USDOLLAR",
			"USDOLLAR TO CADOLLAR",
			"CADOLLAR TO USDOLLAR",
	};
	
	boxCombo = new JComboBox<>(boxOptions);
	boxCombo.setBounds(10, 60, 200, 25);
	boxCombo.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
	boxCombo.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(212,208,200), new Color(212,208,200), Color.BLACK, Color.GRAY));
	bodyPanel.add(boxCombo);
	
	//messageDialog for button
	calcButton.addActionListener(e -> {
		
		//funny parsing for strings to pass param types to logic class
		double amount = Double.parseDouble(currAmount.getText());
		String choice = boxCombo.getSelectedItem().toString();
		
		//pass to logic class && call method after formatting
		double result = conversionLogic.convertResult(amount, choice);
		String formattedResult = String.format("%.2f", result);
		
		JOptionPane.showMessageDialog(this, "Result from " + choice + ": " + formattedResult);
		
	});
	
	add(borderPanel, BorderLayout.NORTH);
	add(bodyPanel, BorderLayout.CENTER);
	
	
	setVisible(true);
	
	}
}