package practise;
import javax.swing.JOptionPane;
public class TwopriticeS {
	public static void main(String[]args){
	String a=JOptionPane.showInputDialog("please input the coordinate of A","x1");
	String b=JOptionPane.showInputDialog("please input the coordinate of A","y1");
	String c=JOptionPane.showInputDialog("please input the coordinate of A","x2");
	String d=JOptionPane.showInputDialog("please input the coordinate of B","y2");
	double xa=Double.parseDouble(a);
	double ya=Double.parseDouble(b);
	double xb=Double.parseDouble(c);
	double yb=Double.parseDouble(d);
	double distance=Math.pow((xb-xa)*(xb-xa)+(ya-yb)*(ya-yb),0.5);
	JOptionPane.showMessageDialog(null, "The distance of the two point is "+distance);
	}
}
//still problem//