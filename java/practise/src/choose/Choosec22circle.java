package choose;
import javax.swing.JOptionPane;
public class Choosec22circle {
	public static void main(String[]args){
		String x=JOptionPane.showInputDialog("for the position of the point please input x of (x,y)","x");
		String y=JOptionPane.showInputDialog("for the position of the point please input y of (x,y)","y");
		double xx=Double.parseDouble(x);
		double yy=Double.parseDouble(y);
		if ((xx*xx+yy*yy)>100)JOptionPane.showMessageDialog(null,"the point "+"("+xx+","+yy+")"+"is not in the circle");
		else if ((xx*xx+yy*yy)==100)JOptionPane.showMessageDialog(null,"the point "+"("+xx+","+yy+")"+"is right on the circle");
		else JOptionPane.showMessageDialog(null,"the point "+"("+xx+","+yy+")"+"is in the circle");
	}
}
