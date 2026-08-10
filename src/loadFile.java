import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class loadFile extends JFrame{
	public loadFile(User u) {
		JFileChooser fc = new JFileChooser();
		int approval    = fc.showOpenDialog(this);
		
		if (approval == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			try {
				Scanner inputStream = new Scanner(file);
				while (inputStream.hasNextLine()) {
					String   line      = inputStream.nextLine();
					String[] lineArray = line.split(",");
					
					if (lineArray[0].equals("Expense")) {
						u.addSpending(new Expense(lineArray[1], Double.parseDouble(lineArray[2]), Integer.parseInt(lineArray[3])));
					}
					else if (lineArray[0].equals("Income")){
						u.addIncome(new Wage(lineArray[1], Double.parseDouble(lineArray[2]), Integer.parseInt(lineArray[3])));
					}
					
				}
			}
			catch (Exception exc)
		    {
		    	JOptionPane.showMessageDialog(null, "Error importing report: " + exc.getMessage());
		    }
		}
	}
}