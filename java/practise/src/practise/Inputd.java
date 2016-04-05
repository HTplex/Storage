package practise;
import javax.swing.JOptionPane;
public class Inputd{
	public static void main (String[]args){
		String string=JOptionPane.showInputDialog("please input a number");
		Double x=Double.parseDouble(string);
		String stringb=JOptionPane.showInputDialog("please input a integer");
		int y=Integer.parseInt(stringb);
		double z=x+y;
		JOptionPane.showMessageDialog(null,z);
		
	}
}