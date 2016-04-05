package practise;
import javax.swing.JOptionPane;
public class TwopriticeP {
	public static void main (String[]args){
		String temperature=JOptionPane.showInputDialog("Enter the temperature in Fahrenheit","only for (-59F)-(41F)");
		String wind=JOptionPane.showInputDialog("Enter the wind speed miles per hour","only for more than 2");
		double tem=Double.parseDouble(temperature);
		double speed=Double.parseDouble(wind);
		double fint=(35.74+0.6215*tem-35.75*Math.pow(speed, 0.16)+0.4275*tem*Math.pow(speed, 0.12));
		if ((tem>-59&&tem<41)||(speed>2))
		JOptionPane.showMessageDialog(null,"The wind chill index is "+fint);
		else JOptionPane.showMessageDialog(null,"Sorry, we cant calculate the number in that range");
	}
}
