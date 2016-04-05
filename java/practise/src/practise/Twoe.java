package practise;
import javax.swing.JOptionPane;
public class Twoe {
	public static void main(String[]args){
		String input=JOptionPane.showInputDialog("please input a number");
		double b=Double.parseDouble(input);
		int a=(int)b;
		JOptionPane.showMessageDialog(null,a+" "+b);
	}
}
