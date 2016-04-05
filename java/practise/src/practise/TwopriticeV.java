package practise;
import javax.swing.JOptionPane;
public class TwopriticeV {
	public static void main(String[]args){
		String a=JOptionPane.showInputDialog("Enter v1");
		String b=JOptionPane.showInputDialog("Enter v2");
		String c=JOptionPane.showInputDialog("Enter t");
		double v=Double.parseDouble(a);
		double vv=Double.parseDouble(b);
		double aa=Double.parseDouble(c);
		double acc=(vv-v)/aa;
		JOptionPane.showMessageDialog(null,"The average acclection is"+acc);
		
		
	}
	
	
}
