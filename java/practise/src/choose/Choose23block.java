package choose;
import javax.swing.JOptionPane;
public class Choose23block {
	public static void main(String[]args){
		String x=JOptionPane.showInputDialog("Enter the position point (x,y)","x");
		String y=JOptionPane.showInputDialog("Enter the position point (x,y)","y");
		double xx=Double.parseDouble(x);
		double yy=Double.parseDouble(y);
		if (xx<5&&xx>-5&&yy<2.5&&yy>(-2.5))
			JOptionPane.showMessageDialog(null, "the point "+"("+xx+","+y+") is in the block");
		else if ((((xx==5.0||xx==-5)&&yy<2.5&&yy>-2.5)||(yy==2.5||yy==(-2.5))&&xx<5&&xx>-5))
		JOptionPane.showMessageDialog(null, "the point "+"("+xx+","+y+") is in the block");
		else JOptionPane.showMessageDialog(null, "the point "+"("+xx+","+yy+") is not in the block");
	}
}
