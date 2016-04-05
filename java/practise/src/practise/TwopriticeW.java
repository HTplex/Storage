package practise;
import javax.swing.JOptionPane;
public class TwopriticeW {
	public static void main(String[]args){
		String vinput=JOptionPane.showInputDialog("please enter a v");
		String ainput=JOptionPane.showInputDialog("Please enter a a");
		Double v=Double.parseDouble(vinput);
		Double a=Double.parseDouble(ainput);
		double length=v*v/(2*a);
		JOptionPane.showMessageDialog(null, "The minium runway length for this airplane is"+length);
		
		
	}
	
}
