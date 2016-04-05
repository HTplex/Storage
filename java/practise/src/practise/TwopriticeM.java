package practise;
import javax.swing.JOptionPane;
public class TwopriticeM {
	public static void main(String[]args){
		String pounds=JOptionPane.showInputDialog("Enter weight in pounds");
		String inches=JOptionPane.showInputDialog("Enter height in inches");
		Double p=Double.parseDouble(pounds);
		Double i=Double.parseDouble(inches);
		double BMI=(p*0.45359237)/(i*0.0254*i*0.0254);
		JOptionPane.showMessageDialog(null,"BMI is "+BMI);
	}
	
}
