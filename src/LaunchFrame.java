import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class LaunchFrame extends JFrame{
	
	public LaunchFrame() {


		setTitle("E-Wallet App");
		setSize(350, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout(0,0));



		//header panel
		JPanel titlePanel = new JPanel();

		titlePanel.setPreferredSize(new Dimension(10,50));
		titlePanel.setBackground(new Color(0,0,128));
		titlePanel.setBorder(new BevelBorder(BevelBorder.RAISED,null,null,null,null));


		JLabel titleLabel = new JLabel("Welcome To The E-Wallet App!");

		titleLabel.setFont(new Font("8-bit Operator+", Font.PLAIN, 17));
		titleLabel.setForeground(Color.WHITE);

		titlePanel.add(titleLabel);


		add(titlePanel, BorderLayout.NORTH);



		//body panel
		JPanel bodyPanel = new JPanel();

		bodyPanel.setBackground(new Color(212,208,200));
		bodyPanel.setBorder(new BevelBorder(BevelBorder.RAISED,null,null,null,null));
		bodyPanel.setLayout(null);


		JLabel welcomeLabel = new JLabel("Manage Your Money!!!");

		welcomeLabel.setFont(new Font("8-bit Operator+", Font.BOLD, 12));
		welcomeLabel.setBounds(100,80,220,25);

		bodyPanel.add(welcomeLabel);



		JButton startButton = new JButton("Start");

		startButton.setBounds(100,110,120,35);
		startButton.setFont(new Font("8-bit Operator+", Font.PLAIN, 12));
		startButton.setBackground(new Color(212,208,200));
		startButton.setBorder(new BevelBorder(BevelBorder.RAISED,null,null,null,null));


		bodyPanel.add(startButton);




		startButton.addActionListener(e -> {

			new MainFrame();

			dispose();
		});



		add(bodyPanel, BorderLayout.CENTER);

		setVisible(true);

	}



	public static void main(String[] args) {

		new LaunchFrame();

	}

}

