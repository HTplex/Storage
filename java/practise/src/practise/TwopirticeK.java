package practise;
import javax.swing.JOptionPane;
public class TwopirticeK {
	public static void main(String[]args){
		String balance= JOptionPane.showInputDialog("Enter balance ");
		String rate=JOptionPane.showInputDialog("enter interest rate");
		Double bal=Double.parseDouble(balance);
		Double rat=Double.parseDouble(rate);
		double interest=(bal*rat)/(1200*100);
		JOptionPane.showMessageDialog(null, "The interest is "+interest);
	
	}
}