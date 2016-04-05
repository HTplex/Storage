package practise;
import javax.swing.JOptionPane;
public class TwopriticeL {
	public static void main(String[]args){
		String investment=JOptionPane.showInputDialog("Enter investment amout :");
		String rate=JOptionPane.showInputDialog("Enter m"
				+ "onthly interest rate : ");
		String time=JOptionPane.showInputDialog("Enter number of years :");
		double inv=Double.parseDouble(investment);
		double rat=Double.parseDouble(rate);
		double t=Double.parseDouble(time);
		double x=inv*(Math.pow((1+rat/1200),(12*t)));
		double y=(int)(100*x)/100.0;
		JOptionPane.showMessageDialog(null,"Accumulated value is"+y);
	}
}
