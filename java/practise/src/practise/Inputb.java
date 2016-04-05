package practise;
import javax.swing.JOptionPane;
public class Inputb {
	public static void main(String[]args){
		String string=JOptionPane.showInputDialog(null,"please input a integer");
		Double x=Double.parseDouble(string);
		double y=x*x;
		JOptionPane.showMessageDialog(null,"the square of the number is"+y);
	}
	
}
