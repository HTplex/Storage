package practise;
import javax.swing.JOptionPane;
public class TwopriticeU {
public static void main(String[]args){
	String a=JOptionPane.showInputDialog("Enter the side :");
	double x=Double.parseDouble(a);
	double area=Math.pow((27/4.0),0.5)*x*x;
	JOptionPane.showMessageDialog(null,"The area of  the hexagon is "+area);
}
}
